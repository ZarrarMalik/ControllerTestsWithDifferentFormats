package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class NewMessageResource {
	@GetMapping
	public String testingMessage() {

		return "Test1";
}
	
	@GetMapping(value = "/json", produces= MediaType.APPLICATION_JSON_VALUE)
	public Hello json() {
		return new Hello("Welcome", "Test2");
	}
	
	
	
	
	private class Hello{
		private String title;
		private String value;
		
		public Hello(String title, String value) {
			super();
			this.title = title;
			this.value = value;
		}
		
	
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
	
}
