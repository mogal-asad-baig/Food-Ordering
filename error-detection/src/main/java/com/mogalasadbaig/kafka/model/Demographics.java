package com.mogalasadbaig.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Demographics extends Object{

	
	private String name;
	private String city;
	private String street;
	private String state;
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Demographics))
			return false;
		Demographics dg = (Demographics)obj;
		if(this.getName().equals(dg.getName()) && this.getCity().equals(dg.getCity()) && this.getStreet().equals(dg.getStreet()) && this.getState().equals(dg.getState()) )
		{
			return true;
		}
		else
			return false;
	}
    
}
