package edu.sabanciuniv.it592.domain;


import javax.persistence.Entity;

@Entity
public class EyeGlasses extends Product{

	private String eyeglasses_colorcode;
	private String eyeglasses_modelcode;
	private boolean reading_glasses;
	
	public EyeGlasses() {
		// TODO Auto-generated constructor stub
	}

	public EyeGlasses(String eyeglasses_colorcode, String eyeglasses_code) {
		super();
		this.eyeglasses_colorcode = eyeglasses_colorcode;
		this.eyeglasses_modelcode = eyeglasses_code;
	}

	public String getEyeglasses_modelcode() {
		return eyeglasses_modelcode;
	}

	public void setEyeglasses_modelcode(String eyeglasses_modelcode) {
		this.eyeglasses_modelcode = eyeglasses_modelcode;
	}

	public String getEyeglasses_colorcode() {
		return eyeglasses_colorcode;
	}

	public void setEyeglasses_colorcode(String eyeglasses_colorcode) {
		this.eyeglasses_colorcode = eyeglasses_colorcode;
	}

	public boolean isReading_glasses() {
		return reading_glasses;
	}

	public void setReading_glasses(boolean reading_glasses) {
		this.reading_glasses = reading_glasses;
	}
	
}
