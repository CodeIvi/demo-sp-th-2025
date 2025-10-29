package org.iesvdm.demospth2025.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
@Data
@AllArgsConstructor
public class Pedido {
    private int id;
    private BigDecimal total;
    private LocalDate fecha;
    private Integer idCLiente;
    private Integer idComercial;
}
