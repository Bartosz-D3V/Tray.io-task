package io.tray.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tray.task.domain.Workflow;
import io.tray.task.domain.WorkflowExecution;
import io.tray.task.service.WorkflowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkflowController.class)
public class WorkflowControllerTest {
  private final static WorkflowExecution workflowExecution = new WorkflowExecution(1, 111);
  private final static Workflow workflow = new Workflow(111, 25);

  @MockBean
  private WorkflowService workflowService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createWorkflowShouldReturnWorkflow() throws Exception {
    final String workflowAsJSON = objectMapper.writeValueAsString(workflow);
    final MvcResult mvcResult = mockMvc
      .perform(
        post("/workflows")
          .content(workflowAsJSON)
          .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isCreated())
      .andReturn();

    assertNull(mvcResult.getResponse().getErrorMessage());
  }

  @Test
  public void createWorkflowExecutionShouldReturnWorkflowExecution() throws Exception {
    final String workflowExecutionAsJSON = objectMapper.writeValueAsString(workflowExecution);
    final MvcResult mvcResult = mockMvc
      .perform(
        post("/workflows/executions")
          .content(workflowExecutionAsJSON)
          .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isCreated())
      .andReturn();

    assertNull(mvcResult.getResponse().getErrorMessage());
  }

  @Test
  public void incrementCurrentStepShouldReturnCurrentStepIndex() throws Exception {
    final MvcResult mvcResult = mockMvc
      .perform(
        put("/workflows/executions/{workflowExecutionId}", 1L))
      .andExpect(status().isAccepted())
      .andReturn();

    assertNull(mvcResult.getResponse().getErrorMessage());
  }

  @Test
  public void getWorkflowExecutionShouldReturnWorkflowExecution() throws Exception {
    final String workflowExecutionAsJSON = objectMapper.writeValueAsString(workflowExecution);
    when(workflowService.getWorkflowExecution(1L)).thenReturn(workflowExecution);

    final MvcResult mvcResult = mockMvc
      .perform(
        get("/workflows/executions/{workflowExecutionId}", 1L)
          .accept(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andReturn();

    assertNull(mvcResult.getResponse().getErrorMessage());
    assertEquals(workflowExecutionAsJSON, mvcResult.getResponse().getContentAsString());
  }
}
