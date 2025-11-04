package org.iesvdm.demospth2025.dao;

import org.iesvdm.demospth2025.modelo.Comercial;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {

    void create(Comercial comercial);
    List<Comercial> getALL();
    Optional<Comercial> find(int id);
    void update(Comercial comercial);
    void delete(int id);

}
