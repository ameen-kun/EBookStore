package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.PreferenceReq;
import com.example.demo.models.Bookmark;
import com.example.demo.models.Cart;
import com.example.demo.models.EBook;
import com.example.demo.models.Library;
import com.example.demo.models.Order;
import com.example.demo.models.ReviewReq;
import com.example.demo.models.User;
import com.example.demo.models.Wishlist;
import com.example.demo.service.UserService;
import com.example.demo.vo.Review;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/cart/{id}")
	Cart getCart(@PathVariable int id) {
		return userService.getCart(id);
	}
	
	@PutMapping("/addBalance/{id}")
	User addBalance(@PathVariable int id) {
		return userService.addBal(id);
	}
	
	@GetMapping("/wallet/{id}")
	double getWallet(@PathVariable int id) {
		return userService.getWallet(id);
	}
	
	@GetMapping("/forYou/{id}")
	List<EBook> getForYou(@PathVariable int id){
		return userService.getForYou(id);
	}
	
	@GetMapping("/bookmark/{id}")
	List<Bookmark> getBookmarks(@PathVariable int id){
		return userService.getBookmarks(id);
	}
	
	@PutMapping("/addPrefs/{userid}")
	User addPreferences(@PathVariable int userid,@RequestBody PreferenceReq x) {
		System.out.println("Here");
		return userService.addPreferences(userid,x.getPrefs());
	}
	
	@GetMapping("/getPrefs/{userid}")
	List<String> getPreferences(@PathVariable int userid){
		return userService.getPreferences(userid);
	}
	
	@PostMapping("/addbookmark/{id}")
	User addBookmarks(@PathVariable int id,@RequestBody Bookmark x) {
		return userService.addBookmark(id,x);
	}
	
	@GetMapping("/wishlist/{id}")
	Wishlist getWishlist(@PathVariable int id) {
		return userService.getWishlist(id);
	}
	@GetMapping("/library/{id}")
	Library getLibrary (@PathVariable int id) {
		return userService.getLibrary(id);
	}
	
	@PostMapping("/moveBooks/{userid}")
	Library moveBooks (@PathVariable int userid) {
		return userService.moveBooks(userid);
	}
	
	@PostMapping("/addToCart/{id}")
	Cart addToCart (@RequestBody EBook x,@PathVariable int id) {
		return userService.addToCart(x, id);
	}
	
	@PostMapping("/addToWishlist/{id}")
	Wishlist addToWishlist (@RequestBody EBook x,@PathVariable int id) {
		return userService.addToWishlist(x, id);
	}
	
	@PostMapping("/addToLibrary/{id}")
	Library addToLibrary (@RequestBody EBook x,@PathVariable int id) {
		return userService.addToLibrary(x, id);
	}
	
	@DeleteMapping("/deleteFromCart/{userid}/{bookid}")
	Cart deleteFromCart(@PathVariable int userid,@PathVariable int bookid) {
		return userService.removeFromCart(userid,bookid);
	}
	
	@DeleteMapping("/deleteFromWishlist/{userid}/{bookid}")
	Wishlist deleteFromWishlist(@PathVariable int userid,@PathVariable int bookid) {
		return userService.removeFromWishlist(userid,bookid);
	}
	
	@DeleteMapping("/deleteFromBookMark/{userid}/{bookid}")
	User deleteFromBookMark(@PathVariable int userid,@PathVariable int bookid) {
		return userService.removeFromBookMark(userid,bookid);
	}
	
	@GetMapping("/orders/{userid}")
	List<Order> getOrders(@PathVariable int userid){
		return userService.getOrders(userid);
	}
	
	@GetMapping("/bookInCart/{userid}/{bookid}")
	boolean isBookInCart(@PathVariable int userid,@PathVariable int bookid) {
		return userService.isBookInCart(userid,bookid);
	}
	
	@GetMapping("/bookInWish/{userid}/{bookid}")
	boolean isBookInWish(@PathVariable int userid,@PathVariable int bookid) {
		return userService.isBookInWish(userid,bookid);
	}
	
	@GetMapping("/bookInLibrary/{userid}/{bookid}")
	boolean isBookInLibrary(@PathVariable int userid,@PathVariable int bookid) {
		return userService.isBookInLib(userid,bookid);
	}
	
	@PostMapping("/addReview")
	public void addReview(@RequestBody ReviewReq reviewreq){
		userService.addReview(reviewreq);
	}
	
	@GetMapping("/getBookReviews/{bookid}")
	public List<Review> getBookReviews(@PathVariable int bookid){
		return userService.getBookReviews(bookid);
	}
	
	@GetMapping("/getUserReviews/{userid}")
	public List<Review> getUserReviews(@PathVariable int userid){
		return userService.getUserReviews(userid);
	}
}
