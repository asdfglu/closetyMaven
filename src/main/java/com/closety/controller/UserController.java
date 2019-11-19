package com.closety.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.closety.model.User;
import com.closety.persistencia.DB;
import com.closety.persistencia.UserDao;

@Controller    
@RequestMapping(path="/user/") 
public class UserController {

	private UserDao userDao;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<User> cadastrarUser(@RequestBody User user) {
		userDao = new UserDao(DB.getConnection());
		System.out.println("NOME: "+user.getUsername());
		user = userDao.insert(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarUser(@RequestBody User user) {
		userDao = new UserDao(DB.getConnection());
		userDao.update(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirUser(@PathVariable long id) {
		userDao = new UserDao(DB.getConnection());
		userDao.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
		userDao = new UserDao(DB.getConnection());
		List<User> listaUsers = userDao.findAll();		
		return new ResponseEntity<List<User>>(listaUsers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findById(@PathVariable long id) {
		userDao = new UserDao(DB.getConnection());
		User user = userDao.findById(id);
		if(user!=null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{		
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "{username}/{password}", method = RequestMethod.GET)
	public ResponseEntity<User> findByLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
		System.out.println("LOGIN: "+username+" SENHA: "+password);
		userDao = new UserDao(DB.getConnection());
		User user = userDao.findByLogin(username, password);
		if(user!=null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		}
	}
}

