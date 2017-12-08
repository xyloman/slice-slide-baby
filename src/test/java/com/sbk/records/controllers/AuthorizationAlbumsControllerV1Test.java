package com.sbk.records.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sbk.records.SecurityConfig;
import com.sbk.records.repositories.AlbumRepository;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AlbumsControllerV1.class, secure = true)
@Import(SecurityConfig.class)
public class AuthorizationAlbumsControllerV1Test {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AlbumRepository albumRepository;

	@Test
	public void example1() throws Exception {		
		this.mvc.perform(get("/v1/albums")
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().string(""));
		
		verifyZeroInteractions(this.albumRepository);
	}
	
	@Test
	public void example2_authenticated_with_out_authorities() throws Exception { 
		this.mvc.perform(get("/v1/albums")
				.with(user("admin"))
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().string(""));
		
		verifyZeroInteractions(this.albumRepository);
	}
	
	@Test
	public void example3_authenticated_with_authority() throws Exception { 
		this.mvc.perform(get("/v1/albums")
				.with(user("admin").authorities(new SimpleGrantedAuthority("ALBUMS_ADMIN")))
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isForbidden());
		
		verify(this.albumRepository, atLeastOnce()).findAll();
	}
	
	@Test
	public void example4_authenticated_with_wrong_authority() throws Exception { 
		this.mvc.perform(get("/v1/albums")
				.with(user("admin").authorities(new SimpleGrantedAuthority("ALBUMS_USER")))
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isOk());	 
		
		verifyZeroInteractions(this.albumRepository);
	}	
}
