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

public class MidletDeleteAlerte extends MIDlet implements CommandListener, Runnable{

    Display disp = Display.getDisplay(this);
    //Form 1
    Form f1 = new Form("suppression");
    List tfNom = new List("id:", List.IMPLICIT);
    List tfId = new List("", List.IMPLICIT);
    Command cmdValider = new Command("supprimer", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);

    Form f2 = new Form("Welcome");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1:84/parseAgence/delete.php";
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
           Alerte[] alertes = personnesHandler.getAlerte();

            if (alertes.length > 0) {
                for (int i = 0; i < alertes.length; i++) {
                  tfNom.append(
                           alertes[i].getPrixMax()+" "+alertes[i].getRegion()
                          +" "+alertes[i].getEngagement()+" "+alertes[i].getTypedeBien()+" "+alertes[i].getSurface(), null);
tfId.append(Integer.toString(alertes[i].getId()), null);

        
        
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
                hc = (HttpConnection) Connector.open(url+"?PrixMin="+tfId.getString(tfNom.getSelectedIndex()));
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
