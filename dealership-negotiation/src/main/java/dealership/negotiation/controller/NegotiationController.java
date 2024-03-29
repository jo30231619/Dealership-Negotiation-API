package dealership.negotiation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dealership.negotiation.controller.model.DealershipData;
import dealership.negotiation.controller.model.DealershipData.AutomakerData;
import dealership.negotiation.controller.model.DealershipData.CarData;
import dealership.negotiation.service.NegotiationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dealership_negotiation")
@Slf4j
public class NegotiationController {
	@Autowired
	private NegotiationService negotiationService;
	
	@PostMapping("/dealership")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DealershipData createDealership(@RequestBody DealershipData dealershipData) {
		log.info("Creating dealership {}", dealershipData);
		return negotiationService.saveDealership(dealershipData);
	}
	
	@PutMapping("/dealership/{dealershipId}")
	public DealershipData updateDealership(@PathVariable Long dealershipId, @RequestBody DealershipData dealershipData) {
		dealershipData.setDealershipId(dealershipId);
		log.info("Updating dealership {}", dealershipData);
		return negotiationService.saveDealership(dealershipData);
	}
	
	@GetMapping("/dealership/{dealershipId}")
	public DealershipData retrieveDealership(@PathVariable Long dealershipId) {
		log.info("Retrieving dealership with ID={}", dealershipId);
		return negotiationService.retrieveDealershipById(dealershipId);
	}
	
	@GetMapping("/dealership")
	public List<DealershipData> retrieveAllDealerships() {
		log.info("Retrieving all dealerships");
		return negotiationService.retrieveAllDealerships();
	}
	
	@DeleteMapping("/dealership/{dealershipId}")
	public Map<String, String> deleteDealership(@PathVariable Long dealershipId) {
		log.info("Deleting dealership with ID=" + dealershipId + ".");
		negotiationService.deleteDealershipById(dealershipId);
		
		return Map.of("message", "Dealership with ID=" + dealershipId + " was deleted successfully.");
	}
	
	@PostMapping("/dealership/{dealershipId}/automaker")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AutomakerData createAutomaker(@PathVariable Long dealershipId, @RequestBody AutomakerData automakerData) {
		log.info("Creating Automaker {} for dealership with ID={}", automakerData, dealershipId);
		return negotiationService.saveAutomaker(dealershipId, automakerData);
	}
	
	@DeleteMapping("/car/{carId}")
	public Map<String, String> deleteCar(@PathVariable Long carId) {
		log.info("Deleting car with ID=" + carId + ".");
		negotiationService.deleteCarById(carId);
		
		return Map.of("message", "Car with ID=" + carId + " was deleted successfully.");
	}
	
	@DeleteMapping("/automaker/{automakerId}")
	public Map<String, String> deleteAutomaker(@PathVariable Long automakerId) {
		log.info("Deleting automaker with ID=" + automakerId + ".");
		negotiationService.deleteAutomakerById(automakerId);
		
		return Map.of("message", "Automaker with ID=" + automakerId + " was deleted successfully.");
	}
	
}
