package com.mogalasadbaig.kafka.restaurantowner.entity;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mogalasadbaig.kafka.restaurantowner.model.Demographics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "restaurants-collection-1")
public class Restaurant{

	// check how to make sure it is not nuul
	
	@Id
	private Demographics demographics;

	@NotNull
	private String phone_number;
	
	
	private String availability;
	
	private Map<String, Boolean> menu;

	

	public boolean equals(Restaurant restaurant)
	{
		if(this.getDemographics().equals(restaurant.getDemographics()))
		{
			return true;
		}
		
		return false;
	}
	
//	@Override
//	public int hashCode()
//	{
//		String s = name + street + city + state + phone_number;
//		
//	    
//	}
//	
//	public static byte[] getSHA(String input) throws NoSuchAlgorithmException
//    {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
// 
//        
//        return md.digest(input.getBytes(StandardCharsets.UTF_8));
//    }
//	
//	public static String toHexString(byte[] hash)
//    {
//        BigInteger number = new BigInteger(1, hash);
// 
//        StringBuilder hexString = new StringBuilder(number.toString(16));
// 
//        while (hexString.length() < 64)
//        {
//            hexString.insert(0, '0');
//        }
// 
//        return hexString.toString();
//    }
// 
	
	
	
}
