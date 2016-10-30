/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.graphics;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import static javax.print.attribute.Size2DSyntax.MM;

/**
 *
 * @author Khalil
 */
public class DateModele {
    
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    public String getFetcha(JDateChooser  jd)
    {
        if(jd.getDate()!=null)
        {
            return format.format(jd.getDate());
            
        }else
        {
            return null;
        }
    }
    
    
    
}
