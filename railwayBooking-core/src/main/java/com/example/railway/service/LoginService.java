package com.example.railway.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.railway.model.User;
import com.example.railway.repository.LoginRepository;

@Service

public class LoginService {
	@Value("${spring.datasource.url}")
    private String dbUrl;
	@Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
    private static Environment environment;
	
	
	public int authenticate(User user) throws Exception {
		if(user.getUsername()!=null && user.getPassword()!=null) {
			String username=user.getUsername();
			String password=user.getPassword();
			try {

				Connection connection=DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

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

	public User createUser(User user)throws Exception {
		User newUser=new User();
		if(user!=null) {
			
			try {
				Connection connection=DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
				String query = "SELECT * from users where email=?";
				PreparedStatement stmt = connection.prepareStatement(query);

				stmt.setString(1, user.getEmail());
				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					result.close();
					stmt.close();
					connection.close();
					throw new Exception("user with this email already exists");
				}
				else {
					newUser.setEmail(user.getEmail());
					newUser.setPassword(user.getPassword());
					newUser.setUsername(user.getUsername());
					newUser.setUserType(user.getUserType());
					loginRepository.save(newUser);
					result.close();
					stmt.close();
					connection.close();
					return newUser;
					
				}


			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newUser;
		
		

	}

}
