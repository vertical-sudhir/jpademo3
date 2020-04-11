package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Author;
import com.example.demo.repositeries.AuthorRepo;


@RestController
public class AuthorController {

	@Autowired
	AuthorRepo authorepo;
	
	
	@RequestMapping(value="/Authors", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) 
	public List<Author> getAuthors()
	{
		return (List<Author>)authorepo.findAll();
	}
/*
	@RequestMapping(value = "/Authors", method = RequestMethod.POST)
	public String createNewAuthor1(@RequestBody Author Author)
	{
		authorepo.save(Author);
		
		return "New User Created...";
	}

*/	
	@RequestMapping(value = "/Authors", method = RequestMethod.POST)
	public ResponseEntity<Author> createNewAuthor(@RequestBody Author Author)
	{
	   Author newlycreated =	authorepo.save(Author);
		
		ResponseEntity res = new ResponseEntity<Author>(newlycreated, HttpStatus.CREATED);
		return res;
	}
	
/*	@RequestMapping(value = "/Authors", method = RequestMethod.PATCH)
	public ResponseEntity<Author> updateAuthor(@RequestBody Author Author)
	{
	    Author newlycreated =	authorepo.save(Author);
		
		ResponseEntity res = new ResponseEntity<Author>(newlycreated, HttpStatus.ACCEPTED);
		return res;
	}

	@RequestMapping(value = "/Authors/{abc}", method = RequestMethod.PATCH)
	public ResponseEntity<Author> updateAuthorById(@PathVariable(name = "abc") Long id, @RequestBody Author Author)
	{
		Author.setId(id);
	//	Author.setName(firstname);
		Author updatedAuthor =	authorepo.save(Author);
		
		ResponseEntity res = new ResponseEntity<Author>(updatedAuthor, HttpStatus.ACCEPTED);
		return res;
	}
*/
	@RequestMapping(value = "/Authors/{abc}", method = RequestMethod.PATCH)
	public ResponseEntity<Author> updateAuthorByIdIfExist(@PathVariable(name = "abc") Long id, @RequestBody Author Author)
	{
		
		if(authorepo.existsById(id))
		{
			System.out.println("In If..****************************************");
			Author.setId(id);
			Author updatedAuthor =	authorepo.save(Author);
			
			ResponseEntity res = new ResponseEntity<Author>(updatedAuthor, HttpStatus.ACCEPTED);
			return res;
		}
		
		else
		{
			System.out.println("in else*************************************************");
			ResponseEntity res = new ResponseEntity<Author>(Author, HttpStatus.NOT_FOUND);
			return res;
		}
	}

	
	@RequestMapping(value = "/Authors/{abc}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAuthorByIdIfExist(@PathVariable(name = "abc") Long id)
	{
		
		if(authorepo.existsById(id))
		{
			System.out.println("In If..Delete****************************************");
			authorepo.deleteById(id);
			
			
			ResponseEntity res = new ResponseEntity<String>("Author Deleted", HttpStatus.OK);
			return res;
		}
		
		else
		{
			System.out.println("in else Delete*************************************************");
			ResponseEntity res = new ResponseEntity<String>("Author Not Found", HttpStatus.NOT_FOUND);
			return res;
		}
	}


}
