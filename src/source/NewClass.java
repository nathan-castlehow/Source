/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Natus
 */
public class NewClass {
    private SimpleStringProperty stockTicker;

    public NewClass(String stockTicker) {
        this.stockTicker = new SimpleStringProperty(stockTicker);
    }

    public String getStockTicker() {
        return stockTicker.get();
    }

    public void setStockTicker(String stockticker) {
        stockTicker.set(stockticker);
    }

    public StringProperty stockTickerProperty() {
        return stockTicker;
    }
 
}
