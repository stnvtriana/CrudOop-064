/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

/**
 *
 * @author siti novi triana
 */
public class sesion {
    
    private static String userName, email, fullName, status; //bisa langsung di panggil tidak perlu membuat object di kelas lain dan bisa di panggil dikelas manapun

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userNmae) {
        sesion.userName = userNmae;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        sesion.email = email;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        sesion.fullName = fullName;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        sesion.status = status;
    }
    
    

    
}
