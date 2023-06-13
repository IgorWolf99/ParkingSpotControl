package com.igorwolf.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		if(parkingSpotService.existsByLiscencePlateCar(parkingSpotDto.getLicensePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa já registrada.");
		}
		if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Vaga já utilizada.");
		}
		if(parkingSpotService.existsByApartment(parkingSpotDto.getApartment())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Apartamento já registrado.");
		}
		
		var parkingSpot = new ParkingSpot();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpot);
		parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		parkingSpotService.save(parkingSpot);
		
		String message = "VAGA REGISTRADA\n"
							+ "Vaga nº: " + parkingSpot.getParkingSpotNumber()
							+ "\nResponsavel: " + parkingSpot.getResponsibleName()
							+ "\nApartamento: " + parkingSpot.getApartment()
							+ "\nPlaca do carro: " + parkingSpot.getLicensePlateCar().toUpperCase();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(message); 
	}
	
	
	@GetMapping
	public ResponseEntity<List<ParkingSpot>> findAllParkingSpots(){
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable UUID id){
		Optional<ParkingSpot> result = parkingSpotService.findById(id);
		if (!result.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(result.get());
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable UUID id){
		Optional<ParkingSpot> parkingSpotOptional= parkingSpotService.findById(id);
		if (!parkingSpotOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada.");
		}
			ParkingSpot parkingSpot = parkingSpotOptional.get();
			parkingSpotService.delete(parkingSpot);
			
			String message = "VAGA DELETADA\n"
					+ "Vaga nº: " +parkingSpot.getParkingSpotNumber()
					+ " - Responsavel: " + parkingSpot.getResponsibleName();
			return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateParkingSpot(@PathVariable UUID id,
													@RequestBody @Valid ParkingSpotDto dto){
		Optional<ParkingSpot> parkingSpotOptional = parkingSpotService.findById(id);
		if (!parkingSpotOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado.");
		}
		
		// Verificação de dados ja existentes exceto do id que esta sendo editado.
		if (parkingSpotService.existsByLicensePlateCarExceptId(dto.getLicensePlateCar(), id)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa já registrada.");
	    }
	    if (parkingSpotService.existsByParkingSpotNumberExceptId(dto.getParkingSpotNumber(), id)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Vaga já utilizada.");
	    }
	    if (parkingSpotService.existsByApartmentExceptId(dto.getApartment(), id)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Apartamento já registrado.");
	    }
		
		var parkingSpot = new ParkingSpot();
		BeanUtils.copyProperties(dto, parkingSpot);
		parkingSpot.setId(parkingSpotOptional.get().getId());
		parkingSpot.setRegistrationDate(parkingSpotOptional.get().getRegistrationDate());
		
		parkingSpotService.save(parkingSpot);
		String message = "VAGA ATUALIZADA.\n" 
				+ "Id: " + parkingSpot.getId()
				+ "\nResponsavel: " + parkingSpot.getResponsibleName();
		
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
}

