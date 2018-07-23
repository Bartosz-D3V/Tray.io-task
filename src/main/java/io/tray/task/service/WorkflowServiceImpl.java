package io.tray.task.service;

import io.tray.task.common.exception.ValidationException;
import io.tray.task.domain.Workflow;
import io.tray.task.domain.WorkflowExecution;
import io.tray.task.repository.WorkflowRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkflowServiceImpl implements WorkflowService {
  private final WorkflowRepository workflowRepository;

  public WorkflowServiceImpl(final WorkflowRepository workflowRepository) {
    this.workflowRepository = workflowRepository;
  }

  @Override
  public Workflow createWorkflow(final Workflow workflow) {
    return workflowRepository.addWorkflow(workflow);
  }

  @Override
  public WorkflowExecution createWorkflowExecution(final WorkflowExecution workflowExecution) throws ValidationException {
    validateWorkflowExecution(workflowExecution);
    validateWorkflow(workflowExecution);
    return workflowRepository.addWorkflowExecution(workflowExecution);
  }

  @Override
  public WorkflowExecution getWorkflowExecution(final long workflowExecutionId) {
    return workflowRepository.getWorkflowExecution(workflowExecutionId);
  }

  @Override
  public WorkflowExecution updateWorkflowExecution(final WorkflowExecution workflowExecution) {
    return workflowRepository.updateWorkflowExecution(workflowExecution);
  }

  @Override
  public long incrementCurrentStepIndex(final long workflowExecutionId) throws ValidationException {
    final WorkflowExecution workflowExecution = workflowRepository.getWorkflowExecution(workflowExecutionId);
    validateWorkflow(workflowExecution);
    workflowExecution.incrementStepIndex();
    updateWorkflowExecution(workflowExecution);
    return workflowExecution.getStepIndex();
  }

  private void validateWorkflow(final WorkflowExecution workflowExecution) throws ValidationException {
    if (!workflowRepository.hasWorkflow(workflowExecution.getWorkflowId())) {
      throw new ValidationException("Workflow with given ID does not exist");
    }
  }

  private void validateWorkflowExecution(final WorkflowExecution workflowExecution) throws ValidationException {
    if(workflowRepository.hasWorkflowExecution(workflowExecution.getWorkflowExecutionId())) {
      throw new ValidationException("Workflow execution with given ID already exist");
    }
  }
}
