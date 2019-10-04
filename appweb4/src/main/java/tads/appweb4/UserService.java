package tads.appweb4;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tads.appweb4.ejb.UserBean;
import tads.appweb4.entidades.User;
import tads.appweb4.jwtConfiguration.JsonTokenNeeded;
import tads.appweb4.util.JwTokenHelper;

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class UserService {

	@EJB
	private UserBean userBean;

	@Context
	private HttpServletRequest httpRequest;

	
	@POST
	@Path("/login")
	// @Consumes(APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		System.out.println(user.getNome() + user.getSenha());
		try {
			User login_user = userBean.login(user.getNome(), PasswordUtils.digestPassword(user.getSenha()));
			if (login_user != null) {
				String token = JwTokenHelper.getInstance().generateToken(user.getNome(), user.getSenha());
				return Response
						.ok(token)
						.header(AUTHORIZATION, "Bearer " + token)
						.build();
			}
			
			return Response.status(NOT_FOUND).build();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(User user) {
		user = userBean.cadastrarUsuario(user.getNome(), PasswordUtils.digestPassword(user.getSenha()));
		if (user != null)
			return Response.ok(user).build();
		return Response.status(NOT_FOUND).build();
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") int id) {
		User user = userBean.getUsuario(id);
		if (user != null)
			return Response.ok(user).build();
		return Response.status(NOT_FOUND).build();
	}

	@GET
	@JsonTokenNeeded
	public Response findAllUsers() {
		String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		String token = authorizationHeader.substring("Bearer".length()).trim();
		System.out.println(token);

		List<User> allUsers = userBean.getAllUsers();
		if (allUsers != null)
			return Response.ok(allUsers).build();
		return Response.status(NOT_FOUND).build();
	}

}