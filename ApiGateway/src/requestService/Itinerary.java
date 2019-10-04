package requestService;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itinerary", catalog = "main")
public class Itinerary extends GeneralEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "departure", nullable = false)
	private String departure;
	
	@Column(name = "arrival", nullable = false)
	private String arrival;
	
	@Column(name = "departureDate")
	private String departureDate;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	
}