package com.axelor.sale.db.service;

import com.axelor.sale.db.Order;

public interface OrderService {

  public Order calculateTotalAmount(Order order);
}
