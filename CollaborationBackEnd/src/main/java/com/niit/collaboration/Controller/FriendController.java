package com.niit.collaboration.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.FriendsDAO;
import com.niit.collaboration.model.Friends;
import com.niit.collaboration.model.User;

@RestController

public class FriendController {

	@Autowired
	FriendsDAO friendsDAO;
	
	@Autowired
	Friends friends;
	
	@RequestMapping(value="/myFriends",method = RequestMethod.GET)
    public ResponseEntity<List<Friends>> getMyFriends(HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		List<Friends> myFriends = friendsDAO.getMyFriend(loggedInUser.getId());
		return new ResponseEntity<List<Friends>>(myFriends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addFriend/{friendID}", method = RequestMethod.POST)
	public ResponseEntity<Friends> sendFriendRequest(@PathVariable("friendID") String friendID,HttpSession session) {
		
		User loggedInUser=(User) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getId());
		friends.setFriendID(friendID);
		friends.setStatus("N");
		friends.setIsOnline('Y');
		friendsDAO.save(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/unFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> unFriend(@PathVariable("friendID") String friendID, HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getId());
		friends.setFriendID(friendID);
		friends.setStatus("U");//N -> New , R-> Rejected , A -> Accepted
		friendsDAO.save(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> rejectFriendFriendRequest(@PathVariable("friendID") String friendID, HttpSession session){
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getId());
		friends.setFriendID(friendID);
		friends.setStatus("R");
		friendsDAO.update(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/getMyFriendRequests",method = RequestMethod.GET)
	public ResponseEntity<Friends> getMyFriendRequests(HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friendsDAO.getNewFriendRequests(loggedInUser.getId());
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> acceptFriendRequest(@PathVariable("friendID") String friendID, HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getId());
		friends.setFriendID(friendID);
		friends.setStatus("A");
		friendsDAO.update(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	//Just for testing purpose from restclient.
	@RequestMapping(value="/myFriends/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Friends>> getMyFriendsTemp(@PathVariable("id") String id) {
	
		List<Friends> myFriends = friendsDAO.getMyFriend(id);
		return new ResponseEntity<List<Friends>>(myFriends, HttpStatus.OK);
	}
	
	
}
