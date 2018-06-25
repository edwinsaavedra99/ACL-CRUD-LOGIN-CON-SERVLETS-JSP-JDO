package controller.users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class UserControllerLogout extends HttpServlet {	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = us.getCurrentUser();
		if(user == null){
			resp.sendRedirect(us.createLoginURL("/users/login"));
		}else {
			resp.sendRedirect(us.createLogoutURL("/users/login"));
		}
		
	}
}
