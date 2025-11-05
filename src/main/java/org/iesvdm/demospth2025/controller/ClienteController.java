package org.iesvdm.demospth2025.controller;


import org.iesvdm.demospth2025.modelo.Cliente;
import org.iesvdm.demospth2025.servicio.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClienteController {


    private ClienteService clienteServicio;

    public ClienteController(ClienteService clienteServicio) {
        this.clienteServicio = clienteServicio;
    }


    @GetMapping("/clientes")
    public String listarClientes(Model model){

        List<Cliente> clienteList = clienteServicio.all();
        model.addAttribute("listaCLientes",clienteList);

        return "clientes";
    }
}
