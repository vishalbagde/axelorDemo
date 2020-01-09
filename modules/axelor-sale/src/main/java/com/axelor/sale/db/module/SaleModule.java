package com.axelor.sale.db.module;

import com.axelor.app.AxelorModule;
import com.axelor.sale.db.repo.OrderRepository;
import com.axelor.sale.db.repo.SaleOrderRepo;
import com.axelor.sale.db.service.OrderService;
import com.axelor.sale.db.service.OrderServiceImpl;

public class SaleModule extends AxelorModule {

  @Override
  protected void configure() {

    bind(OrderRepository.class).to(SaleOrderRepo.class);
    bind(OrderService.class).to(OrderServiceImpl.class);
  }
}
