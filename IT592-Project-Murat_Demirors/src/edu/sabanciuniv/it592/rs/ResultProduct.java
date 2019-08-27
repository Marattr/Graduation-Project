package edu.sabanciuniv.it592.rs;

import java.util.List;

public class ResultProduct {
	private int serviceMessageCode;
	private String serviceMessageText;
	
	private List<GlassProxy>glasses;
	private List<EyeGlassesProxy>eyeglasses;
	private List<SunGlassesProxy>sunglasses;
	private List<LensProxy>lenses;
	
	
	
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
	public List<LensProxy> getLenses() {
		return lenses;
	}
	public void setLenses(List<LensProxy> lenses) {
		this.lenses = lenses;
	}
	
	
}
