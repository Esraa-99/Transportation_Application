package com.company.Transportation_Application;

import com.company.Transportation_Application.model.Person;
import com.company.Transportation_Application.service.DriverControl;
import com.company.Transportation_Application.service.UserControl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TransportationApplication {
	static int ratioOfDiscount=0;
	public static void main(String[] args) {
		SpringApplication.run(TransportationApplication.class, args);

	}

}
