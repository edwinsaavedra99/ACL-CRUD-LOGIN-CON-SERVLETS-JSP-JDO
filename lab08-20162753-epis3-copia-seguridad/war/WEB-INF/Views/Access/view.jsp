<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%> 
<%@ page import="model.entity.*"%> 
<%@ page import="com.google.appengine.api.users.UserService"%> 
<%@ page import ="com.google.appengine.api.users.UserServiceFactory"%> 

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">  
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/styles2.css">
	<title>ACME BOTS</title>
	<script type="text/javascript">
		$(document).ready(function(){
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
	<script type="text/javascript">
		function checkSubmit() {
	    	document.getElementById("btsubmit").value = "Enviando...";
	    	document.getElementById("btsubmit").disabled = true;
	    	return true;
		}
	</script>
	<script>
		function confirmar(){
			if(confirm('¿Estas seguro que quieres borrar a este Access ?'))	
				return true;
			else
				return false;
		}
	</script>
</head>
 <% UserService us = UserServiceFactory.getUserService();%>
<% com.google.appengine.api.users.User user2 = us.getCurrentUser();%>
  <body class="nav-md">
    <div class="container body">
       <div class="main_container">
        <div class="col-md-3 left_col" style="position: fixed;">
          <div class="left_col scroll-view">
          <div class="clearfix"></div>
           <div class="profile clearfix">
              <div class="profile_pic">
              <img src="http://www.gifss.com/robot/robot-19.gif" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
               <span>Welcome,<h2><%=user2.getNickname()%></h2></span>
              </div>
            </div>
            <br />
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
               <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a href="/index.html"><i class="fa fa-home"></i> Home </a></li>                 
                </ul>
                <h3>Administration</h3>
                <ul class="nav side-menu">
                   <li><a href="/users"><i class="fa fa-edit"></i> Manage Users</a></li>
                  <li><a href="/roles"><i class="fa fa-desktop"></i> Manage Roles</a></li>
                  <li><a href="/resources"><i class="fa fa-table"></i> Manage Resources</a></li>
                  <li><a href="/access"><i class="fa fa-bar-chart-o"></i> Manage Access</a></li>
                </ul>
              </div>
            </div>
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="/users/logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
          </div>
        </div>
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>
              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><%=user2.getEmail()%>      .  <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="/users/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>                
              </ul>
            </nav>
          </div>
        </div>
        <div class="right_col" role="main">
        <div class="row tile_count"> </div>
		<% Access access = (Access)request.getAttribute("access");%><div class="container">
       <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Manage <b>Access</b></h2>
					</div>
					 <div class="col-sm-6">
                        <a href="/index.html" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xe021;</i> <span>Home</span></a>
                        <a href="/access" class="btn btn-info" data-toggle="modal"><i class="material-icons">&#xe152;</i> <span>List Access</span></a>
                        <a href="/access/edit?id=<%= access.getId() %>" class="btn btn-warning" data-toggle="modal"><i class="material-icons">&#xe254;</i> <span>Edit</span></a>
                        <a href="/access/delete?id=<%= access.getId() %>" class="btn btn-primary" onclick="return confirmar()" data-toggle="modal"><i class="material-icons">&#xe14c;</i> <span>Delete</span></a>
                    </div>
                </div>
            </div>
            <br>
              <table class="table table-striped table-hover table-bordered">
                <form>
                    <div class="modal-header">                      
                        <h4 class="modal-title">View Access <b><%=access.getId()%></b></h4>
                    </div>
                    <table  align="center">
                        <tr>
                            <th><div  class="modal-body" >
                                <img src="https://i2.wp.com/wp.laravel-news.com/wp-content/uploads/2017/07/roles-permissions.png?resize=2200%2C1125"  style="width:550px;height:350px;"> 
                    	        </div>
                    	    </th>
                            <th>
                              <div class="modal-body" >                   
                                <div class="form-group">
                                    <label>Name Role</label>
                                    <input type="text"  value="<%=access.getIdRole()%>" class="form-control" size="30" maxlength="30" disabled>
                                </div>
                                 <div class="form-group">
                                    <label>Name Resource</label>
                                    <input type="text"  value="<%=access.getIdResource()%>" class="form-control" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Status</label><br>
                                    	<input name="status" type="radio" id="actived" value="true" <% if (access.isStatus()==true)%><%{%> checked<%}%> disabled> Actived<br>
                                    	<input name="status" type="radio" id="disabled" value="false" <% if (access.isStatus()==false)%><%{%>checked <%}%> disabled> Disabled
                                </div>
                                <div class="form-group">
                                    <label>Created</label>
                                    <input type="text" value="<%=access.getFecha()%>" class="form-control" disabled>
                                </div>
                                          
                           		 </div>
                            </th>
                          </tr>
                       </table>
                   </form>
            </table>
		</div>
    </div>      </div>
    </div>
   <script src="js/custom.min.js"></script>
</body>
</html>