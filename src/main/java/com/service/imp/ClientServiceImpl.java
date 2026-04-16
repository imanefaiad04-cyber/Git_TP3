package com.service.imp;

import com.dao.ClientDao;
import com.dao.impl.ClientDaoImp;
import com.entities.Client;
import com.service.ClientService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDaoImp dao = new ClientDaoImp();


    @Override
    public List<Client> getAllClients() {
       List<Client> l = this.dao.findAll();
        Collections.sort(l);
        return l;
    }
}
