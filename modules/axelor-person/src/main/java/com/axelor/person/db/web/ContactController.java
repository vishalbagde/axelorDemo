package com.axelor.person.db.web;

import com.axelor.person.db.Contact;
import com.axelor.person.db.Email;
import com.axelor.person.db.Phone;
import com.axelor.person.db.service.ContactService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;
import java.util.List;

// import com.axelor.meta.CallMethod;
public class ContactController {

  @Inject ContactService service;

  public String say(String what) {
    return "About : " + what;
  }

  public void setFullName(ActionRequest request, ActionResponse response) {

    Contact contact = request.getContext().asType(Contact.class);
    contact.setFullName(contact.getFirstName() + " " + contact.getLastName());
    // service.onSave(contact);
    response.setValue("fullName", contact.getFirstName() + " " + contact.getLastName());
  }

  public void setEmail(ActionRequest request, ActionResponse response) {
    Contact contact = request.getContext().asType(Contact.class);
    List<Email> emails = contact.getEmails();

    if (emails != null || !emails.isEmpty()) {
      for (Email email : emails) {
        if (email.getPrimary()) {
          response.setValue("email", email.getEmail());
          break;
        }
      }
      response.setValue("email", emails.get(0).getEmail());
    }
  }

  public void setPhone(ActionRequest request, ActionResponse response) {
    Contact contact = request.getContext().asType(Contact.class);
    List<Phone> phones = contact.getPhones();
    if (phones != null || !phones.isEmpty()) {
      for (Phone phone : phones) {
        if (phone.getPrimary()) {
          response.setValue("phone", phone.getPhone());
          break;
        }
      }
      response.setValue("phone", phones.get(0).getPhone());
    }
  }
}
