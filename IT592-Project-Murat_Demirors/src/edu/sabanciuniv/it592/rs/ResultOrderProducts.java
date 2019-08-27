package edu.sabanciuniv.it592.rs;

import java.util.ArrayList;
import java.util.List;

public class ResultOrderProducts {
	
	private int serviceMessageCode;
	private String serviceMessageText;
	private List<GlassProxy>glasses=new ArrayList<>();
	private List<LensProxy>lenses=new ArrayList<>();
	private List<EyeGlassesProxy>eyeglasses=new ArrayList<>();
	private List<SunGlassesProxy>sunglasses=new ArrayList<>();
	
	
	
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
	public List<GlassProxy> getGlasses() {
		return glasses;
	}
	public void setGlasses(List<GlassProxy> glasses) {
		this.glasses = glasses;
	}
	public List<LensProxy> getLenses() {
		return lenses;
	}
	public void setLenses(List<LensProxy> lenses) {
		this.lenses = lenses;
	}
	public List<EyeGlassesProxy> getEyeglasses() {
		return eyeglasses;
	}
	public void setEyeglasses(List<EyeGlassesProxy> eyeglasses) {
		this.eyeglasses = eyeglasses;
	}
	public List<SunGlassesProxy> getSunglasses() {
		return sunglasses;
	}
	public void setSunglasses(List<SunGlassesProxy> sunglasses) {
		this.sunglasses = sunglasses;
	}
	
	

}
