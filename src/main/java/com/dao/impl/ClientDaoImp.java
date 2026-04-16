package com.dao.impl;

import com.dao.ClientDao;
import com.dao.Connexion;
import com.entities.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class ClientDaoImp implements ClientDao {

    private Connection con = Connexion.getConnection();

    @Override
    public boolean create(Client c) {
        if(con == null){
            return false;
        }
        try {
            PreparedStatement pr = con.prepareStatement("INSERT INTO client(nom,prenom) VALUES(?,?)"
                    , Statement.RETURN_GENERATED_KEYS);
            pr.setString(1,c.getNom());
            pr.setString(2,c.getPrenom());

            int n = pr.executeUpdate();
            ResultSet rs = pr.getGeneratedKeys();
            if(rs.next()){
                c.setId(rs.getInt(1));
            }
            return n > 0;
        } catch (Exception e) {
            System.out.println("Erreur de creation : "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Client c) {
        if (con == null){
            return false;
        }
        try {
            PreparedStatement pr = con.prepareStatement("DELETE FROM client WHERE id = ? ");
            pr.setInt(1,c.getId());
            int n = pr.executeUpdate();
            return n > 0;
        } catch (Exception e) {
            System.out.println("Erreur de la suprrimer"+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Client c) {
        if (con == null){
            return false;
        }
        try {
            PreparedStatement pr = con.prepareStatement("UPDATE client SET nom = ? , prenom = ? WHERE id = ?  ");
            pr.setString(1,c.getNom());
            pr.setString(2, c.getPrenom());
            pr.setInt(3,c.getId());

            int n = pr.executeUpdate();

            return n > 0;

        } catch (Exception e) {
            System.out.println("Erreur de Modification "+e.getMessage());
            return false;
        }
    }


    public boolean CreateOrUpdate(Client c){
        if (this.findById(c.getId())!= null){
            return this.update(c);
        }
        return this.create(c);
    }

    @Override
    public Client findById(int id) {

        if (con == null){
            return null;
        }
        try {
            PreparedStatement pr = con.prepareStatement("SELECT * FROM client WHERE id = ?");
            pr.setInt(1,id);

            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                Client c = new Client();
                c.setId(id);
                c.setNom(rs.getNString("nom"));
                c.setPrenom(rs.getNString("prenom"));
                return c;
            }

        } catch (Exception e) {
            System.out.println("Erreur Pad Find "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        if (con == null){
            return null;
        }
        try {
            PreparedStatement pr = con.prepareStatement("SELECT * FROM client");
            ResultSet rs = pr.executeQuery();
            List<Client> cl = new ArrayList<>();
            if(rs.next()){
                Client cc = new Client();
                cc.setId(rs.getInt("id"));
                cc.setNom(rs.getNString("nom"));
                cc.setPrenom(rs.getNString("prenom"));
                cl.add(cc);
            }

            return cl;

        } catch (Exception e) {
            System.out.println("Erreur findAll  "+e.getMessage());
        }
        return null;
    }
}
