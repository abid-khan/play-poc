package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import play.Logger;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.query.Query;

import controllers.MorphiaObject;

@Entity
public class User {
	@Id
	public ObjectId id;
	public String email;
	public String name;
	public String password;

	public static User authenticate(String email, String password) {
		if (MorphiaObject.datastore != null) {
			Query<User> query = MorphiaObject.datastore.createQuery(User.class)
					.filter("email =", email).filter("password =", password);
			return (User) query.asList().get(0);
		} else {
			return null;
		}
	}

	public static List<User> all() {
		if (MorphiaObject.datastore != null) {
			return MorphiaObject.datastore.find(User.class).asList();
		} else {
			return new ArrayList<User>();
		}
	}

	public static User findByEmail(String email) {
		if (MorphiaObject.datastore != null) {
			User user = MorphiaObject.datastore.find(User.class).field("email")
					.equal(email).get();
			return user;
		} else {
			return null;
		}

	}

	public static void create(User user) {
		Logger.info("Create request received for " + user.toString());
		MorphiaObject.datastore.save(user);
	}

	public static void delete(String idToDelete) {
		User toDelete = MorphiaObject.datastore.find(User.class).field("_id")
				.equal(new ObjectId(idToDelete)).get();
		if (toDelete != null) {
			Logger.info("toDelete: " + toDelete);
			MorphiaObject.datastore.delete(toDelete);
		} else {
			Logger.debug("ID No Found: " + idToDelete);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name
				+ ", password=" + password + "]";
	}

}
