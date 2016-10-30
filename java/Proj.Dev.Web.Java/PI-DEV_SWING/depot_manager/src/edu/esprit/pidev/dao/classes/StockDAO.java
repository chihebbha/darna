/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.dao.classes;

import edu.esprit.pidev.dao.interfaces.IDepotDao;
import edu.esprit.pidev.dao.interfaces.IStockDAO;
import edu.esprit.pidev.entities.Stock;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karray
 */
public class StockDAO implements IStockDAO {

    private Connection connection;

    public StockDAO() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void insertStock(Stock st) {

        String requete = "insert into stock (type_vet_stock,nombre_articles,id_depot) values (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, st.getType_vet_stock());
            ps.setInt(2, st.getNombre_articles());
            ps.setInt(3, st.getDepot().getId_depot());
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateStock(Stock st) {
        String requete = "update stock set type_vet_stock=?, nombre_articles=?, id_depot=? where numero_stock=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, st.getType_vet_stock());
            ps.setInt(2, st.getNombre_articles());
            ps.setInt(3, st.getDepot().getId_depot());
            ps.setInt(4, st.getNumero_stock());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteSotck(int id) {

        String requete = "delete from stock where numero_stock=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public Stock findStockByNum(int num) {

        String requete = "select * from stock where numero_stock=?";

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            IDepotDao depotDao = new DepotDao();
            Stock stock = new Stock();
            while (resultat.next()) {

                stock.setNumero_stock(resultat.getInt(1));
                stock.setType_vet_stock(resultat.getString(2));
                stock.setNombre_articles(resultat.getInt(3));
                stock.setDepot(depotDao.findDepotById(resultat.getInt(4)));
            }
            return stock;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Stock> DisplayAllStocks() {

        List<Stock> listedepots = new ArrayList<>();
        String requete = "select * from stock";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            IDepotDao depotDAO = new DepotDao();
            while (resultat.next()) {
                Stock stock = new Stock();
                stock.setNumero_stock(resultat.getInt(1));
                stock.setType_vet_stock(resultat.getString(2));
                stock.setNombre_articles(resultat.getInt(3));
                stock.setDepot(depotDAO.findDepotById(resultat.getInt(4)));

                listedepots.add(stock);
            }
            return listedepots;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }
}
