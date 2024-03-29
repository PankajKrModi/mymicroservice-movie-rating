package io.mymicroservice.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.mymicroservice.moviecatalogservice.models.Rating;
import io.mymicroservice.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfoService {

	@Autowired
	RestTemplate restTemplate ; 
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating",
			commandProperties = {
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
						@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
						@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"),
						@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			})
	public UserRating getUserRating(String userId) {
		UserRating userRating=
		restTemplate.
				getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class); 			
		return userRating;
	}
	
	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId("0");
		userRating.setUserRating(Arrays.asList(new Rating("0",0)));
		return userRating;		
	}
}
