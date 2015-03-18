package models;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

@Entity
public class Rule {

	@Id
	public ObjectId id;
	public String name;

	@Reference
	public User user;
}
