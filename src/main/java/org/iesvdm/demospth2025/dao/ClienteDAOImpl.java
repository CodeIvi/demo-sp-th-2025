package org.iesvdm.demospth2025.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.demospth2025.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring
//que forma parte de la 'capa de persistencia'.
@Repository

public class ClienteDAOImpl implements ClienteDAO {


    private final JdbcClient jdbcClient;
    private final JdbcTemplate jdbcTemplate;

    public ClienteDAOImpl(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate) {
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Cliente cliente) {

    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> listClientes = jdbcTemplate.query("""
                Select * 
                from cliente
                """,
                (rs, rowNum) -> new Cliente(
                                        rs.getInt("id"),
                                        rs.getString("nombre"),
                                        rs.getString("apellido1"),
                                        rs.getString("apellido2"),
                                        rs.getString("ciudad"),
                                        rs.getInt("categoría")
                                    )
                );

        log.info("Devueltos {} clientes",listClientes.size());
        log.debug("Devueltos {} clientes ",listClientes.size());

        return listClientes;
    }

    @Override
    public Optional<Cliente> find(int id) {

        Cliente fab = jdbcTemplate
                .queryForObject("""
                        SELECT *
                        FROM cliente 
                        WHERE id = ?
                        """,
                        (rs,rowNum)->Cliente.builder()
                                .id(rs.getInt("id"))
                                .nombre(rs.getString("nombre"))
                                .apellido1(rs.getString("apellido1"))
                                .apellido2(rs.getString("apellido2"))
                                .ciudad(rs.getString("ciudad"))
                                .categoria(rs.getInt("categoría"))
                                .build()
                        ,
                        id

        );

        if (fab != null) {
            return Optional.of(fab);}
        else {
            log.info("Cliente no encontrado.");
            return Optional.empty(); }
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void delete(int id) {

    }
}

