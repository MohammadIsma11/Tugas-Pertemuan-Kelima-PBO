/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LatihanGui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ISMAIL
 */
public class MhsDb {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    
    public MhsDb (){
        try{
            Class.forName("org.postgresql.Driver");
                try{
                    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/uinsa","postgres","isma111004");
                }catch (SQLException ex){
                    Logger.getLogger(MhsDb.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Koneksi sukses");
        }catch (ClassNotFoundException ex){
            Logger.getLogger(MhsDb.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
    
    public void insertDB(String nim, String nama, String alamat){
        try {
            String sql = "insert into mahasiswa values(?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, nim);
            pst.setString(2, nama);
            pst.setString(3, alamat);
            pst.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(MhsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDb(String nim, String nama, String alamat){
        try {
            String sql = "update mahasiswa set nama=?, alamat=? where nim=?";
            pst = con.prepareStatement(sql);
            pst.setString(3, nim);
            pst.setString(1, nama);
            pst.setString(2, alamat);
            pst.executeUpdate();
        } catch (SQLException ex){
            Logger.getLogger(MhsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet selectDb(){
        try{
            String sql = "select * from mahasiswa";
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex){
            Logger.getLogger(MhsDb.class.getName()).log(Level.SEVERE, null,ex);
        }
        return rs;
    }
    
    public void deleteDb(String nim){
        try{
           String sql = "delete from mahasiswa where nim=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, nim);
        pst.executeUpdate(); 
        } catch (SQLException ex){
            Logger.getLogger(MhsDb.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
}
