package edu.sabanciuniv.it592.rs;

import java.util.ArrayList;
import java.util.List;

import edu.sabanciuniv.it592.domain.EyeGlasses;
import edu.sabanciuniv.it592.domain.Glass;
import edu.sabanciuniv.it592.domain.Lens;
import edu.sabanciuniv.it592.domain.SunGlasses;

public class ResultOrderProducts {
	
	private int serviceMessageCode;
	private String serviceMessageText;
	private List<Glass>glasses=new ArrayList<>();
	private List<Lens>lenses=new ArrayList<>();
	private List<EyeGlasses>eyeglasses=new ArrayList<>();
	private List<SunGlasses>sunglasses=new ArrayList<>();
	
	
	
	public int getServiceMessageCode() {
		return serviceMessageCode;
	}
	public void setServiceMessageCode(int serviceMessageCode) {
		this.serviceMessageCode = serviceMessageCode;
	}
	public String getServiceMessageText() {
		return serviceMessageText;
	}
	public void setServiceMessageText(String serviceMessageText) {
		this.serviceMessageText = serviceMessageText;
	}
	public List<Glass> getGlasses() {
		return glasses;
	}
	public void setGlasses(List<Glass> glasses) {
		this.glasses = glasses;
	}
	public List<Lens> getLenses() {
		return lenses;
	}
	public void setLenses(List<Lens> lenses) {
		this.lenses = lenses;
	}
	public List<EyeGlasses> getEyeglasses() {
		return eyeglasses;
	}
	public void setEyeglasses(List<EyeGlasses> eyeglasses) {
		this.eyeglasses = eyeglasses;
	}
	public List<SunGlasses> getSunglasses() {
		return sunglasses;
	}
	public void setSunglasses(List<SunGlasses> sunglasses) {
		this.sunglasses = sunglasses;
	}
	
	

}
