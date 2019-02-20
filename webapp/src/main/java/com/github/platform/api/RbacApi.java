package com.github.platform.api;

import com.github.platform.rbac.entity.Dept;
import com.github.platform.rbac.entity.Role;
import com.github.platform.rbac.entity.User;
import com.github.platform.rbac.service.RbacService;
import com.github.platform.web.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "RBAC管理api")
@RestController
public class RbacApi {

    @Autowired
    RbacService rbacService;

    @ApiOperation("列表-部门")
    @GetMapping("/rbac/list-depts")
    public Response<List<Dept>> listDepts(@RequestHeader("tenantCode") String tenantCode) {
        List<Dept> depts = rbacService.findDepts(tenantCode);
        return Response.success(depts);
    }

    @ApiOperation("列表-用户")
    @GetMapping("/rbac/list-users")
    public Response<List<User>> listUsers(@RequestHeader("tenantCode") String tenantCode) {
        List<User> users = rbacService.findUsers(tenantCode);
        return Response.success(users);
    }

    @ApiOperation("详情-用户")
    @GetMapping("/rbac/get-user")
    public Response<User> getUser(@RequestParam("userCode") String userCode) {
        User user = rbacService.getUserByUserCode(userCode);
        return Response.success(user);
    }

    @ApiOperation("列表-角色")
    @GetMapping("/rbac/list-roles")
    public Response<List<Role>> listRoles(@RequestHeader("tenantCode") String tenantCode) {
        List<Role> roles = rbacService.findRoles(tenantCode);
        return Response.success(roles);
    }
}
