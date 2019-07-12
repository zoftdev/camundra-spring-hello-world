package com.hlex.lab.camundasrpringhelloworld.task;

import org.apache.catalina.core.ApplicationContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.hlex.lab.camundasrpringhelloworld.task.springBeanJavaDelegate
 * To test if spring autowired is work?
 */
public class springBeanJavaDelegate implements JavaDelegate {

    @Autowired
    ApplicationContext context;

    Logger logger=LoggerFactory.getLogger(this.getClass());
    

	@Override
	public void execute(DelegateExecution execution) throws Exception {
        logger.info("context: {}",context);        
	}
    
}