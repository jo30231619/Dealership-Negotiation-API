package dealership.negotiation.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Automaker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long automakerId;
	
	private String name;
	private String city;
	private String state;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "automakers", cascade = CascadeType.PERSIST)
	private Set<Dealership> dealerships = new HashSet<>();
}
