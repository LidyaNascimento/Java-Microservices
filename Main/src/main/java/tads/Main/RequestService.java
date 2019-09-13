package tads.Main;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;

import tads.Main.ejb.RequestBean;
import tads.Main.entidades.Request;

@Path("/requests")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class RequestService {

	@EJB
	private RequestBean requestBean;

	@Context
	private HttpServletRequest httpRequest;

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long id) {
		Request request = requestBean.getRequest(id);
		if (request != null)
			return Response.ok(request).build();
		return Response.status(NOT_FOUND).build();
	}

	@GET
	public Response findAllRequests() {
		List<Request> allRequests = requestBean.getAllRequests();
		if (allRequests != null)
			return Response.ok(allRequests).build();
		return Response.status(NOT_FOUND).build();
	}

}