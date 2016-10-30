/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.adapters;

import edu.esprit.pidev.dao.classes.StockDAO;
import edu.esprit.pidev.dao.interfaces.IStockDAO;
import edu.esprit.pidev.entities.Stock;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mehdi
 */
public class StockAdapter extends AbstractTableModel {

    String[] headers = {"type_vet", "nombre d'articles", "adresse depot"};
    List<Stock> stocks = new ArrayList<>();
    IStockDAO stockDAO;

    public StockAdapter() {
        stockDAO = new StockDAO();
        stocks = stockDAO.DisplayAllStocks();
    }

    @Override
    public int getRowCount() {
        return stocks.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int i) {
        return headers[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int colmunIndex) {
        switch (colmunIndex) {
            case 0:
                return stocks.get(rowIndex).getType_vet_stock();
            case 1:
                return stocks.get(rowIndex).getNombre_articles();
            case 2:
                return stocks.get(rowIndex).getDepot().getAdresse_depot();
            default:
                return null;
        }
    }

}
