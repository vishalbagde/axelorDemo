package com.axelor.sale.db.web;

import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.sale.db.Order;
import com.axelor.sale.db.OrderLine;
import com.axelor.sale.db.Tax;
import com.axelor.sale.db.repo.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrderController {

  public void setProductTotal(ActionRequest request, ActionResponse response) {
    // System.out.println(request.getContext().asType(OrderLine.class));
    OrderLine orderLine = request.getContext().asType(OrderLine.class);

    Tax tax = orderLine.getTaxes();
    float result =
        orderLine.getPrice().floatValue() * orderLine.getQuantity().floatValue()
            + orderLine.getPrice().floatValue()
                * orderLine.getQuantity().floatValue()
                * tax.getTaxRate().floatValue()
                / 100;
    orderLine.setProductTotal(new BigDecimal(result));
    response.setValue("productTotal", result);
  }

  public void setTotalAmount(ActionRequest request, ActionResponse response) {
    Order order = request.getContext().asType(Order.class);
    
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
          total_qty += orderline.getQuantity();
          total_price += result;
        }
        response.setValue("totalQuantity", total_qty);
        response.setValue("totalPrice", total_price);
        response.setValue("totalTax", total_tax);
      }
    }
  }
}
