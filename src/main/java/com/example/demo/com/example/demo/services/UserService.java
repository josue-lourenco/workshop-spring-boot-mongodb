package com.example.demo.com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.com.example.demo.domain.User;
import com.example.demo.com.example.demo.dto.UserDTO;
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
		
		Optional<User> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert( User obj ) {
		return repo.insert(obj);
	}
	
	public void delete( String id ) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO( UserDTO objDto ) {
		return new User( objDto.getId(), objDto.getName(), objDto.getEmail() );
	}
}
