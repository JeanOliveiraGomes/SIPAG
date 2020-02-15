package com.sipag;

import java.util.Objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sipag.configuracoes.security.AuditorAwareImpl;
import com.sipag.entity.Pessoa;
import com.sipag.enums.PerfilEnum;
import com.sipag.service.CustomUseDetailsManager;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class SipagApplication {
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SipagApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(CustomUseDetailsManager pessoaService, PasswordEncoder passEncoder) {
		return args -> {
			initAdministrador(pessoaService,passEncoder);
		}; 
	}
	
	private void initAdministrador(CustomUseDetailsManager pessoaService, PasswordEncoder passEncode) {
		Pessoa administrador = pessoaService.findByEmail("administrador@sipag.com");
		if (Objects.isNull(administrador)) {
			pessoaService.salvarPessoa("administrador@sipag.com", PerfilEnum.ADMIN);
		}
		Pessoa operador = pessoaService.findByEmail("operador@sipag.com");
		if (Objects.isNull(operador)) {
			pessoaService.salvarPessoa("operador@sipag.com", PerfilEnum.OPERADOR);
		}
	}

}
