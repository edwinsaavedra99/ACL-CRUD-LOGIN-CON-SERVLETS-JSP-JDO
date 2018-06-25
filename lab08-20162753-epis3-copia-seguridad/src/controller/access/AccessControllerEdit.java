package controller.access;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;
import model.entity.User;
@SuppressWarnings("serial")
public class AccessControllerEdit extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
						      try{
						    	  
						    	 
									String query = "select from " + Role.class.getName(); 
									List<Role> roles = (List<Role>) pm.newQuery(query).execute();   
									req.setAttribute("roles", roles);
									
									
									String query5 = "select from " +  Resource.class.getName(); 
									List< Resource> resource = (List<Resource>) pm.newQuery(query5).execute();   
									req.setAttribute("resources",  resource);	
						    	  
						    	  Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(req.getParameter("id")));  
						    	  Access a = pm.getObjectById(Access.class, k);  
									req.setAttribute("access",a);
						    	  resp.setContentType("text/html"); resp.setCharacterEncoding("UTF-8");
						    	  req.getRequestDispatcher("/WEB-INF/Views/Access/edit.jsp").forward(req, resp);          
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
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html"); resp.setCharacterEncoding("UTF-8");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {   
			Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(req.getParameter("id")));  
			Access a = pm.getObjectById(Access.class, k);  
			req.setAttribute("access",a);
			a.setIdResource(Long.parseLong(req.getParameter("idResource")));
			a.setIdRole(Long.parseLong(req.getParameter("idRole")));
			a.setStatus(Boolean.parseBoolean(req.getParameter("status")));
			Calendar fecha = new GregorianCalendar();
	        int año = fecha.get(Calendar.YEAR);
	        int mes = fecha.get(Calendar.MONTH);
	        int dia = fecha.get(Calendar.DAY_OF_MONTH);
	        int hora = fecha.get(Calendar.HOUR_OF_DAY);
	        int minuto = fecha.get(Calendar.MINUTE);
	        int segundo = fecha.get(Calendar.SECOND);
	        String fechaactual=(dia) + "/" + (mes+1) + "/" + año +" - "+ hora+":"+minuto+":"+segundo;
	        a.setFecha(fechaactual);
	        resp.sendRedirect("/access");
		 } finally {        
			 pm.close();    
		 } 
	}
}
