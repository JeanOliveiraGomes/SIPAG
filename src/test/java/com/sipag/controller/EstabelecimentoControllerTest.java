package com.sipag.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EstabelecimentoControllerTest {

	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testarBuscarTodosEstabelecimentos() throws Exception {
		String url = "api/estabelecimento";
//		this.mvc.perform(get(url));
	}
}
