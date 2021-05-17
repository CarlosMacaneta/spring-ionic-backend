
package com.webapplication.demo.services.validation;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.domain.enums.TipoCliente;
import com.webapplication.demo.dto.ClienteNewDTO;
import com.webapplication.demo.repositories.ClienteRepository;
import com.webapplication.demo.resources.exceptions.FieldMessage;
import com.webapplication.demo.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author CarlosMacaneta
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteDTO, ConstraintValidatorContext cvc) {
        List<FieldMessage> list  = new ArrayList<>();
        
        if (clienteDTO.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCPF(clienteDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF é inválido"));
        }
        
        if (clienteDTO.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCNPJ(clienteDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ é inválido"));
        }
        
        Cliente aux = clienteRepository.findByEmail(clienteDTO.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "O email inserido já existe"));
        }
        
        list.forEach(e -> {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        });
        
        return list.isEmpty();
    }
    
    
}
