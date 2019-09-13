package tads.Main.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "request", catalog = "main")
public class Request extends GeneralEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Itinerary> itineraries;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;
	
	public void addItinerary(Itinerary itinerary) {
        if (this.itineraries == null) {
            this.itineraries = new ArrayList<>();
        }
        this.itineraries.add(itinerary);
    }

	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}
	
}