/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.dao.classes;

import edu.esprit.pidev.dao.interfaces.IDepotDao;
import edu.esprit.pidev.technique.DataSource;
import edu.esprit.pidev.entities.Depot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehdi
 */
public class DepotDao implements IDepotDao {

    private Connection connection;

    public DepotDao() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void addDepot(Depot depot) {
        try {
            String req = "insert into depot (adresse_depot) values (?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, depot.getAdresse_depot());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DepotDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateDepot(Depot depot) {
        String requete = "update depot set adresse_depot=? where id_depot=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, depot.getAdresse_depot());
            ps.setInt(2, depot.getId_depot());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void removeDepotById(int id) {
        String requete = "delete from depot where id_depot=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Depot supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void removeDepotByAdresse(String adresseDepot) {
        String requete = "delete from depot where adresse_depot=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, adresseDepot);
            ps.executeUpdate();
            System.out.println("Pays supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public Depot findDepotById(int id) {
        Depot depot = new Depot();
        String requete = "select * from depot where id_depot=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                depot.setId_depot(resultat.getInt(1));
                depot.setAdresse_depot(resultat.getString(2));
            }
            return depot;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Depot findDepotByAdresse(String adresse) {
        Depot depot = new Depot();
        String requete = "select * from depot where adresse_depot=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, adresse);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                depot.setId_depot(resultat.getInt(1));
                depot.setAdresse_depot(resultat.getString(2));
            }
            return depot;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Depot> findAll() {
        List<Depot> listedepots = new ArrayList<>();
        String requete = "select * from depot";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Depot depot = new Depot();
                depot.setId_depot(resultat.getInt(1));
                depot.setAdresse_depot(resultat.getString(2));
                listedepots.add(depot);
            }
            return listedepots;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
}
