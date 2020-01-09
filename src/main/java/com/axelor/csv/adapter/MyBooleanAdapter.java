package com.axelor.csv.adapter;

import com.axelor.data.adapter.Adapter;
import java.util.Map;
import java.util.regex.Pattern;

public class MyBooleanAdapter extends Adapter {

  private Pattern pattern;

  @Override
  public Object adapt(Object value, Map<String, Object> context) {

    if (pattern == null) {
      String truePatten = this.get("truePattern", "(T|1|True)");
      System.out.println(truePatten);
      pattern = Pattern.compile(truePatten, Pattern.CASE_INSENSITIVE);
    }

    return pattern.matcher((String) value).matches();
  }
}
