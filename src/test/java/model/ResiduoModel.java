package model;


import com.google.gson.annotations.Expose;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResiduoModel {
    @Expose(serialize = false)
    private Integer id;
    @Expose
    private String tipo;
    @Expose
    private BigDecimal peso;
    @Expose
    private LocalDateTime dataRegistro;
    @Expose
    private String statusColeta;
    @Expose
    private Integer usuarioId;
}
