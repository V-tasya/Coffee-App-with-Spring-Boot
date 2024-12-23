package com.vitaliya.simpleProject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SimpleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleProjectApplication.class, args);
	}

}
