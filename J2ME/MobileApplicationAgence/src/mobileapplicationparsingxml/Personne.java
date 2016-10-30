/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplicationparsingxml;

/**
 *
 * @author ASUS
 */
public class Personne {
   private int id;
    private String password;
    private String email;
    private String nom;
    private String prenom;
    private String date;
    private int tel;

    public Personne() {
    }

    
    
    public Personne(String password, String email, String nom, String prenom, String date, int tel) {
        
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDate() {
        return date;
    }

    public int getTel() {
        return tel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
    
    
}
