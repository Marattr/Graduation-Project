package edu.sabanciuniv.it592.domain;

import javax.persistence.Entity;


@Entity
public class Lens extends Product{
	
	private boolean number_sferik_konkav;
	private boolean number_cyl_sferik_konkav;
	private double number;
	private double number_cyl;
	private boolean right_eye;
	private int aks;
	
	
	
	
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
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
	public double getNumber_cyl() {
		return number_cyl;
	}
	public void setNumber_cyl(double number_cyl) {
		this.number_cyl = number_cyl;
	}
	
	
	
	
}
