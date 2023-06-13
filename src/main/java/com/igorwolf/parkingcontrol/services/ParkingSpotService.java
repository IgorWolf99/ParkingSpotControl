package com.igorwolf.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igorwolf.parkingcontrol.entities.ParkingSpot;
import com.igorwolf.parkingcontrol.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {
	
	@Autowired	
	public ParkingSpotRepository parkingSpotRepository;
	

	public List<ParkingSpot> findAll(){
		return parkingSpotRepository.findAll();
	}
	
	public Optional<ParkingSpot> findById(UUID id) {
		return parkingSpotRepository.findById(id);
	}
	
	@Transactional
	public ParkingSpot save(ParkingSpot parkingSpot) {
		return parkingSpotRepository.save(parkingSpot);
	}
	
	@Transactional
	public void delete(ParkingSpot parkingSpot) {
		parkingSpotRepository.delete(parkingSpot);
	}
	
	
	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
		return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
	}
	public boolean existsByApartment(String apartment) {
		return parkingSpotRepository.existsByApartment(apartment);
	}
	public boolean existsByLiscencePlateCar(String liscencePlateCar) {
		return parkingSpotRepository.existsByLicensePlateCar(liscencePlateCar);
	}

	
	public boolean existsByLicensePlateCarExceptId(String licensePlateCar, UUID id) {
        return parkingSpotRepository.existsByLicensePlateCarAndIdNot(licensePlateCar, id);
    }
    public boolean existsByParkingSpotNumberExceptId(String parkingSpotNumber, UUID id) {
        return parkingSpotRepository.existsByParkingSpotNumberAndIdNot(parkingSpotNumber, id);
    }
    public boolean existsByApartmentExceptId(String apartment, UUID id) {
        return parkingSpotRepository.existsByApartmentAndIdNot(apartment, id);
    }
}
