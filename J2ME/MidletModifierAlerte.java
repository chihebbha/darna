/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplicationparsingxml;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MidletModifierAlerte extends MIDlet implements CommandListener, Runnable{

    Display disp = Display.getDisplay(this);
    Alerte[] alertes2;
    Form fModif = new Form("Inscription");
    TextField prixMin = new TextField("prixMin", null, 100, TextField.NUMERIC);
    TextField prixMax = new TextField("prixMax", null, 100, TextField.NUMERIC);
        TextField region = new TextField("region", null, 100, TextField.ANY);
   // TextField engagement = new TextField("engagement", null, 100, TextField.ANY);
    //TextField typedeBien = new TextField("typedeBien", null, 100, TextField.ANY);
    TextField surface = new TextField("surface", null, 100, TextField.NUMERIC);
    String[] tab={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
ChoiceGroup tfClient;
ChoiceGroup tfOffre;

String[] tabEngagement={"location","vente"};
String[] tabType={"Entreprise","Logement","Terrain"};
 ChoiceGroup engagement=new ChoiceGroup("Engagement",ChoiceGroup.POPUP,tabEngagement,null);
  ChoiceGroup typedeBien=new ChoiceGroup("Type",ChoiceGroup.POPUP,tabType,null);
    
    
    
    
    //Form 1
    Form f1 = new Form("modifier");
    List tfNom = new List("id:", List.IMPLICIT);
    Command cmdValider = new Command("modifier", Command.SCREEN, 0);
    Command cmdUpdate = new Command("update", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);
List tfId = new List("", List.IMPLICIT);
    Form f2 = new Form("Welcome");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1:84/parseAgence/update.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public void startApp() {
        tfNom.addCommand(cmdValider);
        tfNom.addCommand(cmdBack);
        tfNom.setCommandListener(this);
        disp.setCurrent(tfNom);
        
        
        
        
                try {
            // this will handle our XML
            AlerteHandler personnesHandler = new AlerteHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:84/parseAgence/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
           alertes2 = personnesHandler.getAlerte();

            if (alertes2.length > 0) {
                for (int i = 0; i < alertes2.length; i++) {
                  tfNom.append(alertes2[i].getId()+""
                           +alertes2[i].getPrixMax()+" "+alertes2[i].getRegion()
                          +" "+alertes2[i].getEngagement()+" "+alertes2[i].getTypedeBien()+" "+alertes2[i].getSurface(), null);
tfId.append(Integer.toString(alertes2[i].getId()), null);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
       // disp.setCurrent(tfNom);
        
        
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        Thread th = new Thread(this);
        if (c == cmdValider) {
           
            this.modifier();
        }
        if (c == cmdBack) {
            
            disp.setCurrent(f1);
        }
        if(c==cmdUpdate){
         
            th.start();
        }
    }

    public void run() {
        
        
        
        
        
        
        try {
                hc = (HttpConnection) Connector.open(url+"?PrixMin="+tfNom.getString(ch));
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(f2);
                }else{
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    
    
    
    public void modifier()
            
    {
      try {
                  
            // this will handle our XML
            ClientHandler personnesHandler = new ClientHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:84/parsePers/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
           Personne[] alertes = personnesHandler.getPersonne();
 
            if (alertes.length > 0) {
                tab=new String[alertes.length];
             
                for (int i = 0; i < alertes.length; i++) {
                     tab[i]=alertes[i].getNom();
                     
                }
                  
              
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
               
                   try {
                  
            // this will handle our XML
            OffreHandler personnesHandler = new OffreHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:84/parsePers/getXmlPersons_Attributes1.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
           Offre[] alertes = personnesHandler.getOffre();
 
            if (alertes.length > 0) {
                tabOffre=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tabOffre[i]=alertes[i].getDescription();
                     
                }
                 
              
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
           prixMin.setString(Double.toString(alertes2[tfNom.getSelectedIndex()].getPrixMin()));
                      prixMax.setString(Double.toString(alertes2[tfNom.getSelectedIndex()].getPrixMax()));
region.setString(alertes2[tfNom.getSelectedIndex()].getRegion());
surface.setString(Double.toString(alertes2[tfNom.getSelectedIndex()].getSurface()));

fModif.append(prixMin);
        fModif.append(prixMax);
        fModif.append(region);
        fModif.append(engagement);
        fModif.append(typedeBien);
        fModif.append(surface);
         tfClient=new ChoiceGroup("Client",ChoiceGroup.POPUP,tab,null);
               fModif.append(tfClient);
                tfOffre=new ChoiceGroup("Offre",ChoiceGroup.POPUP,tabOffre,null);
               fModif.append(tfOffre);
        fModif.addCommand(cmdValider);
        fModif.setCommandListener(this);
        fModif.addCommand(cmdBack);
        fModif.setCommandListener(this);
    disp.setCurrent(fModif);
    }
}
