package dealership.negotiation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carId;
	
	@EqualsAndHashCode.Exclude
	private String make;
	
	@EqualsAndHashCode.Exclude
	private String model;
	
	@EqualsAndHashCode.Exclude
	private String carYear;
	
	@EqualsAndHashCode.Exclude
	private String vin;
	
	@EqualsAndHashCode.Exclude
	private String mileage;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "dealership_id", nullable = false)
	private Dealership dealership; 
}
