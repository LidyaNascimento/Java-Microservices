package userService;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import userService.User;

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserService {
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/appweb4/api/users/" + id);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
    } 
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/appweb4/api/users/login");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));	
    	
		return response;
    	//return Response.ok(Entity.entity(newUser, MediaType.APPLICATION_JSON).getEntity()).build();
    }
    
    @POST
    @Path("/cadastrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(User newUser) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/appweb4/api/users/cadastrar");
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
				
		return response;
    	//return Response.ok(Entity.entity(newUser, MediaType.APPLICATION_JSON).getEntity()).build();
    }
}
