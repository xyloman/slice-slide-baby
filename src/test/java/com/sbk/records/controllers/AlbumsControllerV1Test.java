package com.sbk.records.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbk.records.entities.AlbumEntity;
import com.sbk.records.repositories.AlbumRepository;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AlbumsControllerV1.class, secure = false)
public class AlbumsControllerV1Test {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AlbumRepository albumRepository;
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void example1() throws Exception {
		List<AlbumEntity> result = Stream.of(new AlbumEntity("To the Extreme", 646876800000L, "Hip Hop", "1989", "SBK Records"))
				.collect(Collectors.toList());
		
		given(this.albumRepository.findAll()).willReturn(result);
		
		this.mvc.perform(get("/v1/albums")
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
	            .andExpect(content().json(mapper.writeValueAsString(result)));
	}
	
	@Test
	public void example2() throws Exception {
		List<AlbumEntity> result = Stream.of(new AlbumEntity("id-goes-here", "To the Extreme", 646876800000L, "Hip Hop", "1989", "SBK Records"))
				.collect(Collectors.toList());
		
		given(this.albumRepository.findAll()).willReturn(result);
		
		this.mvc.perform(get("/v1/albums")
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
	            .andExpect(content().json("[{\"id\":\"id-goes-here\",\"title\":\"To the Extreme\",\"releasedTimestamp\":646876800000,\"genre\":\"Hip Hop\",\"recorded\":\"1989\",\"label\":\"SBK Records\"}]"));
	}
	
	@Test
	public void example3() throws Exception { 
		given(this.albumRepository.findByTitleIgnoreCase(eq("hot space")))
			.willReturn(Stream.of(new AlbumEntity("id-goes-here", "Hot Space", 372902400000L, "Rock", "July 1981", "EMI")).collect(Collectors.toList()));
		
		this.mvc.perform(get("/v1/albums")
				.param("title", "hot space")
	            .accept(APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
	            .andExpect(content().json("[{\"id\":\"id-goes-here\",\"title\":\"Hot Space\",\"releasedTimestamp\":372902400000,\"genre\":\"Rock\",\"recorded\":\"July 1981\",\"label\":\"EMI\"}]"));
	}
}
