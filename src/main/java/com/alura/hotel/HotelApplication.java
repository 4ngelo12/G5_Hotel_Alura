package com.alura.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
		var datos = LocalDateTime.now();
		var despues = LocalDateTime.parse("2023-09-20T20:02:20.135");

		System.out.println(Duration.between(datos,despues).toDays());
	}

}
