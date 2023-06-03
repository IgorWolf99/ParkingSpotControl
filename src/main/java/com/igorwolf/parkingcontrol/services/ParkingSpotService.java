package com.igorwolf.parkingcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorwolf.parkingcontrol.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {
	
	@Autowired	
	public ParkingSpotRepository parkingSpotRepository;
}
