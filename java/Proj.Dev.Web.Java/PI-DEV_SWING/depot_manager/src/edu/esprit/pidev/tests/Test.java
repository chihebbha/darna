/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.dao.classes.DepotDao;
import edu.esprit.pidev.dao.classes.StockDAO;
import edu.esprit.pidev.dao.interfaces.IDepotDao;
import edu.esprit.pidev.dao.interfaces.IStockDAO;
import edu.esprit.pidev.entities.Depot;
import edu.esprit.pidev.entities.Stock;

/**
 *
 * @author Mehdi
 */
public class Test {

    public static void main(String[] args) {
        IDepotDao depotDao = new DepotDao();
        Depot d = depotDao.findDepotById(1);
        Stock st = new Stock("Enfant", 10, d);
        IStockDAO stockDAO = new StockDAO();
        stockDAO.insertStock(st);
    }
}
