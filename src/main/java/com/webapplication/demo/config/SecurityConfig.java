package com.webapplication.demo.config;

import com.webapplication.demo.security.JWTAuthenticationFilter;
import com.webapplication.demo.security.JWTAuthorizationFilter;
import com.webapplication.demo.security.JWTUtils;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author CarlosMacaneta
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private JWTUtils jwtUtils;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    //requisicoes liberadas(elas nao necessitam de autenticacao)
    private static final String[] PUBLIC_MATCHERS = {
        "/h2-console/**"
    };

    //dando acesso apenas de leitura de dados ao visitante
    private static final String[] PUBLIC_MATCHERS_GET = {
        "/produtos/**",
        "/categorias/**",
        "/estados/**"
    };
    
    private static final String[] PUBLIC_MATCHERS_POST = {
        "/clientes",
        "/auth/forgot/**"
    };
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //se o perfil estiver no test entao o h2 tera acesso liberado
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
        
        http.cors().and().csrf().disable();
        
        //dado permisao de acesso as requisicoes liberadas e restringindo acesso para as que nao foram liberadas
        http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager() , jwtUtils));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager() , jwtUtils, userDetailsService));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//garante que sessoes de usario nao sejam criadas
    }

    /*
    * identifica o user detail e o tipo de algoritmo de codificacao da senha
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
 
    
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        source.registerCorsConfiguration("/**", corsConfig);
        
        return source;
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
