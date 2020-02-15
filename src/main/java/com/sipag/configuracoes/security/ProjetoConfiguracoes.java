package com.sipag.configuracoes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

import com.sipag.configuracoes.security.jwt.JwtAuthenticationEntryPoint;
import com.sipag.configuracoes.security.jwt.JwtTokenFilter;
import com.sipag.service.CustomUseDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProjetoConfiguracoes extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthenticationManager autenticationManager;
	
	@Autowired
	CustomUseDetailsManager customUseDetailsManager;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(this.customUseDetailsManager).passwordEncoder(passwordEnconder());
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

	@Bean
	@Qualifier
	public PasswordEncoder passwordEnconder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtTokenFilter();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers(
					HttpMethod.GET,
					"/",
					"/*.html",
					"/favicon.ico",
					"/**/*.html",
					"/**/*.cs",
					"/**/*.js"
			).permitAll()
			.antMatchers("/api/auth/**").permitAll()
			//permitindo tudo
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated();
			http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
			http.headers().cacheControl();
			
			http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
	}

}
