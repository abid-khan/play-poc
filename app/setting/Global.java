package setting;

import java.net.UnknownHostException;

import models.User;
import play.GlobalSettings;
import play.Logger;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import controllers.MorphiaObject;

public class Global extends GlobalSettings {
	@Override
	public void onStart(play.Application arg0) {
		super.beforeStart(arg0);
		Logger.debug("** onStart **");
		try {
			MorphiaObject.mongo = new Mongo("127.0.0.1", 27017);
		} catch (UnknownHostException e) {
			Logger.error("Failed to connect DB", e);
		}
		MorphiaObject.morphia = new Morphia();
		MorphiaObject.datastore = MorphiaObject.morphia.createDatastore(
				MorphiaObject.mongo, "play", "play", "welcome".toCharArray());
		MorphiaObject.datastore.ensureIndexes();
		MorphiaObject.datastore.ensureCaps();

		Logger.info("** Morphia datastore: " + MorphiaObject.datastore.getDB());
		createAdminUser();
	}

	public void createAdminUser() {
		Logger.info("Creating Admin user");
		if (User.findByEmail("abid.khan@talentica.com") == null) {
			Logger.info("Admin user does not exists creating it...");
			User user = new User();
			user.email = "abid.khan@talentica.com";
			user.name = "abid";
			user.password = "welcome";
			User.create(user);
		}

	}

}
