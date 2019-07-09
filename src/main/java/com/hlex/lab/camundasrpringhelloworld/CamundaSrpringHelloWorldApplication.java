package com.hlex.lab.camundasrpringhelloworld;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
@EnableProcessApplication
public class CamundaSrpringHelloWorldApplication  {

	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(CamundaSrpringHelloWorldApplication.class, args);
	}

    
    @Autowired
	RuntimeService runtimeService;

	@Autowired
	RepositoryService repositoryService;
    
	@EventListener
	public void launchProcess(PostDeployEvent postDeployEvent){		
		
		 runtimeService.startProcessInstanceByKey("ProcessAnsible2");
		
	}
}
