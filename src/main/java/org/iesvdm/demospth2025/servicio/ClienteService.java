package org.iesvdm.demospth2025.servicio;

import org.iesvdm.demospth2025.dao.ClienteDAO;
import org.iesvdm.demospth2025.modelo.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {


    private ClienteDAO clienteDAO;

    //Inyección de dependencia de clienteDAO a traves de constructor
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> all(){
        return clienteDAO.getAll();
    }
}
