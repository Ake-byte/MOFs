package com.compuestosmo.app;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.compuestosmo.app.models.service.IUploadFileService;

@SpringBootApplication
public class CompuestosMoLncaeApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	IUploadFileService uploadFileService;
	
	public static void main(String[] args) {
		SpringApplication.run(CompuestosMoLncaeApplication.class, args);
	}
	

	public void run(String... args) {
		//String password = "12345";
		// String result = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
	    //System.out.println("random = " + result);
		
	        
		//for(int i = 0; i < 40; i++) {
			//String password = RandomStringUtils.random(15, 0, 8, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
	        //System.out.print(password);
	        //System.out.print("   ");
	        //String bcryptPassword = passwordEncoder.encode(password);
			//System.out.println(bcryptPassword);
		//}
	}
}
