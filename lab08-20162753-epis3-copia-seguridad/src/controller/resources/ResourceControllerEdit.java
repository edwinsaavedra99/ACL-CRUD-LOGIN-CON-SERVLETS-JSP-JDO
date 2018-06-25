package controller.resources;
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
import model.entity.*;
@SuppressWarnings("serial")
public class ResourceControllerEdit extends HttpServlet {
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
						    	  Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(req.getParameter("id")));  
						    	  Resource r = pm.getObjectById(Resource.class, k);  
									req.setAttribute("resource",r);
						    	  resp.setContentType("text/html"); resp.setCharacterEncoding("UTF-8");
						    	  req.getRequestDispatcher("/WEB-INF/Views/Resources/edit.jsp").forward(req, resp);          
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
			Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(req.getParameter("id")));  
			Resource r = pm.getObjectById(Resource.class, k);  
			req.setAttribute("resource",r);
			r.setNameResource(req.getParameter("nameResource"));
			r.setStatus(Boolean.parseBoolean(req.getParameter("status")));
			Calendar fecha = new GregorianCalendar();
	        //Obtenemos el valor del año, mes, día,
	        //hora, minuto y segundo del sistema
	        //usando el método get y el parámetro correspondiente
	        int año = fecha.get(Calendar.YEAR);
	        int mes = fecha.get(Calendar.MONTH);
	        int dia = fecha.get(Calendar.DAY_OF_MONTH);
	        int hora = fecha.get(Calendar.HOUR_OF_DAY);
	        int minuto = fecha.get(Calendar.MINUTE);
	        int segundo = fecha.get(Calendar.SECOND);
	        String fechaactual=(dia) + "/" + (mes+1) + "/" + año +" - "+ hora+":"+minuto+":"+segundo;
	        r.setFecha(fechaactual);
	        r.setDescription(req.getParameter("description").toString());
	        r.setUrl(req.getParameter("url"));
	        resp.sendRedirect("/resources");
		 } finally {        
			 pm.close();    
		 } 
	} 
	
	
	
	
	
	    
}
