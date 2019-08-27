package edu.sabanciuniv.it592.domain;

import javax.persistence.Entity;

@Entity
public class SunGlasses extends Product{
	
	private String sunglasses_colorcode;
	private String sunglasses_modelcode;
	
	
	
	public String getSunglasses_colorcode() {
		return sunglasses_colorcode;
	}
	public void setSunglasses_colorcode(String sunglasses_colorcode) {
		this.sunglasses_colorcode = sunglasses_colorcode;
	}
	public String getSunglasses_modelcode() {
		return sunglasses_modelcode;
	}
	public void setSunglasses_modelcode(String sunglasses_modelcode) {
		this.sunglasses_modelcode = sunglasses_modelcode;
	}
	
	
	
}
