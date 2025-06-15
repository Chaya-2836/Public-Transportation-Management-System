package com.example.javaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.javaProject.Convertors.BusConvert;
import com.example.javaProject.Convertors.DriverConvert;
import com.example.javaProject.Convertors.LineConverter;
import com.example.javaProject.Convertors.StationConvert;
import com.example.javaProject.Convertors.Station_LineConvert;
import com.example.javaProject.Convertors.TravelConvert;

@SpringBootApplication
public class JavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}

	@Bean
	public BusConvert busConvert() {
		return new BusConvert();
	}

	@Bean
	public TravelConvert travelConvert() {
		return new TravelConvert();
	}

	@Bean
	public DriverConvert driverConvert() {
		return new DriverConvert();
	}

	@Bean
	public LineConverter lineConverter() {
		return new LineConverter();
	}

	@Bean
	public StationConvert stationConvert() {
		return new StationConvert();
	}

	@Bean
	public Station_LineConvert station_LineConvert() {
		return new Station_LineConvert();
	}
}
