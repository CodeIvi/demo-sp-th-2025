package org.iesvdm.demospth2025.controller;

import jakarta.servlet.http.HttpSession;
import org.iesvdm.demospth2025.dao.ClienteDAO;
import org.iesvdm.demospth2025.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DemothController {
    @Autowired
    private ClienteDAO clienteDAO;

    //SIN SERVICE, Sólo actuar sobre plantilla

    //ENPOINTS


    @GetMapping("/demoth1")
    public String demothText(Model model) {

        model.addAttribute("parrafo2", "Este es el párrafo 2");

        return "plantilla1";
    }

    @GetMapping("/demoth2")
    public String demonthText(Model model) {

        Cliente cliente1 = Cliente.builder().nombre("Don Miguel de Cervantes").build();
        Cliente cliente2 = Cliente.builder().nombre("Lope de Vega").build();

        List<Cliente> list = List.of(cliente1, cliente2);
        model.addAttribute("escritores", list);

        return "plantilla2";
    }

    @GetMapping("/demoth3")
    public String demothHttpSession(Model model, HttpSession session) {

        String mensajeAsession = "Lo grabé en session 3";
        String mensajeAModel = "Esto solo lo ve la plantilla plantilla3";

        //Atributos en model solo visibles en esta planitlla.
        model.addAttribute("msgModel", mensajeAModel);

        //Atributos en sesión son visibles en todos los endpoints/plantillas
        session.setAttribute("msgSession", mensajeAsession);

        return "plantilla3";
    }

    @GetMapping("/demoth3_2")
    public String demthHttpSession2() {

        return "plantilla3_2";
    }

    @GetMapping("/demoth5")
    public String demoth5(Model model) {
        Cliente cliente = Cliente.builder()
                .id(101)
                .nombre("Cervantes")
                .build();
        model.addAttribute("cliente", cliente);
        return "plantilla5";
    }

    @GetMapping("/demoth/crear")
    public String demothCrear(Model model) {

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "plantilla6";

    }

    @PostMapping("/demoth/crear")
    public String demothCrearSubmit(@ModelAttribute Cliente cliente) {

        clienteDAO.create(cliente);

        return "redirect:/demoth/listar";
    }

    @GetMapping("/demoth/listar")
    public String demothListarClientes(Model model) {

        List<Cliente> clientes = clienteDAO.getAll();
        model.addAttribute("clientes", clientes);

        return "plantilla7";

    }

}





