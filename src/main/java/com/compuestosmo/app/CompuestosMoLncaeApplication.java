package com.compuestosmo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.compuestosmo.app.models.service.IUploadFileService;

@SpringBootApplication
public class CompuestosMoLncaeApplication {

	@Autowired
	IUploadFileService uploadFileService;
	
	public static void main(String[] args) {
		SpringApplication.run(CompuestosMoLncaeApplication.class, args);
	}

}
