package com.umkc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.umkc.dao.RegisterDao;
import com.umkc.pojo.RegisterPojo;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		//Creating the object to POJO Class 
				RegisterPojo registerPoJO = new RegisterPojo();
				//Receiving the parameters from the request to process
				registerPoJO.setUserName(request.getParameter("user_name"));
				registerPoJO.setFirstName(request.getParameter("first_name"));
				registerPoJO.setLastName(request.getParameter("last_name"));
				registerPoJO.setPassword(request.getParameter("password"));
				registerPoJO.setConfirmPassword(request.getParameter("password_confirmation"));
				registerPoJO.setEmailID(request.getParameter("email"));
				
				String username = request.getParameter("user_name");
				String firstname = request.getParameter("first_name");
				String lastname = request.getParameter("last_name");
				String password = request.getParameter("password");
				String cnfpassword = request.getParameter("password_confirmation");
				String emailID = request.getParameter("email");
				//Creating the Basic DB object Mongo Labs
				
				BasicDBObject basicDBObject = new BasicDBObject();
			
					basicDBObject.put("user_name", username);
					basicDBObject.put("first_name", firstname);
					basicDBObject.put("last_name", lastname);
					basicDBObject.put("Password", password);
					basicDBObject.put("email", emailID);
				
				//Creating DAO Object to send and passsing JSON Object to Mongo Labs
				RegisterDao registerDAO = new RegisterDao();
			
				//Calling the method sendDataToMongoLabs by passing prepared JSON to MongoLabs
				boolean success=registerDAO.sendDataToMongoDB(basicDBObject);
				
				if (success){
					//Navingating the page to login.jsp if success
					request.setAttribute("status", "Registration successful");
				request.getRequestDispatcher("Login.html").forward(request, response);	
				}
				else{
					//Navigating the page to Registration.jsp if failed
					request.setAttribute("status", "Registration Failed");
					request.getRequestDispatcher("Register.html").forward(request, response);
				}
				
			}

	
}
