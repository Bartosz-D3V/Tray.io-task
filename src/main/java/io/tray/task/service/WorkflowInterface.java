package io.tray.task.service;

import io.tray.task.common.exception.ValidationException;
import io.tray.task.domain.Workflow;
import io.tray.task.domain.WorkflowExecution;

public interface WorkflowInterface {
  Workflow createWorkflow(Workflow workflow) throws ValidationException;

  WorkflowExecution createWorkflowExecution(WorkflowExecution workflowExecution) throws ValidationException;

  long incrementCurrentStepIndex(long workflowExecutionId) throws ValidationException;

  WorkflowExecution updateWorkflowExecution(WorkflowExecution workflowExecution);

  WorkflowExecution getWorkflowExecution(long workflowExecutionId);
}
