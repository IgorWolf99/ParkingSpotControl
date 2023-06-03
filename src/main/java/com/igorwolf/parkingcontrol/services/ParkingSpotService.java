package com.igorwolf.parkingcontrol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igorwolf.parkingcontrol.entities.ParkingSpot;
import com.igorwolf.parkingcontrol.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {
	
	@Autowired	
	public ParkingSpotRepository parkingSpotRepository;
	
	
	@Transactional
	public ParkingSpot save(ParkingSpot parkingSpot) {
		return parkingSpotRepository.save(parkingSpot);
	}
	
	public List<ParkingSpot> findAll(){
		return parkingSpotRepository.findAll();
	}
}
