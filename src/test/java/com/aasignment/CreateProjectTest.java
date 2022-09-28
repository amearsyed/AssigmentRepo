package com.aasignment;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateProjectTest {
	@Test
	public void createGetAndValidateProjectTest() throws Throwable
	{
//		JSONObject jobj=new JSONObject();
//		jobj.put("createdBy", "vivek");
//		jobj.put("projectName", "SDETgggg");
//		jobj.put("status", "created");
//		jobj.put("teamSize", 13);
//		
//		given()
//		.contentType(ContentType.JSON)
//		.body(jobj)
//		.when()
//		.post("http://localhost:8084/addProject")
//		.then().log().all();
		
		
		Response res = when().get("http://localhost:8084/projects");
		String projectName = res.jsonPath().get("[12].projectName");
		System.out.println("projectName is : "+projectName);
		
		Connection con=null;
		try {
		Driver d=new Driver();
		DriverManager.registerDriver(d);
		
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from project;");
		
	    while(rs.next())
	    {
	    	if(rs.getString(4).equals(projectName))
	    	{
	    		System.out.println("project is available in DB");
	    		break;
	    	}
	    }
		

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			System.out.println("Connection is closed");
		}
		
		
		
		
	}

}