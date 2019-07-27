package com.hlex.lab.camundasrpringhelloworld.filter;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

import com.hlex.lab.camundasrpringhelloworld.util.UserUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringSecurityAuthenticationCopyGroup extends ContainerBasedAuthenticationProvider {

    Logger logger=LoggerFactory.getLogger(this.getClass());
    UserUtil userUtil=new UserUtil();

    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return AuthenticationResult.unsuccessful();
        }

        String name = authentication.getName();
        if (name == null || name.isEmpty()) {
            return AuthenticationResult.unsuccessful();
        }

        logger.info("extract authen @ SpringSecurityAuthenticationCopyGroup");
        AuthenticationResult authenticationResult = new AuthenticationResult(name, true);
        String roles[];        
        roles=toCamundaRole(authentication);
        userUtil.autoCreateRole(engine,  roles);
        userUtil.autoCreateUser(engine,name,roles);        
        authenticationResult.setGroups(Arrays.asList( roles));        
        return authenticationResult;
    }

    private String[] toCamundaRole(Authentication authentication) {
        String name=authentication.getName();
        String roles[];

        if(name.equals("dosadmin1")){
            roles= new String[] { "dosadmin"};
        }else if (name.equals("admin")) {
            roles= new String[] { "camunda-admin"};
        
        }else {
            roles= new String[] { "user"};
        }
        return roles;
    }

    // private List<String> getUserGroups(Authentication authentication) {

    //     List<String> groupIds;
    //     logger.info("Spring forward row {}",authentication.getAuthorities());
    //     groupIds = authentication.getAuthorities().stream()
    //             .map(res -> res.getAuthority())
    //             .map(res -> res.substring(5)) // Strip "ROLE_"
    //             .collect(Collectors.toList());

    //     return groupIds;

    // }

}
