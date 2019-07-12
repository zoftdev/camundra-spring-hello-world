package com.hlex.lab.camundasrpringhelloworld.filter;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.hlex.lab.camundasrpringhelloworld.util.UserUtil;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyAuthenticationMockAdapter extends ContainerBasedAuthenticationProvider {

    Logger logger=LoggerFactory.getLogger(this.getClass());
    UserUtil userUtil=new UserUtil();

    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {
        logger.info("extract authen @ MyAuthenticationMockAdapter");
        AuthenticationResult authenticationResult=new AuthenticationResult("hlex", true);
        String name="hlex";
        String roles[]= new String[] {"camunda-admin","employee","dosadmin"};
        authenticationResult.setGroups(Arrays.asList( roles));
        userUtil.autocreate(engine,name,roles);
        // engine.getIdentityService().newUser(userId)
        return authenticationResult;

    }

     

}
