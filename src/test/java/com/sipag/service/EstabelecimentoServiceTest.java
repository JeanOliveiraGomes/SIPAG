package com.sipag.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sipag.dto.EstabelecimentoDTO;
import com.sipag.entity.Email;
import com.sipag.entity.Endereco;
import com.sipag.entity.Estabelecimento;
import com.sipag.entity.Telefone;
import com.sipag.enums.EstadosEnum;
import com.sipag.enums.TipoTelefoneEnum;
import com.sipag.repository.EstabelecimentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstabelecimentoServiceTest {
	
	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Test
	public void testDeletarSucesso() throws Exception {
		
		Email email = new Email("teste2@teste1.com");
		List<Email> emails = new ArrayList<Email>();
		emails.add(email);
		
		Telefone telefone = new Telefone();
		telefone.setNumero("1115121212");
		telefone.setTipo(TipoTelefoneEnum.CELULAR);
		List<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(telefone);
		
		
		List<Endereco> enderecos = new ArrayList<Endereco>();
		Endereco endereco = new Endereco();
		endereco.setBairro("Bairro");
		endereco.setCep("2151212120");
		endereco.setCidade("cidade");
		endereco.setLougradouro("lougradoruo");
		endereco.setUf(EstadosEnum.DF);
		enderecos.add(endereco);
		
		EstabelecimentoDTO estabelecimento = new EstabelecimentoDTO();
		estabelecimento.setCnpj("11111111111111");
		estabelecimento.setEmail(emails);
		estabelecimento.setNome("NOME2 DO ESTABELECIMENTO VINTE CARACTERES");
		estabelecimento.setEnderecos(enderecos);
		estabelecimento.setTelefone(telefones);
		
		EstabelecimentoDTO dtoSalvo = estabelecimentoService.salvar(estabelecimento);
		
		System.out.println(dtoSalvo.getId());
		estabelecimentoService.deletar(dtoSalvo.getId());
		Estabelecimento estabelecimentoEntity = estabelecimentoRepository.findById(dtoSalvo.getId()).orElse(null);
		
		assertNull(estabelecimentoEntity);
		
	}
	
	@Test
	public void testSave() throws Exception {
		
		Email email = new Email("teste@teste.com");
		List<Email> emails = new ArrayList<Email>();
		emails.add(email);
		
		Telefone telefone = new Telefone();
		telefone.setNumero("1115121212");
		telefone.setTipo(TipoTelefoneEnum.CELULAR);
		List<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(telefone);
		
		
		List<Endereco> enderecos = new ArrayList<Endereco>();
		Endereco endereco = new Endereco();
		endereco.setBairro("Bairro");
		endereco.setCep("2151212120");
		endereco.setCidade("cidade");
		endereco.setLougradouro("lougradoruo");
		endereco.setUf(EstadosEnum.DF);
		enderecos.add(endereco);
		
		EstabelecimentoDTO estabelecimento = new EstabelecimentoDTO();
		estabelecimento.setCnpj("11111111111111");
		estabelecimento.setEmail(emails);
		estabelecimento.setNome("NOME DO ESTABELECIMENTO VINTE CARACTERES");
		estabelecimento.setEnderecos(enderecos);
		estabelecimento.setTelefone(telefones);
		
		EstabelecimentoDTO dtoSalvo = estabelecimentoService.salvar(estabelecimento);
		assertNotNull(dtoSalvo);
	}
	
	
	@Test
	public void testFindAllSucesso() {
		estabelecimentoService.findAll();
	}
}
