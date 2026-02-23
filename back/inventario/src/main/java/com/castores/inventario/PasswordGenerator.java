package com.castores.inventario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	


	
		 public static void main(String[] args) {

		        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		        String password = "1234";

		        String hash = encoder.encode(password);

		        System.out.println("Hash generado:");
		        System.out.println(hash);
		    }

	

}
