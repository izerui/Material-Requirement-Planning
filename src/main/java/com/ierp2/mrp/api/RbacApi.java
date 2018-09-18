package com.ierp2.mrp.api;

import com.ierp2.mrp.configuration.web.Response;
import com.ierp2.mrp.entity.Dept;
import com.ierp2.mrp.entity.Role;
import com.ierp2.mrp.entity.User;
import com.ierp2.mrp.service.RbacService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ierp2.mrp.configuration.web.Response.*;

@Api(description = "RBAC管理api")
@RestController
public class RbacApi {

    @Autowired
    RbacService rbacService;

    @ApiOperation("列表-部门")
    @GetMapping("/rbac/list-depts")
    public Response<List<Dept>> listDepts(){
        List<Dept> depts = rbacService.findDepts();
        return success(depts);
    }

    @ApiOperation("列表-用户")
    @GetMapping("/rbac/list-users")
    public Response<List<User>> listUsers(){
        List<User> users = rbacService.findUsers();
        return success(users);
    }

    @ApiOperation("详情-用户")
    @GetMapping("/rbac/get-user")
    public Response<User> getUser(@RequestParam("userCode")String userCode){
        User user = rbacService.getUserByUserCode(userCode);
        return success(user);
    }

    @ApiOperation("列表-角色")
    @GetMapping("/rbac/list-roles")
    public Response<List<Role>> listRoles(){
        List<Role> roles = rbacService.findRoles();
        return success(roles);
    }
}
