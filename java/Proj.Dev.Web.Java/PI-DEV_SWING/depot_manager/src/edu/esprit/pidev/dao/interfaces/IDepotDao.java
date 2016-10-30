/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.dao.interfaces;

import edu.esprit.pidev.entities.Depot;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface IDepotDao {

    void addDepot(Depot depot);

    void updateDepot(Depot depot);

    void removeDepotByAdresse(String adresse);

    void removeDepotById(int id);

    List<Depot> findAll();

    Depot findDepotById(int id);

    Depot findDepotByAdresse(String adresse);

}
