package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {

    private static Connection con = null;

    private static void cont() {

        String url = "jdbc:mysql://127.0.0.1:3306/tp3jdbc";
        String user = "root";
        String password = "imane0000";

        try {

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");
        } catch (Exception e) {
            System.out.println("Problème de connexion !! " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (con == null) {
            cont();
        }
        return con;
    }
}