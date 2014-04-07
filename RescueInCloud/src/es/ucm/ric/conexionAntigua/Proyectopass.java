/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.ric.conexionAntigua;

import java.util.ArrayList;
import java.util.Scanner;

import es.ucm.ric.model.Protocolo;
import es.ucm.ric.parser.TextInterpreter;
import es.ucm.ric.parser.TextParser;

public class Proyectopass {

    public static void main(String[] args) {
    	/*nombre= manejo de la hipotermia accidental severa*/
    	/*
    	CajasProtocolo cp;  
    	CajasHijos ch;*/
    	Protocolo protocolo = null;
    	Scanner sc = new Scanner(System.in);
    	Conexion con;
    	con = new Conexion();
    	System.out.println("Introduce elnombre del protocolo");
    	String user=sc.nextLine();
    	protocolo=con.consultarProtocolo(user);
        if(protocolo != null){   
            if (con.consultarCajaTexto(user,protocolo)){
            	System.out.println("--------------------------");
            	System.out.println("Las cajas de texto que forman el protocolo son:");
            	System.out.println(protocolo.cajas_toString());
            	System.out.println("--------------------------");
            	System.out.println("Sus tipos son:");
            	TextParser tp = new TextParser();
                ArrayList<TextInterpreter> arr_textParser= tp.parseCaja(protocolo.cajas_toString());
               for (TextInterpreter t:arr_textParser){
            	   System.out.println(t.toString());
               }
               //obtenemos cuales son las cajas de decision.. CAMBIAR:estaria bien separarlas antes del parser
               //y ya no seria arrayList de cajaTexto si no del parser
         /*      ArrayList<CajaTexto> decision = cp.getCajasDecision();
               System.out.println("--------------------------");
           		System.out.println("Las cajas de texto que son de decision son:");
           		System.out.println(decision.toString());
               /*consulta sus hijos*/
 /*              Set<Integer> ids = cp.getIds();
               ch = new CajasHijos();
              
               for (int id:ids){
                   con.consultarCajaHijo(String.valueOf(id),ch);
                       
                   
                   }
               System.out.println("--------------------------");
          		System.out.println("Las relaciones de parentesco de las cajas son:");
          		System.out.println(ch.toString());
            }
               
            
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Datos Incorrectos");
        }   
                      
           
   }//GEN-LAST:event_buttonAction1ActionPerformed
    */
}
        }
    }
}
