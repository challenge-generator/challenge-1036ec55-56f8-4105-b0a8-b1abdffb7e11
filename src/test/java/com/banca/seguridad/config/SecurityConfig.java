package com.banca.seguridad.config;

import net.serenitybdd.screenplay.player.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.WhenPageLoads;
import net.thucydides.core.pages.Pages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/publico/**").permitAll()
                .requestMatchers("/api/seguro/**").authenticated()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

    public static class SecurityTestConfig {
        private String tokenJwt;
        private String endpointProtegido;
        private String metodoAutenticacion;

        @WhenPageLoads
        public void waitForPage() {
        }

        public String getTokenJwt() {
            return tokenJwt;
        }

        public void setTokenJwt(String tokenJwt) {
            this.tokenJwt = tokenJwt;
        }

        public String getEndpointProtegido() {
            return endpointProtegido;
        }

        public void setEndpointProtegido(String endpointProtegido) {
            this.endpointProtegido = endpointProtegido;
        }

        public String getMetodoAutenticacion() {
            return metodoAutenticacion;
        }

        public void setMetodoAutenticacion(String metodoAutenticacion) {
            this.metodoAutenticacion = metodoAutenticacion;
        }
    }
}