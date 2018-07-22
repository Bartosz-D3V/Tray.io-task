package io.tray.task.repository;

import io.tray.task.domain.Workflow;
import io.tray.task.domain.WorkflowExecution;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class WorkflowRepository {
  private ConcurrentLinkedQueue<WorkflowExecution> workflowExecutionList;
  private ConcurrentLinkedQueue<Workflow> workflowList;

  public WorkflowRepository() {
    workflowExecutionList = new ConcurrentLinkedQueue<>();
    workflowList = new ConcurrentLinkedQueue<>();
  }

  public WorkflowExecution addWorkflowExecution(final WorkflowExecution workflowExecution) {
    workflowExecutionList.add(workflowExecution);
    return workflowExecution;
  }

  public boolean hasWorkflowExecution(final long workflowExecutionId) {
    return workflowExecutionList
      .stream()
      .anyMatch(workflowExecution -> workflowExecution.getWorkflowExecutionId() == workflowExecutionId);
  }

  public Workflow addWorkflow(final Workflow workflow) {
    workflowList.add(workflow);
    return workflow;
  }

  public boolean hasWorkflow(final long workflowId) {
    return workflowList
      .stream()
      .anyMatch(workflow -> workflow.getWorkflowId() == workflowId);
  }

  public WorkflowExecution getWorkflowExecution(final long workflowExecutionId) {
    return workflowExecutionList
      .stream()
      .filter(workflowExecution -> workflowExecution.getWorkflowExecutionId() == workflowExecutionId)
      .findFirst()
      .orElse(null);
  }

  public WorkflowExecution updateWorkflowExecution(final WorkflowExecution workflowExecution) {
    final WorkflowExecution oldWorkflowExecution = getWorkflowExecution(workflowExecution.getWorkflowExecutionId());
    workflowExecutionList.remove(oldWorkflowExecution);
    workflowExecutionList.add(workflowExecution);
    return workflowExecution;
  }
}
