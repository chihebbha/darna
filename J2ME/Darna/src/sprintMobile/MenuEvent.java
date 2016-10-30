package sprintMobile;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import net.sourceforge.mewt.button.ImageButton;
import net.sourceforge.mewt.button.TextButton;
import net.sourceforge.mewt.iform.IFormCanvas;
import net.sourceforge.mewt.iform.IFormListener;


public class MenuEvent implements CommandListener, IFormListener {

//    static Utilisateur clientConnecté = null;
    Form authentifForm = new Form("authentification");
    TextField pseudo = new TextField("pseudo", null, 30, TextField.ANY);
    TextField motDePasse = new TextField("mot de passe", null, 30, TextField.PASSWORD);
    Command cmOk = new Command("valider", Command.SCREEN, 0);
    Command insciption = new Command("S'inscrire", Command.BACK, 0);
    private Display display;

    
    
     private Ticker tk;

    
  
    IFormCanvas menuList = new IFormCanvas(3, 3);
    TextButton proposer = new TextButton("Agence", "/icons/agence.png");
    TextButton sinscrir = new TextButton("Alerte", "/icons/alerte.png");
    TextButton photos = new TextButton("Facture","/icons/facture.png");
    TextButton videos = new TextButton("Offres", "/icons/offre.png");
    TextButton recherche = new TextButton("Personnes","/icons/personne.png");
    TextButton ev = new TextButton("Trasporteur","/icons/transporteur.png");
     TextButton stat = new TextButton("Statistique","/icons/statistique.png");

    TextButton exit = new TextButton("Exit", "/icons/14.png");
    public MenuEvent( Display display) {

          this.display = display;
     
        menuList.setTitle("Menu Events");
    
        menuList.addItem(proposer);
        menuList.addItem(sinscrir);
        menuList.addItem(recherche);
        menuList.addItem(stat);
        menuList.addItem(photos);
        menuList.addItem(ev);
        
        menuList.addItem(videos);
         menuList.addItem(exit);
       // menuList.addItem(exit);
        menuList.setCommandListener(this);
        menuList.addPopperListener(this);
        this.display.setCurrent(menuList);
        display.setCurrent( menuList);
    }
    
   


    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void selected(ImageButton ib) {

    }

  
public void clicked(ImageButton ib) {
        if (ib == proposer) {
         //   new ProposerUnEvent(display);
        }
        
         if (ib == sinscrir) {
         new MidletParsingAlerte1(display);
        }
           if (ib == recherche) {
         //   new RechercheEvent(display);
           }
           
            if (ib == stat) {
            new MidletStatistique(display);
            }
            
            if (ib == ev) {
       // new myEvents(display);
    }
            if (ib == photos) {
 new MidletParsingXmlFacture1(display); 
            }
           if (ib == videos) {
      //new Diaporama(display);
    }
           
           
    }}
           
        
           
        
         
       
    
    
   
