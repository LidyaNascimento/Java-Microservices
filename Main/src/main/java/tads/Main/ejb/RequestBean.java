package tads.Main.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tads.Main.entidades.Request;

@Stateless
public class RequestBean {

	@PersistenceContext(unitName = "pu")
	private EntityManager entityManager;

	@PostConstruct
	private void initializeBean() {
	}

	public Request getRequest(Long id) {
		Request request = entityManager.find(Request.class, id);
		if (request != null)
			return request;
		return null;
	}

	public List<Request> getAllRequests() {
		String jpql = ("select r from Request r");
		Query query = entityManager.createQuery(jpql, Request.class);
		List<Request> requests = query.getResultList();
		if (requests != null) {
			return requests;
		}
		return null;
	}

}