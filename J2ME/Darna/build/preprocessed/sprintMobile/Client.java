/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

/**
 *
 * @author ASUS
 */
public class Client extends Personne{
    private String adresse;

    public Client(String adresse, String password, String email, String nom, String prenom, String date, int tel) {
        super(password, email, nom, prenom, date, tel);
        this.adresse = adresse;
    }

    public Client() {

    }
    

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    
    
    
    
    
}
