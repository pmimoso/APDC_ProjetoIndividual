package pt.unl.fct.di.apdc.firstwebapp.resources;

import java.util.logging.Logger;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.gson.Gson;

@Path("/user")
public class UserResource {
	
	public UserResource() { }
	
	private static final Logger LOG = Logger.getLogger(RegisterResource.class.getName());
	private static final Gson g = new Gson();
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	@GET
	@Path("/info")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserData(pt.unl.fct.di.apdc.firstwebapp.util.RegisterData data) {
		return null;
		
	}

}
