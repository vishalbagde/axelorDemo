package com.axelor.person.db.module;

import com.axelor.app.AxelorModule;
import com.axelor.person.db.repo.ContactRepository;
import com.axelor.person.db.repo.PersonRepository;
import com.axelor.person.db.service.ContactService;
import com.axelor.person.db.service.ContactServiceImpl;

public class ContactModule extends AxelorModule {
  @Override
  protected void configure() {
    bind(ContactService.class).to(ContactServiceImpl.class);
    bind(ContactRepository.class).to(PersonRepository.class);
  }
}
