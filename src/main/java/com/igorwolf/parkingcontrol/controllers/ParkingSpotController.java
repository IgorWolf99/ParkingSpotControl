package com.igorwolf.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igorwolf.parkingcontrol.dtos.ParkingSpotDto;
import com.igorwolf.parkingcontrol.entities.ParkingSpot;
import com.igorwolf.parkingcontrol.services.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/parking-spot")
public class ParkingSpotController {

	@Autowired
	public ParkingSpotService parkingSpotService;
	
	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
		var parkingSpot = new ParkingSpot();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpot);
		parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		parkingSpotService.save(parkingSpot);
		
		String message = "VAGA REGISTRADA\n"
							+ "Vaga nº: " + parkingSpot.getParkingSpotNumber()
							+ "\nResponsavel: " + parkingSpot.getResponsibleName()
							+ "\nApartamento: " + parkingSpot.getApartment()
							+ "\nPlaca: " + parkingSpot.getLicensePlateCar();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
	
	@GetMapping
	public ResponseEntity<List<ParkingSpot>> findAllParkingSpots(){
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
	}
	
}





