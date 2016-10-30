package sprintMobile;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import sprintMobile.MenuEvent;


public class MidletEvent extends MIDlet  {

    Display display = Display.getDisplay(this);
 
    Alert sp = new Alert("Welcome");
    public static MIDlet midlet;

   

    public void startApp() {
       
   MenuEvent m=new MenuEvent( display);
      // Acceuil acceuil =new Acceuil(sp, display);
        System.out.println("Menu");
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
