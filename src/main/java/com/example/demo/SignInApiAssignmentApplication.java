package com.example.demo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SpringBootApplication
public class SignInApiAssignmentApplication {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		SpringApplication.run(SignInApiAssignmentApplication.class, args);
	}
	
}
