/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplicationparsingxml;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.microedition.lcdui.*;
/**
 * @author ASUS
 */
public class MidletStatistique extends MIDlet implements CommandListener{
    Display disp = Display.getDisplay(this);
    
    int nbLocation=0;
    int nbVente=0;
    Offre[] alertes;
    Command cmdRectangle = new Command("Rectangle", Command.SCREEN, 1);
    Command cmdCercle = new Command("Cercle", Command.SCREEN, 1);
    private Form form =new Form("Statistique");
    public void startApp() {
        
        form.addCommand(cmdCercle);
        form.addCommand(cmdRectangle);
        form.setCommandListener(this);
        disp.setCurrent(form);
               
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
if(c==cmdRectangle)
{
  rectangle();

}
if(c==cmdCercle)
{cercle();}
    
    
    }
    public void rectangle()
    {
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
            alertes = personnesHandler.getOffre();

            if (alertes.length > 0) {
                
                for (int i = 0; i < alertes.length; i++) {
                     
                   if (alertes[i].getEngagement().trim().equals("vente"))
                   {
                      
                   nbVente++;
                   }
                   else
                   {nbLocation++;}
                }
                 
            }
            

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
       
         MyCanvas1 canv=new MyCanvas1(nbVente,nbLocation,alertes.length);
              canv.repaint();
              disp.setCurrent(canv);
    
    }
    
    
     public void cercle()
    {
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
            alertes = personnesHandler.getOffre();

            if (alertes.length > 0) {
                
                for (int i = 0; i < alertes.length; i++) {
                     
                   if (alertes[i].getEngagement().trim().equals("vente"))
                   {
                       System.out.println("vente");
                   nbVente++;
                   }
                   else
                   {nbLocation++;System.out.println("location");}
                }
                 
            }
            

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
       
             MyCanvas2 canv=new MyCanvas2(nbVente,nbLocation,alertes.length);
              canv.repaint();
              disp.setCurrent(canv);
    
    }

   
    
    
}
