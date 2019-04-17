package pt.unl.fct.di.apdc.firstwebapp.resources;

import java.util.logging.Logger;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
import com.google.appengine.api.datastore.Transaction;
import com.google.gson.Gson;

@Path("/register")
public class RegisterResource {

	private static final Logger LOG = Logger.getLogger(RegisterResource.class.getName());
	private static final Gson g = new Gson();
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public RegisterResource() {
	}

	@POST
	@Path("/v1")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response doRegistrationV1(pt.unl.fct.di.apdc.firstwebapp.util.LoginData data) {
		Entity user = new Entity("User", data.username);
		user.setProperty("user_pwd", DigestUtils.sha512Hex(data.password));
		user.setUnindexedProperty("user_creation_time", new Date());
		datastore.put(user);
		LOG.info("O utilizador " + data.username + " foi registado com sucesso.");
		return Response.ok().build();

	}

	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response doRegistrationV2(pt.unl.fct.di.apdc.firstwebapp.util.RegisterData data) {
		Transaction txn = datastore.beginTransaction();

		try {
			Key userKey = KeyFactory.createKey("User", data.username);
			Entity user = datastore.get(userKey);
			txn.rollback();
			return Response.status(Status.BAD_REQUEST).entity("O utilizador já existe.").build();
		} catch (EntityNotFoundException e) {
			Entity user = new Entity("User", data.username);
			user.setProperty("user_role", data.role);
			user.setProperty("user_pwd", DigestUtils.sha512Hex(data.password));
			user.setProperty("email", data.email);
			user.setProperty("profile_type", data.profileType);
			user.setProperty("user_address", data.address);
			user.setProperty("user_alt_address", data.altAddress);
			user.setProperty("user_pc", data.postalCode);
			user.setProperty("user_city", data.city);
			user.setProperty("mobile_phone", data.mobilePhone);
			user.setProperty("home_phone", data.homePhone);
			user.setUnindexedProperty("user_creation_time", new Date());
			datastore.put(txn, user);
			LOG.info("Utilizador " + data.username + " registado com sucesso.");
			txn.commit();
			return Response.ok().build();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}

		}

	}
	
	@POST
	@Path("/advanced")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response doRegistrationGBO(pt.unl.fct.di.apdc.firstwebapp.util.RegisterData data) {
		Transaction txn = datastore.beginTransaction();

		try {
			Key userKey = KeyFactory.createKey("User", data.username);
			Entity user = datastore.get(userKey);
			txn.rollback();
			return Response.status(Status.BAD_REQUEST).entity("O utilizador já existe.").build();
		} catch (EntityNotFoundException e) {
			Entity user = new Entity("User", data.username);
			user.setProperty("user_role", data.role);
			user.setProperty("user_pwd", DigestUtils.sha512Hex(data.password));
			user.setProperty("email", data.email);
			user.setProperty("profile_type", data.profileType);
			user.setProperty("user_address", data.address);
			user.setProperty("user_alt_address", data.altAddress);
			user.setProperty("user_pc", data.postalCode);
			user.setProperty("user_city", data.city);
			user.setProperty("mobile_phone", data.mobilePhone);
			user.setProperty("home_phone", data.homePhone);
			user.setUnindexedProperty("user_creation_time", new Date());
			datastore.put(txn, user);
			LOG.info("Utilizador " + data.username + " registado com sucesso.");
			txn.commit();
			return Response.ok().build();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}

		}

	}
	
	@POST
	@Path("/gbo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response doRegistrationGS(pt.unl.fct.di.apdc.firstwebapp.util.RegisterData data) {
		Transaction txn = datastore.beginTransaction();

		try {
			Key userKey = KeyFactory.createKey("User", data.username);
			Entity user = datastore.get(userKey);
			txn.rollback();
			return Response.status(Status.BAD_REQUEST).entity("O utilizador já existe.").build();
		} catch (EntityNotFoundException e) {
			Entity user = new Entity("User", data.username);
			user.setProperty("user_role", "gbo");
			user.setProperty("user_pwd", DigestUtils.sha512Hex(data.password));
			user.setProperty("email", data.email);
			user.setProperty("profile_type", data.profileType);
			user.setProperty("user_address", data.address);
			user.setProperty("user_alt_address", data.altAddress);
			user.setProperty("user_pc", data.postalCode);
			user.setProperty("user_city", data.city);
			user.setProperty("mobile_phone", data.mobilePhone);
			user.setProperty("home_phone", data.homePhone);
			user.setUnindexedProperty("user_creation_time", new Date());
			datastore.put(txn, user);
			LOG.info("Utilizador " + data.username + " registado com sucesso.");
			txn.commit();
			return Response.ok().build();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}

		}

	}

}
