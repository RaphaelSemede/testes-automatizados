package model;


import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UsuarioModel {
    @Expose(serialize = false)
    private Integer id;
    @Expose
    private String nome;
    @Expose
    private String email;
    @Expose
    private String role;
    @Expose
    private String senha;
}
