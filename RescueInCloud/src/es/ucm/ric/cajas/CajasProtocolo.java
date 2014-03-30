/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.cajas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Mila
 */
public class CajasProtocolo {
    public static HashMap<Integer,CajaTexto> cajasProtocolo;
    
    public CajasProtocolo(){
        cajasProtocolo = new HashMap<Integer,CajaTexto>();
    }
    
    public void add(CajaTexto caja){
        cajasProtocolo.put(caja.getIdCajaTexto(), caja);
    }
    
    public HashMap<Integer,CajaTexto> getCajasProtocolo(){
        return this.cajasProtocolo;
    }
    
    public Set<Integer> getIds(){
        return this.cajasProtocolo.keySet();
    }
    public String toString(){
        String s="";
        for (CajaTexto value : cajasProtocolo.values()) {
            s+=value.toString();
            }
        return s;
    }

    public ArrayList<CajaTexto> getCajasDecision() {
        ArrayList<CajaTexto> decision = new ArrayList();
        for (CajaTexto value :  this.cajasProtocolo.values()){
            if (value.tipo.equals("decision")){
                decision.add(value);
            }
        }
        return decision;
    }
}
