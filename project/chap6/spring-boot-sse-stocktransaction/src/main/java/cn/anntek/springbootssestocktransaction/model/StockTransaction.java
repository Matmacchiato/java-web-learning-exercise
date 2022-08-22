package cn.anntek.springbootssestocktransaction.model;

import java.util.Date;

public class StockTransaction {
    private String user;
    private  Stock stock;
    private Date when;

    public StockTransaction(){

    }

    public StockTransaction(String user, Stock stock, Date when) {
        this.user = user;
        this.stock = stock;
        this.when = when;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}
