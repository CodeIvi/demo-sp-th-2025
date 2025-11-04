package org.iesvdm.demospth2025.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.demospth2025.modelo.Cliente;
import org.iesvdm.demospth2025.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring
//que forma parte de la 'capa de persistencia'.
@Repository
public class ComercialDAOImpl implements ComercialDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ComercialDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Comercial comercial) {
        String sql = """
                insert into comercial (nombre, apellido1, apellido2, comision)
                values (      ?,      ?,     ?,     ?);
                """;
        String[] ids = {"id"};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con->{

                    PreparedStatement ps = con.prepareStatement(sql,ids);

                    ps.setString(1,comercial.getNombre());
                    ps.setString(2,comercial.getApellido1());
                    ps.setString(3,comercial.getApellido2());
                    ps.setDouble(4,comercial.getComision());


                    return ps;
                }
                ,keyHolder);
        comercial.setId((int)keyHolder.getKey().intValue());

    }

    @Override
    public List<Comercial> getALL() {
        List<Comercial> comercialList = jdbcTemplate.query("""
                Select * 
                from Comercial
                """,
                (rs, rowNum) -> new Comercial(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comision")
                )
        );

        log.info("Devueltos {} clientes",comercialList.size());
        log.debug("Devueltos {} clientes ",comercialList.size());
        return comercialList;
    }

    @Override
    public Optional<Comercial> find(int id) {
        try {
            Comercial comercial = jdbcTemplate.queryForObject("""
                        
                        SELECT *
                        FROM Comercial 
                        WHERE id = ?
                        """,
                    (rs,rowNum)->Comercial.builder()
                            .id(rs.getInt("id"))
                            .nombre(rs.getString("nombre"))
                            .apellido1(rs.getString("apellido1"))
                            .apellido2(rs.getString("apellido2"))
                            .comision(rs.getFloat("Comision"))
                            .build()
                    ,
                    id

            );
            return Optional.of(comercial);

        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }


    @Override
    public void update(Comercial comercial) {
        var rowsUpdate = jdbcTemplate.update("""
                UPDATE Comercial
                SET nombre = ?,apellido1 = ?,apellido2 = ?, comision = ?
                WHERE id = ?
                """, comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getComision(),
                comercial.getId()
        );

        log.debug("Flias actualizadas {}", rowsUpdate);


    }


    @Override
    public void delete(int id) {
        int rowsUpdate = jdbcTemplate.update("""
        DELETE FROM Comercial
        WHERE id = ?
        """
                ,id);
        log.debug("Flias actualizadas {}", rowsUpdate);

    }
}
