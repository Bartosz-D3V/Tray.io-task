package io.tray.task.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorkflowExecution implements Serializable {
  private long workflowExecutionId;
  private long workflowId;
  private int stepIndex;
  private LocalDateTime timestamp;

  public WorkflowExecution() {
    super();
  }

  public WorkflowExecution(final long workflowExecutionId, final long workflowId) {
    this.workflowExecutionId = workflowExecutionId;
    this.workflowId = workflowId;
    stepIndex = 0;
    timestamp = LocalDateTime.now();
  }

  public long getWorkflowExecutionId() {
    return workflowExecutionId;
  }

  public void setWorkflowExecutionId(final long workflowExecutionId) {
    this.workflowExecutionId = workflowExecutionId;
  }

  public long getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(final long workflowId) {
    this.workflowId = workflowId;
  }

  public int getStepIndex() {
    return stepIndex;
  }

  public void setStepIndex(final int stepIndex) {
    this.stepIndex = stepIndex;
  }

  public void incrementStepIndex() {
    stepIndex++;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
