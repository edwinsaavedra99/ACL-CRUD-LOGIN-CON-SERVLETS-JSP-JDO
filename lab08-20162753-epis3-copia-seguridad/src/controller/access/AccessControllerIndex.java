package controller.access;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.jdo.PersistenceManager;

import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.*;
@SuppressWarnings("serial")
public class AccessControllerIndex extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
	      try{
	    	  PersistenceManager pm = PMF.get().getPersistenceManager();
	    	  UserService us = UserServiceFactory.getUserService();
	  		  com.google.appengine.api.users.User user = us.getCurrentUser();
	  		  if(user == null) {
	  			req.getRequestDispatcher("/WEB-INF/Views/Errores/error1.jsp").forward(req, resp); 
	  		  }else {
	  			String query1 = "select from " + User.class.getName() + " where email == '"+user.getEmail()+"'"+"&& status==true"; 
				List<User> userSearch = (List<User>) pm.newQuery(query1).execute();
	  			if(userSearch.isEmpty()) {
	  				req.getRequestDispatcher("/WEB-INF/Views/Errores/error2.jsp").forward(req, resp); 
	  			}else{
	  				String query2 = "select from " + Resource.class.getName()+ " where url == '"+req.getServletPath()+"'"+"&& status == true"; 
		  			List<Resource> rSearch = (List<Resource>) pm.newQuery(query2).execute();
		  			if(rSearch.isEmpty()) {
		  				req.getRequestDispatcher("/WEB-INF/Views/Errores/error3.jsp").forward(req, resp); 
			    	}else {
			    		String query3 = "select from " + Access.class.getName()+ 
			    				" where idRole =="+ userSearch.get(0).getIdRole()+
			    				" && idResource =="+rSearch.get(0).getId()+
			    				" && status==true"; 
			  			List<Access> aSearch = (List<Access>) pm.newQuery(query3).execute();
				    	if(aSearch.isEmpty()) {
				    		req.getRequestDispatcher("/WEB-INF/Views/Errores/error4.jsp").forward(req, resp); 
						}else {	
						
							String query = "select from " + Access.class.getName(); 
							List<Access> access = (List<Access>) pm.newQuery(query).execute();   
							
							req.setAttribute("access", access);
							
						      try{
						    	  resp.setContentType("text/html"); resp.setCharacterEncoding("UTF-8");
						    	  req.getRequestDispatcher("/WEB-INF/Views/Access/index.jsp").forward(req, resp);          
						      }catch(Exception ex){
						    	  out.println(ex.getMessage()); 
						      }
}
						
			    	}
		    	}
  			}	 
  		  }catch(Exception ex){
    	  out.println(ex.getMessage()); 
      }	
}
}

