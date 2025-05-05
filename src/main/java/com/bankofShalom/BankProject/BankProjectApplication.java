package com.bankofShalom.BankProject;

import com.bankofShalom.BankProject.Controller.BankController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankProjectApplication implements CommandLineRunner {
	@Autowired
	BankController controller;

	public static void main(String[] args) {
		SpringApplication.run(BankProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		controller.run();
	}
}

