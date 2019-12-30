package com.axelor.person.db.repo;

import com.axelor.person.db.Contact;
import java.util.Map;

public class ContactRepository extends AbstractContactRepository {

  @Override
  public Map<String, Object> populate(Map<String, Object> json, Map<String, Object> context) {
    if (!context.containsKey("json-enhance")) {
      return json;
    }
    try {
      Long id = (Long) json.get("id");
      Contact contact = find(id);
      json.put("address", contact.getAddresses().get(0));
      json.put("hasImage", contact.getImage() != null);
    } catch (Exception e) {
    }
    return json;
  }
}
