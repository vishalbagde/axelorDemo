package com.axelor.csv.imports;

import com.axelor.sale.db.Order;
import com.axelor.sale.db.OrderLine;
import com.axelor.sale.db.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SaleOrderImport {
  public Object updateOrder(Object bean, Map<String, Object> values) {

    assert bean instanceof Order;
    Order order = (Order) bean;
    int total_qty = 0;
    float total_price = 0;
    float total_tax = 0;
    if (order != null) {

      List<OrderLine> orderlineList = order.getSaleProducts();
      if (!orderlineList.isEmpty() || orderlineList == null) {

        for (OrderLine orderline : orderlineList) {
          Tax tax = orderline.getTaxes();
          float total = orderline.getPrice().floatValue() * orderline.getQuantity().floatValue();
          float tax_rate = total * tax.getTaxRate().floatValue() / 100;
          total_tax += tax_rate;
          float result = total + tax_rate;

           orderline.setProductTotal(new BigDecimal(result));

          total_qty += orderline.getQuantity();
          total_price += result;
        }
      }
      order.setSaleProducts(orderlineList);
      order.setOrderDate(LocalDate.now());
      order.setTotalQuantity(total_qty);
      order.setTotalPrice(new BigDecimal(total_price));
      order.setTotalTax(new BigDecimal(total_tax));
    }
    return order;
  }
}
