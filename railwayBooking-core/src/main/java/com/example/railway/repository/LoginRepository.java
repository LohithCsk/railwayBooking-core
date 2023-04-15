package com.example.railway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.railway.model.User;
import com.sun.mail.imap.protocol.ID;

@Repository
public interface LoginRepository extends JpaRepository<User, String>{
	
	//public void authenticate(User user);
}
