package io.tray.task.domain;

import java.io.Serializable;

public class Workflow implements Serializable {
  private long workflowId;
  private int numberOfSteps;

  public Workflow() {
    super();
  }

  public Workflow(final long workflowId, final int numberOfSteps) {
    this.workflowId = workflowId;
    this.numberOfSteps = numberOfSteps;
  }

  public long getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(final long workflowId) {
    this.workflowId = workflowId;
  }

  public int getNumberOfSteps() {
    return numberOfSteps;
  }

  public void setNumberOfSteps(final int numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
  }
}
