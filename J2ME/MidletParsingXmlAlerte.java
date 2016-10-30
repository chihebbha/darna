/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplicationparsingxml;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Houssem Eddine
 */
public class MidletParsingXmlAlerte extends MIDlet implements CommandListener, Runnable {

    Display disp = Display.getDisplay(this);
    Command cmdParse = new Command("Alertes", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Alerte[] alertes;
    List lst = new List("Alertes", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Infos Alertes");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();

    public void startApp() {
        f.append("Click Alertes to get your alertes_list");
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.setCommandListener(this);
        disp.setCurrent(f);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

        if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Alertes : \n");
            form.append(showPersonne(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }

    }

    public void run() {
        try {
            // this will handle our XML
            AlerteHandler alertesHandler = new AlerteHandler();
            // get a parser object
            
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:84/parseAgence/getXmlPersons_Attributes.php");

            DataInputStream dis = new DataInputStream(hc.openDataInputStream());

            parser.parse(dis, alertesHandler);

            // display the result
            alertes = alertesHandler.getAlerte();
           // System.out.println(alertes[0].getPrixMax()+"prix"+alertes[1].getEngagement());
            
            if (alertes.length > 0) {
                for (int i = 0; i < alertes.length; i++) {
                  lst.append(alertes[i].getId()+" "
                            +alertes[i].getPrixMin()+" "
                            +alertes[i].getPrixMax()+" "
                            +alertes[i].getRegion()+" "
                            +alertes[i].getEngagement()+" "
                            +alertes[i].getTypedeBien()+" "
                            +alertes[i].getSurface()+" "
                            +alertes[i].getClient().getId()+" "
                            +alertes[i].getOffre().getId()+" "
                            , null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception1:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showPersonne(int i) {
        String res = "";
        if (alertes.length > 0) {
           
            sb.append("* Prix min: ");
            sb.append(alertes[i].getPrixMin());
            sb.append("\n");
            sb.append("* Prix max: ");
            sb.append(alertes[i].getPrixMax());
            sb.append("\n");
             sb.append("* Region : ");
            sb.append(alertes[i].getRegion());
            sb.append("\n");
             sb.append("* Engagement : ");
             if(alertes[i].getEngagement().equals("l"))
            sb.append("location");
              if(alertes[i].getEngagement().equals("v"))
            sb.append("vente");
            sb.append("\n");
             sb.append("* Type : ");
             if(alertes[i].getTypedeBien().equals("l"))
            sb.append("logement");
             if(alertes[i].getTypedeBien().equals("e"))
            sb.append("entreprise");
             if(alertes[i].getTypedeBien().equals("t"))
            sb.append("terrain");
            sb.append("\n");
             sb.append("* Surface : ");
            sb.append(alertes[i].getSurface());
            sb.append("\n");
             sb.append("* ");
           
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
