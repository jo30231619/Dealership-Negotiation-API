package dealership.negotiation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dealership.negotiation.controller.model.DealershipData;
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
}