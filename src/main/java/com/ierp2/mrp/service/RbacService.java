package com.ierp2.mrp.service;

import com.ierp2.mrp.configuration.jpa.impl.Conditions;
import com.ierp2.mrp.configuration.security.UserSession;
import com.ierp2.mrp.dao.*;
import com.ierp2.mrp.entity.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
@Transactional
public class RbacService implements UserDetailsService {


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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByAccount(username);
    }

    private UserDetails loadUserByAccount(String account) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Boolean swithUser = (Boolean) request.getAttribute("switchUser");
        if (swithUser != null && swithUser) {
            account = (String) request.getAttribute("account");
        }
        //获取账号下多个租户下的用户
        Conditions conditions = Conditions.where("account").is(account).and("recordStatus").is(1);
        List<User> users = userDao.findAll(conditions);
        if (users != null && users.size() > 0) {
            String tenantCode = (String) request.getAttribute("tenantCode");
            //指定登录的租户用户
            if (isNotEmpty(tenantCode)) {
                Optional<User> first = users.stream().filter(user -> user.getTenantCode().equals(tenantCode)).findFirst();
                if (first.isPresent()) {
                    return createUser(first.get());
                }
            }
            //取第一个可用的租户用户
            return createUser(users.get(0));
        }
        //没有可用的租户用户
        throw new UsernameNotFoundException("账号或密码错误");
    }

    private UserSession createUser(User user) {
        //加载用户权限
        Set<String> authorities = new HashSet<>();
        if (user != null) {
            authorities = this.findAuthorities(user);
        }
        UserSession userSession = new UserSession(user.getAccount(),
                user.getPassword(),
                user.getRecordStatus() == 1,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()]))
        );
        //权限集合
        userSession.setAccount(user.getAccount());
        userSession.setUserCode(user.getUserCode());
        userSession.setTenantCode(user.getTenantCode());
        return userSession;
    }


    private Set<String> findAuthorities(User user) {
        Set<String> authorities = new HashSet<>();
        if (user.isAdmin()) {
            List<Resource> resources = this.findResources();
            for (Resource resource : resources) {
                authorities.add(resource.getResourceCode());
            }
        } else {
            List<Role> roles = this.findRolesByUserCode(user.getTenantCode(), user.getUserCode());
            for (Role role : roles) {
                List<Resource> resources = this.findResourcesByRoleCode(role.getTenantCode(), role.getRoleCode());
                for (Resource resource : resources) {
                    authorities.add(resource.getResourceCode());
                }
            }
        }
        return authorities;
    }

    public List<Resource> findResourcesByRoleCode(String tenantCode, String roleCode) {
        List<RoleResource> roleResources = roleResourceDao.findByTenantCodeAndRoleCode(tenantCode, roleCode);
        List<String> resourceCodes = roleResources.stream().map(RoleResource::getResourceCode).collect(Collectors.toList());
        Conditions conditions = Conditions
                .where("resourceCode").in(resourceCodes);
        return resourceDao.findAll(conditions);
    }

    public List<Role> findRolesByUserCode(String tenantCode, String userCode) {
        List<UserRole> userRoles = userRoleDao.findByTenantCodeAndUserCode(tenantCode, userCode);
        List<String> roleCodes = userRoles.stream().map(UserRole::getRoleCode).collect(Collectors.toList());
        Conditions conditions = Conditions
                .where("tenantCode").is(tenantCode)
                .and("roleCode").in(roleCodes);
        return roleDao.findAll(conditions);
    }

    public User getUserByUserCode(String userCode) {
        return userDao.findOne(Conditions.where("userCode").is(userCode));
    }

    public List<Resource> findResources() {
        return resourceDao.findAll();
    }

    public List<User> findUsers() {
        return userDao.findAll();
    }

    public List<Role> findRoles() {
        return roleDao.findAll();
    }

    public List<Dept> findDepts() {
        return deptDao.findAll();
    }

}
