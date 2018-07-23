package io.tray.task.service;

import io.tray.task.common.exception.ValidationException;
import io.tray.task.domain.WorkflowExecution;
import io.tray.task.repository.WorkflowRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkflowServiceTest {

  @Autowired
  private WorkflowService workflowService;

  @SpyBean
  private WorkflowRepository workflowRepository;

  @Test
  public void createWorkflowExecutionShouldCreateWorkflowExecution() throws ValidationException {
    when(workflowRepository.hasWorkflow(111)).thenReturn(true);
    when(workflowRepository.hasWorkflowExecution(1)).thenReturn(false);

    final WorkflowExecution workflowExecution = new WorkflowExecution(1, 111);

    assertEquals(workflowExecution, workflowService.createWorkflowExecution(workflowExecution));
    assertEquals(0, workflowExecution.getStepIndex());
  }

  @Test(expected = ValidationException.class)
  public void createWorkflowExecutionShouldThrowErrorIfRequestedWorkflowDoesNotExist() throws ValidationException {
    when(workflowRepository.hasWorkflow(111)).thenReturn(false);
    when(workflowRepository.hasWorkflowExecution(1)).thenReturn(false);

    final WorkflowExecution workflowExecution = new WorkflowExecution(1, 111);
    workflowService.createWorkflowExecution(workflowExecution);
  }

  @Test(expected = ValidationException.class)
  public void createWorkflowExecutionShouldThrowErrorIfWorkflowExecutionAlreadyExist() throws ValidationException {
    when(workflowRepository.hasWorkflow(111)).thenReturn(true);
    when(workflowRepository.hasWorkflowExecution(1)).thenReturn(true);

    final WorkflowExecution workflowExecution = new WorkflowExecution(1, 111);
    workflowService.createWorkflowExecution(workflowExecution);
  }

  @Test
  public void incrementCurrentStepIndexShouldIncrementCurrentStepIndex() throws ValidationException {
    when(workflowRepository.hasWorkflow(111)).thenReturn(true);
    when(workflowRepository.hasWorkflowExecution(1)).thenReturn(false);

    final WorkflowExecution workflowExecution = new WorkflowExecution(1, 111);
    when(workflowRepository.getWorkflowExecution(1)).thenReturn(workflowExecution);

    assertEquals(1, workflowService.incrementCurrentStepIndex(1));
    assertEquals(2, workflowService.incrementCurrentStepIndex(1));
  }

  @Test(expected = ValidationException.class)
  public void incrementCurrentStepIndexShouldThrowErrorIfRequestedWorkflowDoesNotExist() throws ValidationException {
    when(workflowRepository.hasWorkflow(111)).thenReturn(false);
    when(workflowRepository.hasWorkflowExecution(1)).thenReturn(false);

    final WorkflowExecution workflowExecution = new WorkflowExecution(1, 111);
    when(workflowRepository.getWorkflowExecution(1)).thenReturn(workflowExecution);

    workflowService.incrementCurrentStepIndex(1);
  }

  @Test(expected = ValidationException.class)
  public void incrementCurrentStepIndexShouldThrowErrorIfRequestedWorkflowExecutionDoesNotExist() throws ValidationException {
    when(workflowRepository.getWorkflowExecution(1)).thenReturn(null);

    workflowService.incrementCurrentStepIndex(1);
  }
}
