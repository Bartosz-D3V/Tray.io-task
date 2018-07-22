package io.tray.task.controller;

import io.tray.task.common.exception.ValidationException;
import io.tray.task.domain.Workflow;
import io.tray.task.domain.WorkflowExecution;
import io.tray.task.service.WorkflowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController(value = "/workflows")
public final class WorkflowController {
  private final WorkflowService workflowService;

  public WorkflowController(final WorkflowService workflowService) {
    this.workflowService = workflowService;
  }

  @PostMapping(
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public Workflow createWorkflow(@RequestBody final Workflow workflow) {
    return workflowService.createWorkflow(workflow);
  }

  @PostMapping(
    value = "/executions",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public WorkflowExecution createWorkflowExecution(@RequestBody final WorkflowExecution workflowExecution)
    throws ValidationException {
    return workflowService.createWorkflowExecution(workflowExecution);
  }

  @PutMapping(
    value = "/executions/{workflowExecutionId}",
    produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Map<String, Long> incrementCurrentStep(@PathVariable final long workflowExecutionId) throws ValidationException {
    return Collections.singletonMap("currentStepIndex", workflowService.incrementCurrentStepIndex(workflowExecutionId));
  }

  @GetMapping(value = "/{workflowExecutionId}")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public WorkflowExecution getWorkflowExecution(@PathVariable final long workflowExecutionId) {
    return workflowService.getWorkflowExecution(workflowExecutionId);
  }
}
