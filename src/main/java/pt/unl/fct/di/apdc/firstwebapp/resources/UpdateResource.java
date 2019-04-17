package pt.unl.fct.di.apdc.firstwebapp.resources;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

@Path("/update")
public class UpdateResource {	
	
	private static final Logger LOG = Logger.getLogger(RegisterResource.class.getName());
	private static final Gson g = new Gson();
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public UpdateResource() {}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
		public Response updateData(pt.unl.fct.di.apdc.firstwebapp.util.UpdateData data) {
			try {
				Key userKey = KeyFactory.createKey("User", data.username);
				Entity user = datastore.get(userKey);
				user.setProperty("username", data.username);
				user.setProperty("user_role", data.role);
				user.setProperty("email", data.email);
				user.setProperty("profile_type", data.profileType);
				user.setProperty("user_address", data.address);
				user.setProperty("user_alt_address", data.altAddress);
				user.setProperty("user_pc", data.postalCode);
				user.setProperty("user_city", data.city);
				user.setProperty("mobile_phone", data.mobilePhone);
				user.setProperty("home_phone", data.homePhone);
				datastore.put(user);
				LOG.info("Dados do utilizador " + data.username + " atualizados com sucesso.");
				return Response.ok().build();
			} catch (EntityNotFoundException e) {
				return Response.status(Status.BAD_REQUEST).entity("O utilizador não existe.").build();
			}

		}
		
		@PUT
		@Path("/password")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
		public Response updatePassword(pt.unl.fct.di.apdc.firstwebapp.util.UpdateData data) {
			try {
				Key userKey = KeyFactory.createKey("User", data.username);
				Entity user = datastore.get(userKey);
				user.setProperty("user_pwd", DigestUtils.sha512Hex(data.password));
				datastore.put(user);
				LOG.info("Password do utilizador " + data.username + " atualizados com sucesso.");
				return Response.ok().build();
			} catch (EntityNotFoundException e) {
				return Response.status(Status.BAD_REQUEST).entity("O utilizador não existe.").build();
			}

		}
		
		@PUT
		@Path("/role")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
		public Response updateRole(pt.unl.fct.di.apdc.firstwebapp.util.UpdateData data) {
			try {
				Key userKey = KeyFactory.createKey("User", data.username);
				Entity user = datastore.get(userKey);
				user.setProperty("user_role", "auser");
				datastore.put(user);
				LOG.info("Role do utilizador " + data.username + " atualizados com sucesso.");
				return Response.ok().build();
			} catch (EntityNotFoundException e) {
				return Response.status(Status.BAD_REQUEST).entity("O utilizador não existe.").build();
			}

		}

}
