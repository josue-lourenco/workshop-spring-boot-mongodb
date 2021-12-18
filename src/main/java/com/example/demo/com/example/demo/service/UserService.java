package com.example.demo.com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.com.example.demo.domain.User;
import com.example.demo.com.example.demo.repository.UserRepository;
import com.example.demo.com.example.demo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}

	public User findById(String id) {
		
		User user = repo.findOne(id);
		
		if(user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return user; 
	}

}
