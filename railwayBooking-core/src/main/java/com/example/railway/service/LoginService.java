package com.example.railway.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.example.railway.model.User;

@Service
public class LoginService {
	private static final String DB_URL="dbc:mysql://localhost:3306/railway_project";
	private static final String DB_USERNAME="root";
	private static final String DB_PASSWORD="Bhargav@0405";
	public int authenticate(User user) throws Exception {
		if(user.getUsername()!=null && user.getPassword()!=null) {
			String username=user.getUsername();
			String password=user.getPassword();
			try {
				Connection connection=DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				
				String query = "SELECT * from users where username = ? and password =?";
				PreparedStatement stmt = connection.prepareStatement(query);
				
				stmt.setString(1, username);
				stmt.setString(2, password);
				
				ResultSet result = stmt.executeQuery();
				
				if(result.next()) {
					result.close();
					stmt.close();
					connection.close();
					return 1;
				}
				
				result.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		else {
			throw new Exception("User object is null");
		}
		return 	0;
				
	}

}
