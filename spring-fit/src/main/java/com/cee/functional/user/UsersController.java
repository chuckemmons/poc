/**
 * 
 */
package com.cee.functional.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chuck
 *
 */
@Controller
public class UsersController {
	
	@Autowired
	UserRepository repo;
	
	@GetMapping("/")
    public ModelAndView dashboard() {
    	final ModelAndView model = new ModelAndView();
    	//model.addObject("users", getUsers());
    	model.addObject("users", repo.findAll());
    	model.setViewName("users");
    	return model;
    }

	/*private List<User> getUsers() {
		final User user = new User();
		user.setEmail("johndoe123@gmail.com");
		user.setName("John Doe");
		final User user1 = new User();
		user1.setEmail("amitsingh@yahoo.com");
		user1.setName("Amit Singh");
		final User user2 = new User();
		user2.setEmail("bipulkumar@gmail.com");
		user2.setName("Bipul Kumar");
		final User user3 = new User();
		user3.setEmail("prakashranjan@gmail.com");
		user3.setName("Prakash Ranjan");
		return Arrays.asList(user, user1, user2, user3);
	}*/
}
