package io.tray.task.service;

import io.tray.task.common.exception.ValidationException;
import io.tray.task.domain.Workflow;
import io.tray.task.domain.WorkflowExecution;
import io.tray.task.repository.WorkflowRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkflowInterfaceImpl implements WorkflowInterface {
  private final WorkflowRepository workflowRepository;

  public WorkflowInterfaceImpl(final WorkflowRepository workflowRepository) {
    this.workflowRepository = workflowRepository;
  }

  @Override
  public Workflow createWorkflow(final Workflow workflow) throws ValidationException {
    if (workflowRepository.hasWorkflowExecution(workflow.getWorkflowId())) {
      throw new ValidationException("Workflow with given ID exists");
    }
    return workflowRepository.addWorkflow(workflow);
  }

  @Override
  public WorkflowExecution createWorkflowExecution(final WorkflowExecution workflowExecution) throws ValidationException {
    validateWorkflowExecution(workflowExecution.getWorkflowExecutionId());
    if (workflowRepository.hasWorkflowExecution(workflowExecution.getWorkflowId())) {
      throw new ValidationException("Workflow with provided ID does not exist");
    }
    return workflowRepository.addWorkflowExecution(workflowExecution);
  }

  @Override
  public long incrementCurrentStepIndex(final long workflowExecutionId) throws ValidationException {
    validateWorkflowExecution(workflowExecutionId);
    final WorkflowExecution workflowExecution = getWorkflowExecution(workflowExecutionId);
    workflowExecution.incrementStepIndex();
    updateWorkflowExecution(workflowExecution);
    return workflowExecution.getStepIndex();
  }

  @Override
  public WorkflowExecution updateWorkflowExecution(final WorkflowExecution workflowExecution) {
    return workflowRepository.updateWorkflowExecution(workflowExecution);
  }

  @Override
  public WorkflowExecution getWorkflowExecution(final long workflowExecutionId) {
    return workflowRepository.getWorkflowExecution(workflowExecutionId);
  }

  private void validateWorkflowExecution(final long workflowExecutionId) throws ValidationException {
    if (workflowRepository.hasWorkflow(workflowExecutionId)) {
      throw new ValidationException("Workflow execution with given ID exists");
    }
  }
}
