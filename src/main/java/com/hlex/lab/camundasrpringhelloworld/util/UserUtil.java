package com.hlex.lab.camundasrpringhelloworld.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;

/**
 * UserUtil
 */
public class UserUtil {

	public void autoCreateUser(ProcessEngine engine, String name, String[] roles) {
        
      User singleResult =  engine.getIdentityService().createUserQuery().userId(name).singleResult();
      if (singleResult == null) {
        
      

        User user = engine.getIdentityService().newUser(name);
        user.setFirstName(name);
        user.setLastName(name);
        user.setPassword("---");
        user.setEmail("demo@camunda.org");
        engine.getIdentityService().saveUser(user); 
        for (String role : roles) {
          engine.getIdentityService().deleteMembership(name, role);
          engine.getIdentityService().createMembership(name, role);  
        }   
      }

      
      
               
      // engine.getIdentityService().newGroup(role);

     
	}

  public void autoCreateRole(ProcessEngine engine, String[] roles) {
      
    //default group is camnunda-admin|system
    // foreach group - create if miss, join to group
    List<Group> group=engine.getIdentityService().createGroupQuery().groupIdIn(roles).list();
    List<String> createdGroupId=  group.stream().map(Group::getId).collect(Collectors.toList());
    List<String> toCreateGroups=Arrays.asList(roles).stream().filter(e-> !createdGroupId.contains(e)  ).collect(Collectors.toList());
    for (String groupId2Create : toCreateGroups) {
      Group toCreateGroup=engine.getIdentityService().newGroup(groupId2Create);
      toCreateGroup.setName(groupId2Create);
      toCreateGroup.setType("type1");
      engine.getIdentityService().saveGroup(toCreateGroup);

    }
    



    }

    
}