package com.axelor.sale.db.web;

import com.axelor.data.ImportTask;
import com.axelor.data.Importer;
import com.axelor.data.csv.CSVImporter;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.sale.db.Order;
import com.axelor.sale.db.OrderLine;
import com.axelor.sale.db.Tax;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class OrderController {

  public void getProductIdList(ActionRequest request, ActionResponse response) {
    response.setFlash("hello");
  }

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

  public void setSaleOrderNo(ActionRequest request, ActionResponse response) {
    Order order = request.getContext().asType(Order.class);
    response.setFlash(order.getOrderNo());
  }

  public void importCsvCountry(ActionRequest request, ActionResponse response) {

    Importer importer = new CSVImporter("./data/csv-config.xml");
    importer.run(
        new ImportTask() {
          @Override
          public void configure() throws IOException {
            input("[country]", new File("./data-demo/input/country3.csv"));
          }
        });
    response.setFlash("call csv import");
  }
  //	private File getDataCsvFile(MetaFile dataFile) {
  //
  //		File csvFile = null;
  //		try {
  //			File tempDir = Files.createTempDir();
  //			csvFile = new File(tempDir, "country3.csv");
  //
  //			Files.copy(MetaFiles.getPath(dataFile).toFile(), csvFile);
  //
  //		} catch (Exception e) {
  //			e.printStackTrace();
  //		}
  //		return csvFile;
  //	}

}
