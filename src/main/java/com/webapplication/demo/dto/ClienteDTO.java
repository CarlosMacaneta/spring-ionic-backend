package com.webapplication.demo.dto;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.services.validation.ClienteUpdate;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author CarlosMacaneta
 */
@ClienteUpdate
public class ClienteDTO implements Serializable {
    
    private Integer id;
    
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5, max = 120, message = "O tamanho de ser entre 5 e 120 caracteres")
    private String nome;
    
    @NotEmpty(message = "Preencimento obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
    
    public ClienteDTO(Cliente cliente){
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
