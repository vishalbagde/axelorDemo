package com.axelor.person.db.service;

import com.axelor.person.db.Contact;

public interface ContactService {

  public void onSave(Contact contact);

  public void onNew(Contact contact);

  public void onRemove(Contact contact);
}
