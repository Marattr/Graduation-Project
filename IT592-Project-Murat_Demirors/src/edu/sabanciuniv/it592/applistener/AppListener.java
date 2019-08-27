package edu.sabanciuniv.it592.applistener;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.sabanciuniv.it592.services.CategoryService;


@WebListener
public class AppListener implements ServletContextListener {
	@EJB
	private CategoryService categoryService;
  
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce)  { 
        categoryService.appInitialized();
    }
	
}
