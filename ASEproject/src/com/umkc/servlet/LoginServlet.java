package com.umkc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.umkc.dao.RegisterDao;
import com.umkc.pojo.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
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
		
		Login loginPojo = new Login();
		
		loginPojo.setUsername(request.getParameter("user_name"));
		
		System.out.println("user_name"+request.getParameter("use_name"));
		loginPojo.setPassword(request.getParameter("password"));
		
		BasicDBObject loginDBObject = new BasicDBObject("user_name",loginPojo.getUsername());
		
		
		RegisterDao loginDAO = new RegisterDao();
		DBCursor loginDBCursor = loginDAO.retrieveDocument(loginDBObject);
		
//		DBObject result = loginDBCursor.getQuery();
//		
//		System.out.println(result.get("username"));
//		System.out.println(result.get("password"));
		
		String password= "";
		
		while(loginDBCursor.hasNext()){
			BasicDBObject outputObject = (BasicDBObject) loginDBCursor.next();
			
			password = outputObject.getString("Password");
			
			System.out.println("password"+password);
		}

		
		if(loginPojo.getPassword().equals(password)){
			System.out.println("you are here");
			request.setAttribute("username", request.getParameter("user_name"));
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "login failed.");
			request.getRequestDispatcher("Login.html").forward(request, response);
		}
	}
		
}