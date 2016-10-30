/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.dao.interfaces;

import edu.esprit.pidev.entities.Stock;
import java.util.List;

/**
 *
 * @author mahdi
 */
public interface IStockDAO {

    void insertStock(Stock stock);

    void updateStock(Stock stock);

    void deleteSotck(int id);

    Stock findStockByNum(int num);

    List<Stock> DisplayAllStocks();
}
