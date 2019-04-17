package pt.unl.fct.di.apdc.firstwebapp.resources;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

import pt.unl.fct.di.apdc.firstwebapp.util.AuthToken;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class LoginResource {

	/**
	 * Logger Object - util para gerar logs que reportam o que acontece na execucao
	 * do nosso serviço
	 */
	private static final Logger LOG = Logger.getLogger(LoginResource.class.getName());

	private final Gson g = new Gson();
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	/*
	 * construtores vazios permitem que as classes deste tipo sejam mais facilmente
	 * instanciadas pelo jersey
	 */
	public LoginResource() {
	}

	@POST
	@Path("/v1")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response doLoginV1(pt.unl.fct.di.apdc.firstwebapp.util.LoginData data) {
		LOG.fine("Attempt to login user: " + data.username);

		Key userKey = KeyFactory.createKey("User", data.username);
		try {
			Entity user = datastore.get(userKey);
			String hashedPWD = (String) user.getProperty("user_pwd");
			if (hashedPWD.equals(DigestUtils.sha512Hex(data.password))) {
				AuthToken token = new AuthToken(data.username, (String) user.getProperty("user_role"));
				LOG.info("Login do utilizador " + data.username + " feito com sucesso.");
				return Response.ok(g.toJson(token)).build();
			} else {
				LOG.warning("A password do utilizador: " + data.username + " está errada.");
				return Response.status(Status.FORBIDDEN).build();

			}
		} catch (EntityNotFoundException e) {
			// Username does not exist
			LOG.warning("Login falhado. O utilizador " + data.username + " não existe.");
			return Response.status(Status.FORBIDDEN).build();
		}

	}
	
	
	//provavelmente useless
	@GET
	@Path("/{username}")
	public Response checkUsernameAvaliable(@PathParam("username") String username) {
		if (!username.equals("jleitao")) {
			return Response.ok().entity(g.toJson(false)).build();
		} else {
			return Response.ok().entity(g.toJson(true)).build();
		}
	}

}
