package edu.sabanciuniv.it592.domain;

import javax.persistence.Entity;

@Entity
public class Glass extends Product{

	private String type;// Organik / Mineral
	private boolean antirefle;// +,-
	private boolean number_sferik_konkav;// +,-
	private boolean number_cyl_sferik_konkav;
	private double number;
	private double number_cyl;
	private String color;// Beyaz / Colormatik
	private boolean right_eye;
	private int aks;
	private boolean reading_glasses;
	
	
	
	
	public Glass() {
		// TODO Auto-generated constructor stub
	}


	public Glass(String type, boolean antirefle, boolean number_sferik_konkav, boolean number_cyl_sferik_konkav,
			double number, double number_cyl, String color, boolean right_eye, int aks, boolean reading_glasses) {
		super();
		this.type = type;
		this.antirefle = antirefle;
		this.number_sferik_konkav = number_sferik_konkav;
		this.number_cyl_sferik_konkav = number_cyl_sferik_konkav;
		this.number = number;
		this.number_cyl = number_cyl;
		this.color = color;
		this.right_eye = right_eye;
		this.aks = aks;
		this.reading_glasses = reading_glasses;
	}


	public boolean isNumber_sferik_konkav() {
		return number_sferik_konkav;
	}



	public void setNumber_sferik_konkav(boolean number_sferik_konkav) {
		this.number_sferik_konkav = number_sferik_konkav;
	}


	public boolean isNumber_cyl_sferik_konkav() {
		return number_cyl_sferik_konkav;
	}

	public void setNumber_cyl_sferik_konkav(boolean number_cyl_sferik_konkav) {
		this.number_cyl_sferik_konkav = number_cyl_sferik_konkav;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAntirefle() {
		return antirefle;
	}

	public void setAntirefle(boolean antirefle) {
		this.antirefle = antirefle;
	}

	public boolean isSferik_konkav() {
		return number_sferik_konkav;
	}

	public void setSferik_konkav(boolean sferik_konkav) {
		this.number_sferik_konkav = sferik_konkav;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public double getNumber_cyl() {
		return number_cyl;
	}

	public void setNumber_cyl(double number_cyl) {
		this.number_cyl = number_cyl;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isRight_eye() {
		return right_eye;
	}

	public void setRight_eye(boolean right_eye) {
		this.right_eye = right_eye;
	}

	public int getAks() {
		return aks;
	}

	public void setAks(int aks) {
		this.aks = aks;
	}

	public boolean isReading_glasses() {
		return reading_glasses;
	}

	public void setReading_glasses(boolean reading_glasses) {
		this.reading_glasses = reading_glasses;
	}
	
	
	
}
