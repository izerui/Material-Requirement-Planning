package com.ierp2.mrp.api;

import com.ierp2.mrp.configuration.web.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ierp2.mrp.configuration.web.Response.error;
import static com.ierp2.mrp.configuration.web.Response.success;

@Api(description = "获取已登录用户相关信息")
@RestController
public class ContextApi {

    @ApiOperation("获取当前sessionId")
    @GetMapping("/session")
    public Response<String> getSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return success(session.getId());
        } else {
            return error("-1","获取session失败");
        }
    }

}
