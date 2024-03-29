package dealership.negotiation.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dealership.negotiation.controller.model.DealershipData;
import dealership.negotiation.controller.model.DealershipData.AutomakerData;
import dealership.negotiation.dao.AutomakerDao;
import dealership.negotiation.dao.CarDao;
import dealership.negotiation.dao.DealershipDao;
import dealership.negotiation.entity.Automaker;
import dealership.negotiation.entity.Car;
import dealership.negotiation.entity.Dealership;

@Service
public class NegotiationService {

	@Autowired
	AutomakerDao automakerDao;

	@Autowired
	CarDao carDao;

	@Autowired
	DealershipDao dealershipDao;

	@Transactional(readOnly = false)
	public DealershipData saveDealership(DealershipData dealershipData) {
		Dealership dealership = dealershipData.toDealership();
		Dealership dbDealership = dealershipDao.save(dealership);

		return new DealershipData(dbDealership);
	}

	@Transactional(readOnly = true)
	public DealershipData retrieveDealershipById(Long dealershipId) {
		Dealership dealership = findDealershipById(dealershipId);
		return new DealershipData(dealership);
	}

	private Dealership findDealershipById(Long dealershipId) {
		return dealershipDao.findById(dealershipId)
				.orElseThrow(() -> new NoSuchElementException("Dealership with ID" + dealershipId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<DealershipData> retrieveAllDealerships() {
		List<Dealership> dealershipEntities = dealershipDao.findAll();
		List<DealershipData> dealershipDtos = new LinkedList<>();

		dealershipEntities.sort((loc1, loc2) -> loc1.getDealershipName().compareTo(loc2.getDealershipName()));

		for (Dealership dealership : dealershipEntities) {
			DealershipData dealershipData = new DealershipData(dealership);
			dealershipDtos.add(dealershipData);
		}

		return dealershipDtos;
	}

	@Transactional(readOnly = false)
	public void deleteDealershipById(Long dealershipId) {
		Dealership dealership = findDealershipById(dealershipId);
		dealershipDao.delete(dealership);
	}

	private Car findCarById(Long carId) {
		return carDao.findById(carId)
				.orElseThrow(() -> new NoSuchElementException("Car with ID" + carId + " was not found."));
	}

	@Transactional(readOnly = false)
	public void deleteCarById(Long carId) {
		Car car = findCarById(carId);
		carDao.delete(car);

	}
	
	@Transactional(readOnly = false)
	public AutomakerData saveAutomaker(Long dealershipId, AutomakerData automakerData) {
		Dealership dealership = findDealershipById(dealershipId);
		
		Long automakerId = automakerData.getAutomakerId();
		Automaker automaker = findOrCreateAutomaker(automakerId, dealershipId);

		copyAutomakerFields(automaker, automakerData);

		automaker.getDealerships().add(dealership);
		dealership.getAutomakers().add(automaker);
		
		Automaker dbAutomaker = automakerDao.save(automaker);
		return new AutomakerData(dbAutomaker);
	}
	
	private void copyAutomakerFields(Automaker automaker, AutomakerData automakerData) {
		automaker.setName(automakerData.getName());
		automaker.setCity(automakerData.getCity());
		automaker.setState(automakerData.getState());
		automaker.setAutomakerId(automakerData.getAutomakerId());
	}

	private Automaker findOrCreateAutomaker(Long automakerId, Long dealershipId) {
		Automaker automaker;

		if (Objects.isNull(automakerId)) {
			automaker = new Automaker();
		} else {
			automaker = findAutomakerById(automakerId, dealershipId);
		}

		return automaker;
	}

	private Automaker findAutomakerById(Long automakerId, Long dealershipId) {
		Automaker automaker = automakerDao.findById(automakerId)
				.orElseThrow(() -> new NoSuchElementException("Automaker with ID=" + automakerId + " was not found."));

		boolean found = false;

		for (Dealership dealership : automaker.getDealerships()) {
			if (dealership.getDealershipId() == dealershipId) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new IllegalArgumentException("The automaker with ID=" + automakerId
					+ " is not doing business with the dealership with ID=" + dealershipId + ".");
		}
		return automaker;
	}
	
	private Automaker findAutomakerToDeleteById(Long automakerId) {
		return automakerDao.findById(automakerId)
				.orElseThrow(() -> new NoSuchElementException("Automaker with ID=" + automakerId + " was not found."));
	}
	
	@Transactional(readOnly = false)
	public void deleteAutomakerById(Long automakerId) {
		Automaker automaker = findAutomakerToDeleteById(automakerId);
		automakerDao.delete(automaker);
	}
	
	
	
	
}
