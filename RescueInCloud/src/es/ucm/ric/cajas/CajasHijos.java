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
public class CajasHijos {
    private HashMap<Integer,HashMap<Integer,String>> hijos;
    
    public CajasHijos(){
        hijos=new HashMap<Integer,HashMap<Integer,String>>();
    }
    
    public HashMap<Integer,String> getHijos(int id){
        return this.hijos.get(id);
    }
    
    public void insertarHijos(int id,HashMap<Integer,String> hijos){
        this.hijos.put(id, hijos);
    }
    
    public void insertarHijos(int id, String lista){
        String[] idsS= lista.split(",");
        HashMap<Integer,String> ids = new  HashMap<Integer,String>();
        for(int i=0;i<idsS.length;i++){
            ids.put(Integer.parseInt(idsS[i]),"");
        }
        this.insertarHijos(id, ids);
    }
    public void insertarHijos(int id, int lista,String tipo){
    	HashMap<Integer,String> arr = this.getHijos(id);
        if (arr==null)
             arr = new  HashMap<Integer,String>();
        arr.put(lista,tipo);
        this.insertarHijos(id, arr);
    }
    
        public void insertarHijos(String id, String lista,String tipo){
            this.insertarHijos(Integer.parseInt(id), Integer.parseInt(lista),tipo);
    }
    
     public String toString(){
         String s="";
         Set<Integer> ids= this.hijos.keySet();
         for(int i: ids){
           s+=" "+i + " relacion: "+hijos.get(i)+"\n";  
         }
         return s;
    }
}
