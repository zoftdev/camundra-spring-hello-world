package com.hlex.lab.camundasrpringhelloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validate com.hlex.lab.camundasrpringhelloworld.JavaDelegate
 */   
public class ProcessAnsible implements JavaDelegate {

    
    Logger logger=LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info("!!! process ansible!");    
        
    }

    
}