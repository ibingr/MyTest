package com.ibingr.xuer.mongo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ibingr.xuer.Service;

public class MyApp {

	public static void main(String[] args) {

		// For XML
		// ApplicationContext ctx = new
		// GenericXmlApplicationContext("mongo-config.xml");

		// For Annotation
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);

		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		User user = new User("mkyong", "password123");

		// save
		mongoOperation.save(user, "users");

		// find
		User savedUser = mongoOperation.findOne(
				new Query(Criteria.where("username").is("mkyong")), User.class,
				"users");

		System.out.println("savedUser : " + savedUser);

		// update
		mongoOperation.updateMulti(
				new Query(Criteria.where("username").is("mkyong")),
				Update.update("password", "new password"), "users");

		// find
		User updatedUser = mongoOperation.findOne(
				new Query(Criteria.where("username").is("mkyong")), User.class,
				"users");

		System.out.println("updatedUser : " + updatedUser);

		// delete
//		mongoOperation.remove(
//				new Query(Criteria.where("username").is("mkyong")), "users");

		// List
		List<User> listUser = mongoOperation.findAll(User.class, "users");
		System.out.println("Number of user = " + listUser.size());

	}

}