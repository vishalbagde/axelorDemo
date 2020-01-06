package com.axelor.project.db.web;

import com.axelor.meta.CallMethod;
import com.axelor.project.db.Event;
import com.axelor.project.db.EventStatus;
import com.axelor.project.db.ProjectTask;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloController {

  protected Logger log = LoggerFactory.getLogger(getClass());

  
  public String hello(String what) {
    System.out.println(what);
    return "Hello world " + what;
  }

  
  public void hello(ActionRequest request, ActionResponse response) {
    String str = "hello world";
    response.setFlash(str);
  }

  
  public void say(ActionRequest request, ActionResponse response) {
    ProjectTask projectTask = request.getContext().asType(ProjectTask.class);
    projectTask.setNotes("this is test note");

    response.setFlash(projectTask.getName());
  }
  
  
  
  public void changeStatus(ActionRequest request,ActionResponse response)
  {
	  
	  Event event = request.getContext().asType(Event.class);
	  
	  String action=request.getAction();
	  if(action.equals("action-project-event-method-onclick-change-statusPending"))
		  response.setAttr("eventStatus","value", "PENDING");
	  else if(action.equals("action-project-event-method-onclick-change-statusActive"))
		  response.setAttr("eventStatus","value", "ACTIVE");
	  else if(action.equals("action-project-event-method-onclick-change-statusDeactive"))
		  response.setAttr("eventStatus","value", "DEACTIVE");
	  else if(action.equals("action-project-event-method-onclick-change-statusClosed"))
		  response.setAttr("eventStatus","value", "CLOSED"); 
	  
  }
  
}
