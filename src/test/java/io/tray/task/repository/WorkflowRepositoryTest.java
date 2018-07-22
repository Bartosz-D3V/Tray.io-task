package io.tray.task.repository;

import io.tray.task.domain.Workflow;
import io.tray.task.domain.WorkflowExecution;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkflowRepositoryTest {
  private final static WorkflowExecution workflowExecution = new WorkflowExecution(1, 111);
  private final static Workflow workflow = new Workflow(111, 25);

  @Autowired
  private WorkflowRepository workflowRepository;

  @Before
  public void setUp() {
    workflowRepository.addWorkflowExecution(workflowExecution);
    workflowRepository.addWorkflow(workflow);
  }

  @Test
  public void hasWorkflowExecutionShouldReturnTrueIfWorkflowExecutionExists() {
    assertTrue(workflowRepository.hasWorkflowExecution(1));
  }

  @Test
  public void hasWorkflowExecutionShouldReturnFalseIfWorkflowExecutionDoesNotExist() {
    assertFalse(workflowRepository.hasWorkflowExecution(123));
  }

  @Test
  public void hasWorkflowShouldReturnTrueIfWorkflowExists() {
    assertTrue(workflowRepository.hasWorkflow(111));
  }

  @Test
  public void hasWorkflowShouldReturnFalseIfWorkflowDoesNotExist() {
    assertFalse(workflowRepository.hasWorkflow(123));
  }

  @Test
  public void getWorkflowExecutionShouldReturnWorkflowExecution() {
    assertEquals(workflowExecution, workflowRepository.getWorkflowExecution(1));
  }

  @Test
  public void getWorkflowExecutionShouldReturnNullIfWorkflowExecutionWasNotFound() {
    assertNull(workflowRepository.getWorkflowExecution(1000));
  }

  @Test
  public void updateWorkflowExecutionShouldUpdateWorkflowExecution() {
    final WorkflowExecution updatedWorkflowExecution = new WorkflowExecution(1, 222);
    final WorkflowExecution actualWorkflowExecution = workflowRepository.updateWorkflowExecution(updatedWorkflowExecution);

    assertEquals(updatedWorkflowExecution, actualWorkflowExecution);
  }
}
