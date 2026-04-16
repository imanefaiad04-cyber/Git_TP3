package com.test;

import com.dao.impl.ClientDaoImp;
import com.entities.Client;
import com.service.ClientService;
import com.service.imp.ClientServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        ClientServiceImpl service = new ClientServiceImpl();

        List<Client> clients = service.getAllClients();

        for(Client c : clients){
            System.out.println(c.getId()+" "+c.getNom()+" "+c.getPrenom());
        }
    }
}