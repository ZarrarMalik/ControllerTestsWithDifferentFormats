package com.example.demo;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestForResource {

	private MockMvc mockMvc;
	
	@Mock
	private MessageService messageService;
	
	@InjectMocks
	private NewMessageResource newMessageResource;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(newMessageResource)
				.build();
	}
	
	
	@Test
	public void testFirst() throws Exception {
		Mockito.when(messageService.messageString()).thenReturn("Test 1");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/home")
				).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Test 1"));
		
		Mockito.verify(messageService).messageString();
	}

	
	@Test
	public void testSecond() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/home/json")
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Welcome")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is("Test2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)));
	}
	
	
}
