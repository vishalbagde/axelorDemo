package com.axelor.person.db.service;

import com.axelor.person.db.Contact;
import com.axelor.person.db.repo.PersonRepository;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class ContactServiceImpl implements ContactService {

  @Inject PersonRepository personRepo;

  @Override
  public void onNew(Contact contact) {
    // personRepo.persist(contact);
  }

  @Override
  public void onRemove(Contact contact) {
    // personRepo.remove(contact);
  }

  @Override
  @Transactional
  public void onSave(Contact contact) {
    System.out.println(contact.getFullName());
    System.err.println(contact.getClass());
    personRepo.save(contact);
  }
}
