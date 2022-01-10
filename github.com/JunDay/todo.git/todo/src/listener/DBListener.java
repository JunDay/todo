package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	try {
    		Class.forName("org.mariadb.jdbc.Driver");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
}
