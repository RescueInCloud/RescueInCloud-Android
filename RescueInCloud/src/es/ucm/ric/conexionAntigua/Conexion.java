/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.ric.conexionAntigua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.ucm.ric.model.CajaTexto;
import es.ucm.ric.model.CajasHijos;
import es.ucm.ric.model.Protocolo;
import es.ucm.ric.model.Tupla;

/**
 *
 * @author Mila
 */
public class Conexion {
    public String bd="mydb";
    public String login ="root";
    public String password="";
    public String url="jdbc:mysql://localhost/"+bd;
    //private Protocolo protocolo;
   // private CajaTexto cajaTexto;
    
    Connection conn =null;
    
    public Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,login,password);
/*            if (conn != null)
                JOptionPane.showMessageDialog(null, "Conexion a bbdd "+bd+" listo");
            else JOptionPane.showMessageDialog(null, "NO se ùede conectar");*/
        }
        catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void desconectar(){
        conn= null;
    }
    
    /** COGER DATOS DE LA BBDD**/
    public Object [][] select(String table, String fields, String where){
        int registros=0;
        String colname[]=fields.split(",");
        
        //COnsultas sql
        String q="SELECT "+ fields+" FROM "+table;
        String q2="SELECT count(*) as total FROM "+table;
        
        if (where != null){
            q += " WHERE "+where;
            q2 += " WHERE "+where;
        }
        try{
            //prepara una consulta parea luego lanzarla
            PreparedStatement pstm=conn.prepareStatement(q2);
            ResultSet res=pstm.executeQuery();
            res.next();
            registros =res.getInt("total");
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
       Object[][] data= new String[registros][fields.split(",").length];
        try{
           Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            int i=0;
        while (rs.next()) {
             for(int j=0;j<=fields.split(",").length-1;j++){
                    data[i][j] =rs.getString(colname[j].trim());
                }
            i++;
        }
            stmt.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return data;
    }
    
    public Protocolo consultarProtocolo(String user){
        user = "nombre_protocolo = '"+user+"'";
        Object[][] res=this.select("protocolos", "nombre_protocolo,email_usuario,creado_en",user);
       
        Protocolo protocolo = null;
        if (res.length >0){
        	protocolo = new Protocolo(1,res[0][0].toString());
        }
        return protocolo;
    }
    
    public boolean consultarCajaTexto(String user,Protocolo protocolo){
        boolean exito =false;
        user = "Protocolos_nombre_protocolo = '"+user+"'";
        Object[][] res=this.select("CajaTexto", "idCajaTexto,tipo,subTipo,text",user);
        for(int i=0;i<res.length;i++){
            exito=true;
            CajaTexto cajaTexto = new CajaTexto(Integer.parseInt(res[i][0].toString()),1,res[i][1].toString(),res[i][3].toString());
            protocolo.anyadirCaja(cajaTexto);
        }
        return exito;
    }

    
    public boolean consultarCajaHijo(String id,Protocolo protocolo){
        boolean exito=false;
        String consulta = "CajaTexto_idCajaTexto = '"+id+"'";
        Object[][] res=this.select("cajatexto_has_hijo", "CajaTexto_idHijo , Relacion",consulta);
        CajasHijos cajasHijos = new CajasHijos();
        for (int i=0;i<res.length;i++){
            exito=true;
           // int idint=Integer.parseInt(id);
            if (res[i][1] == null)
            	res[i][1]="";
            Tupla tupla=new Tupla();
            tupla.id=Integer.parseInt(res[i][0].toString());
            tupla.relacion=res[i][1].toString();
            cajasHijos.addHijo(Integer.parseInt(id), tupla);
        }
        protocolo.setHijos(cajasHijos);
        return exito;
    }    
   
  /* public void NuevaPersona(String name, String contra){
       try {            
            PreparedStatement pstm =this.getConnection().prepareStatement("insert into " + 
                    "tablausers(USUARIO,CONTRASEÑA) " +
                    " values(?,?)"); 
            pstm.setString(1, name);
            pstm.setString(2, contra);                      
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "usuario"+" "+name+" "+"creado correctamente");
            
         }catch(SQLException e){
         System.out.println(e);
      }
   }*/

}
