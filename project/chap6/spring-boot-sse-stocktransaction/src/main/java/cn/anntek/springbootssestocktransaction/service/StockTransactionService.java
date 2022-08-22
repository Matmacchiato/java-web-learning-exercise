package cn.anntek.springbootssestocktransaction.service;


import cn.anntek.springbootssestocktransaction.model.Stock;
import cn.anntek.springbootssestocktransaction.model.StockTransaction;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

@Service
public class StockTransactionService {
    private final List<Stock> stockList=new ArrayList<>();
    private final List<String> stockName= Arrays.asList("石油,半导体,医疗,军工".split(","));

    public StockTransactionService(){
        stockName.forEach(stockName->{
            stockList.add(new Stock(stockName,generateRandomStockPrice()));
        });
    }

    //四舍五入（分）
    Float roundFloat(Float num){
        return Math.round(num*100)/100.0f;
    }
    //产生随机价格
    Float generateRandomStockPrice(){
        float min=30;
        float max=50;
        return min+roundFloat(new Random().nextFloat()*(max-min));
    }

    //修改价格（涨跌5%）
    float changePrice(Float price){
        return roundFloat(Math.random()>=0.5?price*1.05f:price*0.95f);
    }

    //获取随机用户
    String getRandomUser(){
        String users[]="郑豪,郑家豪,郭采洁,郑正奇".split(",");
        return users[new Random().nextInt(users.length)];
    }

    Stock getRandomStock(){
        return stockList.get(new Random().nextInt(stockList.size()));
    }
    public Flux<StockTransaction> getStockTransactions(){
        Flux<Long> interval=Flux.interval(Duration.ofSeconds(3));
        interval.subscribe((i)->stockList.forEach((stock -> stock.setPrice(changePrice(stock.getPrice())))));
        Flux<StockTransaction> stockTransactionFlux=Flux.fromStream(
                Stream.generate(()->new StockTransaction(getRandomUser(),getRandomStock(),new Date()))
        );
        return Flux.zip(interval,stockTransactionFlux).map(Tuple2::getT2);
    }
}
