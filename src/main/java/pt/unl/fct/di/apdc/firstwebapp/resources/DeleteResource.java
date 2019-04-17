package pt.unl.fct.di.apdc.firstwebapp.resources;

import java.util.logging.Logger;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

@Path("/delete")
public class DeleteResource {

	private static final Logger LOG = Logger.getLogger(RegisterResource.class.getName());
	private static final Gson g = new Gson();
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public DeleteResource() {
	}

	
	@DELETE
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(pt.unl.fct.di.apdc.firstwebapp.util.DeleteData data) {
		LOG.fine("Attempt to delete user: " + data.username);
		Key userKey = KeyFactory.createKey("User", data.username);
		try {
			Entity user = datastore.get(userKey);
			datastore.delete(userKey);
			LOG.info("Username " + data.username + " deleted with success.");
			return Response.ok().build();
		} catch (EntityNotFoundException e) {
			LOG.warning("Username " + data.username + " does not exist.");
			return Response.status(Status.FORBIDDEN).build();
		}

	}

}
