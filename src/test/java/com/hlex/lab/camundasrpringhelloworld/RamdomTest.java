package com.hlex.lab.camundasrpringhelloworld;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RamdomTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=InMemProcessEngineConfiguration.class)
public class RamdomTest {

    Logger logger=LoggerFactory.getLogger(this.getClass());
    

    @Autowired
    ProcessEngine processEngine;
     
    @Rule
    @ClassRule
    public static ProcessEngineRule rule ;
  
     
    @PostConstruct
    void initRule() {
      rule = TestCoverageProcessEngineRuleBuilder.create(processEngine).build();
    }
  
    
    @Test
    @Deployment(resources = "randomTest.bpmn")
    public void testRandom(){
        RuntimeService rt=processEngine.getRuntimeService();
        Map<String, Object> variables =new HashMap<>();
        variables.put("username", "hlex");
        rt.startProcessInstanceByKey("randomProcess",variables);
        assertEquals(0, rt.createProcessInstanceQuery().count());
        assertEquals(1,processEngine.getHistoryService().createHistoricProcessInstanceQuery().count(),1);   
        // String id=(processEngine.getHistoryService().createHistoricProcessInstanceQuery().singleResult().getProcessDefinitionId());
        HistoricVariableInstance random=processEngine.getHistoryService().createHistoricVariableInstanceQuery().variableName("random").singleResult();        
        assertEquals(5,random.getValue());
        HistoricVariableInstance username=processEngine.getHistoryService().createHistoricVariableInstanceQuery().variableName("username").singleResult();        
        assertEquals("hlex",username.getValue());

    }    
}