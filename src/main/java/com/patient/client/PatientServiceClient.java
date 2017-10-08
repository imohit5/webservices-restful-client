package com.patient.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.patient.persistence.Patient;

public class PatientServiceClient {

	private static final String ENDPOINT = "http://localhost:8080/webservices-restful-demo/services/patientService";

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		// To check status
		WebTarget target = client
				.target("http://localhost:8080/webservices-restful-demo/services/patientService/patients/12");
		Response response = target.request().get();
		System.out.println(response.getStatus());

		// To get response
		WebTarget targetGet = client.target(ENDPOINT).path("/patients").path("/{id}").resolveTemplate("id", 12);
		Patient patient = targetGet.request().get(Patient.class);
		System.out.println(patient.getId());
		System.out.println(patient.getName());

		// To put response
		WebTarget targetPut = client.target(ENDPOINT).path("/patients");
		patient.setName("jim");
		Response put = targetPut.request().put(Entity.entity(patient, MediaType.APPLICATION_XML));
		System.out.println(put.getStatus());
		put.close();

		// To post response
		WebTarget targetPost = client.target(ENDPOINT).path("/patients");
		Patient patient2 = new Patient();
		patient2.setName("jam");
		Patient post = targetPut.request().post(Entity.entity(patient2, MediaType.APPLICATION_XML), Patient.class);
		System.out.println(post.getId());
		System.out.println(post.getName());

		// To delete response
		WebTarget targetDelete = client.target(ENDPOINT).path("/patients").path("/{id}").resolveTemplate("id", 12);
		Response delete = targetGet.request().delete();
		System.out.println(delete.getStatus());
		delete.close();

		client.close();

	}
}
