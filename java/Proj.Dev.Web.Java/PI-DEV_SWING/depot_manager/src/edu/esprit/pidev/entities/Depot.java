/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.entities;

/**
 *
 * @author Mehdi
 */
public class Depot {
    
    private int id_depot;
    private String adresse_depot;

    public Depot() {
    }

    public Depot(String adresse_depot) {
        this.adresse_depot = adresse_depot;
    }

    public int getId_depot() {
        return id_depot;
    }

    public String getAdresse_depot() {
        return adresse_depot;
    }

    public void setId_depot(int id_depot) {
        this.id_depot = id_depot;
    }

    public void setAdresse_depot(String adresse_depot) {
        this.adresse_depot = adresse_depot;
    }

    @Override
    public String toString() {
        return "Depot{" + "id_depot=" + id_depot + ", adresse_depot=" + adresse_depot + '}';
    }
    
    
}
