package edu.sabanciuniv.it592.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class HomeBean {
	private List<String> images=new ArrayList<>();
    
    @PostConstruct
    public void init() {
        
        for (int i = 1; i <= 4; i++) {
            images.add("banner" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}
