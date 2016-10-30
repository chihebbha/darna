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

public class MidletDeleteFacture extends MIDlet implements CommandListener, Runnable{

    Display disp = Display.getDisplay(this);
    //Form 1
    Form f1 = new Form("suppression");
    List tfNom = new List("id:", List.IMPLICIT);
    Command cmdValider = new Command("supprimer", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);

    Form f2 = new Form("Welcome");
    Form f3 = new Form("Erreur");
List tfId = new List("", List.IMPLICIT);
    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);
 String[] tabGerant={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabClient={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1:84/parseFacture/delete.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public void startApp() {
        tfNom.addCommand(cmdValider);
        tfNom.addCommand(cmdBack);
        tfNom.setCommandListener(this);
        disp.setCurrent(tfNom);
        
        
        
        
        
        
        
        
        
                try {
            // this will handle our XML
            FactureHandler factureHandler = new FactureHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:84/parseFacture/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, factureHandler);
            // display the result
           Facture[] factures = factureHandler.getFacture();

            if (factures.length > 0) {
                for (int i = 0; i < factures.length; i++) {
                  tfNom.append("Date : "+factures[i].getDate()+"\n Client : "+factures[i].getClient().getId()+"\n Gerant :  "+factures[i].getGerant().getId()+"\n Offre :  "+factures[i].getOffre().getId(), null);
tfId.append(Integer.toString(factures[i].getId()), null);
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
        if (c == cmdValider) {
            Thread th = new Thread(this);
            th.start();
        }
        if (c == cmdBack) {
            
            disp.setCurrent(f1);
        }
    }

    public void run() {
        try {
                hc = (HttpConnection) Connector.open(url+"?idFacture="+tfId.getString(tfNom.getSelectedIndex()));
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
}
