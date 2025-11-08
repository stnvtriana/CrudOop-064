/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author siti novi triana
 */
public class product extends koneksi{
    private int productId;
    private String productName;
    private int productCategory;
    private String productDescription;
    private int productPrice;
    private  Connection koneksi; 
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public product (){
        koneksi = super.configDB();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public String getproductDescription() {
        return productDescription;
    }

    public void setproductDescription(String description) {
        this.productDescription = description;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    

   
    
    public void TambahProduct(){
        query = "INSERT INTO product (productId, productName, productCategory, productDescription, productPrice) VALUES (?,?,?,?,?)";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setString(2, productName);
            ps.setInt(3, productCategory);
            ps.setString(4, productDescription);
            ps.setInt(5, productPrice);
            
            
           ps.executeUpdate();
           ps.close();//untuk menutup koneksi
           JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException sQLException) {
             JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan" + sQLException.getMessage());
        }
    }
    
    public void UbahProduct() {
             query = "UPDATE product SET productName=?,productCategory=?,productDescription=?,productPrice=? WHERE productId=?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, productName);
            ps.setInt(2, productCategory);
            ps.setString(3, productDescription);
            ps.setInt(4, productPrice);
            ps.setInt(5, productId);
           
           ps.executeUpdate();
           ps.close();//untuk menutup koneksi
           JOptionPane.showMessageDialog(null, "Data Berhasil DiUbah");
        } catch (SQLException sQLException) {
             JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + sQLException.getMessage());
        }
     
    }
    
    public void HapusProduct(){
        query = "DELETE FROM product WHERE productId = ?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, productId);
           
           ps.executeUpdate();
           ps.close();//untuk menutup koneksi
           JOptionPane.showMessageDialog(null, "Data Berhasil DiHapus");
        } catch (SQLException sQLException) {
             JOptionPane.showMessageDialog(null, "Data Gagal DiHapus" + sQLException.getMessage());
        }
    }
    
    public ResultSet tampilProduct(){
       query = "SELECT p.productId, p.productName, c.categoryId, p.productDescription, p.productPrice "
          + "FROM product p LEFT JOIN category c ON p.productCategory = c.categoryId";
         try {
            st = koneksi.createStatement();
            rs= st.executeQuery(query);
           
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + e.getMessage());
        
    }
         return rs;
    }
}
