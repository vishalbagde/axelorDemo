package com.axelor.project.db.web;

import com.axelor.meta.CallMethod;
import com.axelor.project.db.ProjectTask;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloController {

  protected Logger log = LoggerFactory.getLogger(getClass());

  @CallMethod
  public String hello(String what) {
    System.out.println(what);
    return "Hello world " + what;
  }

  @CallMethod
  public void hello(ActionRequest request, ActionResponse response) {
    String str = "hello world";
    response.setFlash(str);
  }

  @CallMethod
  public void say(ActionRequest request, ActionResponse response) {
    ProjectTask projectTask = request.getContext().asType(ProjectTask.class);
    projectTask.setNotes("this is test note");

    response.setFlash(projectTask.getName());
  }
}
