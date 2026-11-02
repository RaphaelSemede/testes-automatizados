package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PontoColetaModel {
    @Expose(serialize = false)
    private Integer id;
    @Expose
    private String endereco;
    @Expose
    private BigDecimal capacidadeMaxima;
    @Expose
    private BigDecimal quantidadeAtual;
}
