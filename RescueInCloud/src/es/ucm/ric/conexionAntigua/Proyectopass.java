/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.ric.conexionAntigua;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import es.ucm.ric.cajas.CajasHijos;
import es.ucm.ric.cajas.CajasProtocolo;
import es.ucm.ric.model.CajaTexto;
import es.ucm.ric.parser.TextInterpreter;
import es.ucm.ric.parser.TextParser;

public class Proyectopass {

  /*  public static void main(String[] args) {
    	/*nombre= manejo de la hipotermia accidental severa*/
  /*  	Conexion con;
    	CajasProtocolo cp;  
    	CajasHijos ch;
    	Scanner sc = new Scanner(System.in);
    	con = new Conexion();
    	System.out.println("Introduce elnombre del protocolo");
    	String user=sc.nextLine();
        if(con.consultarProtocolo(user)){   
            /*consulta y muestra el nombre del protocolo si existe*/
            /*crea un hashMap de todas las cajas de texto que coontiene un protocolo, 
            con la clave el id de la caja y el contenido la caja en si (class CajaTexto)
            */
  /*          cp= new CajasProtocolo();
            /*a��ade las cajas al hashmap y las muestra.
            Parsea el contenido de las cajas creando nuevos objetos con los que trabajar�� Android*/
 /*           if (con.consultarCajaTexto(user,cp)){
            	System.out.println("--------------------------");
            	System.out.println("Las cajas de texto que forman el protocolo son:");
            	System.out.println(cp.toString());
            	System.out.println("--------------------------");
            	System.out.println("Sus tipos son:");
            	TextParser tp = new TextParser();
                ArrayList<TextInterpreter> arr_textParser= tp.parseCaja(cp.toString());
               for (TextInterpreter t:arr_textParser){
            	   System.out.println(t.toString());
               }
               //obtenemos cuales son las cajas de decision.. CAMBIAR:estaria bien separarlas antes del parser
               //y ya no seria arrayList de cajaTexto si no del parser
               ArrayList<CajaTexto> decision = cp.getCajasDecision();
               System.out.println("--------------------------");
           		System.out.println("Las cajas de texto que son de decision son:");
           		System.out.println(decision.toString());
               /*consulta sus hijos*/
    /*           Set<Integer> ids = cp.getIds();
               ch = new CajasHijos();
              
               for (int id:ids){
                   con.consultarCajaHijo(String.valueOf(id),ch);
                       
                   
                   }
               System.out.println("--------------------------");
          		System.out.println("Las relaciones de parentesco de las cajas son:");
          		System.out.println(ch.toString());
            }
               
            
            
        }
       /* else{
            JOptionPane.showMessageDialog(null, "Datos Incorrectos");
        }   */
                      
           
  // }//GEN-LAST:event_buttonAction1ActionPerformed
    
}
