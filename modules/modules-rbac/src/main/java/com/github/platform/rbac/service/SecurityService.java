package com.github.platform.rbac.service;

import com.github.platform.rbac.dao.*;
import com.github.platform.rbac.dto.UserSession;
import com.github.platform.rbac.entity.Resource;
import com.github.platform.rbac.entity.RoleResource;
import com.github.platform.rbac.entity.User;
import com.github.platform.rbac.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.platform.jpa.base.Conditions.where;

@Service
@Transactional
public class SecurityService implements UserDetailsService {


    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleResourceDao roleResourceDao;
    @Autowired
    ResourceDao resourceDao;
    @Autowired
    DeptDao deptDao;

    @Deprecated
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Boolean swithUser = (Boolean) request.getAttribute("switchUser");
        if (swithUser != null && swithUser) {
            username = (String) request.getAttribute("userName");
        }
        //获取账号下多个租户下的用户
        List<User> users = userDao.findAll(
                where("username").is(username).and("recordStatus").is(1)
        );
        if (users != null && users.size() > 0) {
            String tenantCode = (String) request.getAttribute("tenantCode");
            //指定登录的租户用户
            if (tenantCode != null && !"".equals(tenantCode)) {
                Optional<User> first = users.stream().filter(user -> user.getTenantCode().equals(tenantCode)).findFirst();
                if (first.isPresent()) {
                    return createSession(first.get());
                }
            }
            //取第一个可用的租户用户
            return createSession(users.get(0));
        }
        //没有可用的租户用户
        throw new UsernameNotFoundException("账号或密码错误");
    }

    private UserSession createSession(User user) {
        //加载用户权限
        Set<String> authorities = this.findUserAuthorities(user);
        UserSession userSession = new UserSession(user.getUserName(),
                user.getPassword(),
                user.getRecordStatus() == 1,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()]))
        );
        //权限集合
        userSession.setUserCode(user.getUserCode());
        userSession.setTenantCode(user.getTenantCode());
        return userSession;
    }


    private Set<String> findUserAuthorities(User user) {
        List<Resource> resources;
        if (user.isAdmin()) {
            resources = resourceDao.findAll();
        } else {
            List<String> roleCodes = this.findRoleCodesByUserCode(user.getTenantCode(), user.getUserCode());
            resources = this.findResourcesByRoleCodes(user.getTenantCode(), roleCodes);
        }
        return resources.stream().map(Resource::getResourceCode).collect(Collectors.toSet());
    }


    public List<Resource> findResourcesByRoleCodes(String tenantCode, Iterable<String> roleCodes) {
        List<RoleResource> roleResources = roleResourceDao.findAll(
                where("tenantCode").is(tenantCode).and("roleCode").in(roleCodes)
        );
        List<String> resourceCodes = roleResources.stream().map(RoleResource::getResourceCode).collect(Collectors.toList());
        return resourceDao.findAll(
                where("resourceCode").in(resourceCodes)
        );
    }


    public List<String> findRoleCodesByUserCode(String tenantCode, String userCode) {
        List<UserRole> userRoles = userRoleDao.findByTenantCodeAndUserCode(tenantCode, userCode);
        List<String> roleCodes = userRoles.stream().map(UserRole::getRoleCode).collect(Collectors.toList());
        return roleCodes;
    }


    public List<Resource> findResources() {
        return resourceDao.findAll();
    }

}
