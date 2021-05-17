
package com.webapplication.demo.services.validation;

import com.webapplication.demo.domain.Cliente;
import com.webapplication.demo.dto.ClienteDTO;
import com.webapplication.demo.repositories.ClienteRepository;
import com.webapplication.demo.resources.exceptions.FieldMessage;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

/**
 *
 * @author CarlosMacaneta
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext cvc) {
        
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));
        
        List<FieldMessage> list  = new ArrayList<>();
        
        
        Cliente aux = clienteRepository.findByEmail(clienteDTO.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "O email inserido já existe"));
        }
        
        list.forEach(e -> {
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        });
        
        return list.isEmpty();
    }
    
    
}
