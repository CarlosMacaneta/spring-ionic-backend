package com.webapplication.demo.resources;

import com.webapplication.demo.dto.EmailDTO;
import com.webapplication.demo.security.JWTUtils;
import com.webapplication.demo.security.UserSpringSecurity;
import com.webapplication.demo.services.AuthService;
import com.webapplication.demo.services.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CarlosMacaneta
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private JWTUtils jwtUtils;
    
    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSpringSecurity user = UserService.authenticated();
        String token = jwtUtils.generationToken(user.getUsername());
        
        response.addHeader("Authorization", "Bearer "+token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        authService.sendNewPassword(emailDTO.getEmail());
        
        return ResponseEntity.noContent().build();
    }
}
