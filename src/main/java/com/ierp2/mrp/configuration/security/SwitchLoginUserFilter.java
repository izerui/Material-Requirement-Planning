package com.ierp2.mrp.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by serv on 2017/3/29.
 */
public class SwitchLoginUserFilter extends SwitchUserFilter {

    @Override
    protected Authentication attemptSwitchUser(HttpServletRequest request) throws AuthenticationException {
        String account = UserSession.getUser().getAccount();
        request.setAttribute("switchUser", true);
        request.setAttribute("account", account);
        request.setAttribute("tenantCode", request.getParameter("tenantCode"));
        return super.attemptSwitchUser(request);
    }
}
