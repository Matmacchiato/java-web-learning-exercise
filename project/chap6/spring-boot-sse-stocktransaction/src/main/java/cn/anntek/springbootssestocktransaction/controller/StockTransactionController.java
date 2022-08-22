package cn.anntek.springbootssestocktransaction.controller;

import cn.anntek.springbootssestocktransaction.model.StockTransaction;
import cn.anntek.springbootssestocktransaction.service.StockTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/stock/transaction")
public class StockTransactionController {
    @Autowired
    StockTransactionService stockTransactionService;

    @GetMapping(value = "",produces = "text/event-stream")
    public Flux<StockTransaction> stockTransactionFlux(){
        return stockTransactionService.getStockTransactions();
    }
}
