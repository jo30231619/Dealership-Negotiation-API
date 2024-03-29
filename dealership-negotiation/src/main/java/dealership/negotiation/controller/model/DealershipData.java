package dealership.negotiation.controller.model;

import java.util.HashSet;
import java.util.Set;

import dealership.negotiation.entity.Automaker;
import dealership.negotiation.entity.Car;
import dealership.negotiation.entity.Dealership;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DealershipData {
	private Long dealershipId;
	private String dealershipName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private Set<CarData> cars = new HashSet<>();
	private Set<AutomakerData> automakers = new HashSet<>();

	public DealershipData(Dealership dealership) {
		this.dealershipId = dealership.getDealershipId();
		this.dealershipName = dealership.getDealershipName();
		this.streetAddress = dealership.getStreetAddress();
		this.city = dealership.getCity();
		this.state = dealership.getState();
		this.zip = dealership.getZip();
		this.phone = dealership.getPhone();

		for (Car car : dealership.getCars()) {
			this.cars.add(new CarData(car));
		}

		for (Automaker automaker : dealership.getAutomakers()) {
			this.automakers.add(new AutomakerData(automaker));
		}
	}

	public DealershipData(Long dealershipId, String dealershipName, String streetAddress, String city, String state,
			String zip, String phone) {
		this.dealershipId = dealershipId;
		this.dealershipName = dealershipName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}

	public Dealership toDealership() {
		Dealership dealership = new Dealership();

		dealership.setDealershipId(dealershipId);
		dealership.setDealershipName(dealershipName);
		dealership.setStreetAddress(streetAddress);
		dealership.setCity(city);
		dealership.setState(state);
		dealership.setZip(zip);
		dealership.setPhone(phone);

		for (CarData carData : cars) {
			dealership.getCars().add(carData.toCar());
		}

		for (AutomakerData automakerData : automakers) {
			dealership.getAutomakers().add(automakerData.toAutomaker());
		}

		return dealership;
	}
	

	@Data
	@NoArgsConstructor
	public static class CarData {
		private Long carId;
		private String make;
		private String model;
		private String carYear;
		private String vin;
		private String mileage;

		public CarData(Car car) {
			this.carId = car.getCarId();
			this.make = car.getMake();
			this.model = car.getModel();
			this.carYear = car.getCarYear();
			this.vin = car.getVin();
			this.mileage = car.getMileage();
		}

		public Car toCar() {
			Car car = new Car();

			car.setCarId(carId);
			car.setMake(make);
			car.setModel(model);
			car.setCarYear(carYear);
			car.setVin(vin);
			car.setMileage(mileage);

			return car;
		}
	}

	@Data
	@NoArgsConstructor
	public static class AutomakerData {
		private Long automakerId;
		private String name;
		private String city;
		private String state;
		
		public AutomakerData(Automaker automaker) {
			this.automakerId = automaker.getAutomakerId();
			this.name = automaker.getName();
			this.city = automaker.getCity();
			this.state = automaker.getState();
		}

		public Automaker toAutomaker() {
			Automaker automaker = new Automaker();

			automaker.setAutomakerId(automakerId);
			automaker.setName(name);
			automaker.setCity(city);
			automaker.setState(state);

			return automaker;
		}

	}
}


//toDealership is to convert from object to data object.
