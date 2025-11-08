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
public class user extends koneksi {

    private String userName, userEmail, userPassword, userFullName;
    private int userStatus;
    private final Connection koneksi; // fungsi final untuk diberi nilai 1 kali yng pertama jika diberi nilai lagi, nilai yang akhir tidak di anggap
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;

    public user() {
        koneksi = super.configDB();

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public void TambahUser() {
        query = "INSERT INTO user VALUES (?,?,MD5(?),?,?)";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, userPassword);
            ps.setString(4, userFullName);
            ps.setInt(5, userStatus);

            ps.executeUpdate();
            ps.close();//untuk menutup koneksi
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan" + sQLException.getMessage());
        }
    }

    public void UbahUser() {
        if (userPassword == "") {
            query = "UPDATE user SET userEmail=?,userFullName=?,userStatus=? WHERE userName=?";
            try {
                ps = koneksi.prepareStatement(query);
                ps.setString(1, userEmail);
                ps.setString(2, userFullName);
                ps.setInt(3, userStatus);
                ps.setString(4, userName);

                ps.executeUpdate();
                ps.close();//untuk menutup koneksi
                JOptionPane.showMessageDialog(null, "Data Berhasil DiUbah");
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + sQLException.getMessage());
            }
        } else {
            query = "UPDATE user SET userEmail=?,userPassword=MD5(?),userFullName=?,userStatus=? WHERE userName=?";
            try {
                ps = koneksi.prepareStatement(query);
                ps.setString(1, userEmail);
                ps.setString(2, userPassword);
                ps.setString(3, userFullName);
                ps.setInt(4, userStatus);
                ps.setString(5, userName);

                ps.executeUpdate();
                ps.close();//untuk menutup koneksi
                JOptionPane.showMessageDialog(null, "Data Berhasil DiUbah");
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, "Data Gagal DiUbah" + sQLException.getMessage());
            }
        }

    }

    public void HapusUser() {
        query = "DELETE FROM user WHERE userName = ?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);

            ps.executeUpdate();
            ps.close();//untuk menutup koneksi
            JOptionPane.showMessageDialog(null, "Data Berhasil DiHapus");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal DiHapus" + sQLException.getMessage());
        }
    }

    public ResultSet tampilUser() {
        query = "SELECT * FROM user";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");

        }
        return rs;
    }

    public void login() {
        query = "SELECT * FROM user WHERE userName =? AND userPassword = MD5(?)";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPassword);

            rs = ps.executeQuery();
            if (rs.next()) {
                sesion.setUserName(rs.getString("userName"));
                sesion.setEmail(rs.getString("userEmail"));
                sesion.setFullName(rs.getString("userFullName"));
                sesion.setStatus("Active"); // status milik sesion
            } else {
                sesion.setStatus("Inactive");
                JOptionPane.showMessageDialog(null, "Anda Gagal Login");
            }

        } catch (SQLException sQLException) {

        }
    }

    public void logout() {
        sesion.setUserName("");
        sesion.setEmail("");
        sesion.setFullName("");
        sesion.setStatus("");
    }
}

   

    

