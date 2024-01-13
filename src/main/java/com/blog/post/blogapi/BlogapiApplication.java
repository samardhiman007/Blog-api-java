package com.blog.post.blogapi;

import com.blog.post.blogapi.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogapiApplication {


	public static void main(String[] args) {
		SpringApplication.run(BlogapiApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
