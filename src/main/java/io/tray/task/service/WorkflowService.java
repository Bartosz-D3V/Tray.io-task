package io.tray.task.service;

import io.tray.task.common.exception.ValidationException;
import io.tray.task.domain.Workflow;
import io.tray.task.domain.WorkflowExecution;

public interface WorkflowService {
  Workflow createWorkflow(Workflow workflow);

  WorkflowExecution createWorkflowExecution(WorkflowExecution workflowExecution) throws ValidationException;

  WorkflowExecution getWorkflowExecution(long workflowExecutionId);

  long incrementCurrentStepIndex(long workflowExecutionId) throws ValidationException;

  WorkflowExecution updateWorkflowExecution(WorkflowExecution workflowExecution);
}
