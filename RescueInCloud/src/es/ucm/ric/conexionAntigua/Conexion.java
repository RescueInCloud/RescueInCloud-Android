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

import es.ucm.ric.cajas.CajaTexto;
import es.ucm.ric.cajas.CajasHijos;
import es.ucm.ric.cajas.CajasProtocolo;
import es.ucm.ric.model.Protocolo;

/**
 *
 * @author Mila
 */
public class Conexion {
    public String bd="mydb";
    public String login ="root";
    public String password="";
    public String url="jdbc:mysql://localhost/"+bd;
    private Protocolo protocolo = new Protocolo();
    private CajaTexto cajaTexto = new CajaTexto();
    
    Connection conn =null;
    
    public Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,login,password);
          /*  if (conn != null)
                JOptionPane.showMessageDialog(null, "Conexion a bbdd "+bd+" listo");
            else JOptionPane.showMessageDialog(null, "NO se ��ede conectar");*/
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
    
    public boolean consultarProtocolo(String user){
        user = "nombre_protocolo = '"+user+"'";
        Object[][] res=this.select("protocolos", "nombre_protocolo,email_usuario,creado_en",user);
        if (res.length >0){
            protocolo.setNombre(res[0][0].toString());
//            protocolo.setEmail_usuario(res[0][1].toString());
//            protocolo.setFecha(res[0][2].toString());
            
        }
        else return false;
        return true;
    }
    
    public boolean consultarCajaTexto(String user,CajasProtocolo cp){
        boolean exito =false;
        user = "Protocolos_nombre_protocolo = '"+user+"'";
        Object[][] res=this.select("CajaTexto", "idCajaTexto,tipo,subTipo,text",user);
        for(int i=0;i<res.length;i++){
            exito=true;
            cajaTexto.setIdCajaTexto(Integer.parseInt(res[i][0].toString()));
            cajaTexto.setTipo(res[i][1].toString());
            cajaTexto.setSubTipo(res[i][2].toString());
            cajaTexto.setText(res[i][3].toString());
            cp.add(cajaTexto);
            cajaTexto=new CajaTexto();
        }
        return exito;
    }

    public boolean consultarCajaHijo(String id,CajasHijos cp){
        boolean exito=false;
        String consulta = "CajaTexto_idCajaTexto = '"+id+"'";
        Object[][] res=this.select("cajatexto_has_hijo", "CajaTexto_idHijo , Relacion",consulta);
        for (int i=0;i<res.length;i++){
            exito=true;
           // int idint=Integer.parseInt(id);
            if (res[i][1] == null)
            	res[i][1]="";
            cp.insertarHijos(id, res[i][0].toString(),res[i][1].toString());
        }
        return exito;
    }    
   
  /* public void NuevaPersona(String name, String contra){
       try {            
            PreparedStatement pstm =this.getConnection().prepareStatement("insert into " + 
                    "tablausers(USUARIO,CONTRASE��A) " +
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
