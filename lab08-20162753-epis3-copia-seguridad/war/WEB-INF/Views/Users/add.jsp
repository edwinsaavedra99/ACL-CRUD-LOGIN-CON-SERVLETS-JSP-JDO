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
</head>
 <% UserService us = UserServiceFactory.getUserService();%>
<% com.google.appengine.api.users.User user = us.getCurrentUser();%>
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
               <span>Welcome,<h2><%=user.getNickname()%></h2></span>
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
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><%=user.getEmail()%>      .  <span class=" fa fa-angle-down"></span>
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
		<div class="container">
        	<div class="table-wrapper">
            	<div class="table-title">
                	<div class="row">
                    	<div class="col-sm-6">
                        	<h2>Manage <b>Users</b></h2>
                    	</div>
                    	<div class="col-sm-6">
	                        <a href="/index.html" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xe021;</i> <span>Home</span></a>
    	                    <a href="/users" class="btn btn-info" data-toggle="modal"><i class="material-icons">&#xe152;</i> <span>List Users</span></a>
        	            </div>
            	    </div>
            	</div>
            	<br>
            	<table class="table table-striped table-hover table-bordered">
                	<form method="post" action="/users/add" onsubmit="return checkSubmit();">
	                    <div class="modal-header">                      
    	                    <h4 class="modal-title">Add User</h4>
                        </div>
                        <table  align="center">
                        	<tr>
                            	<th>
                            		<div  class="modal-body" >
                                	<img src="https://scontent.faqp2-1.fna.fbcdn.net/v/t1.0-9/11752519_441735665999165_7814991917523584018_n.jpg?_nc_cat=0&oh=e6c53636c3536940fb6674fd9da4a4b6&oe=5B86C473"  style="width:450px;height:450px;"> 
                            		</div>
                            	</th>
                                <th>
                               
                            		<div class="modal-body" >
                            			<div class="form-group">
                                    		<label>Name</label>
                                    		<input name="name" type="text" autofocus class="form-control" required>
                                		</div>                   
	                                	<div class="form-group">
	                                	
			                              	<label>Role Name</label>
    	                                	<select name="idRole" class="form-control">
    	                                  	<% List<Role> roles = (List<Role>)request.getAttribute("roles"); %>
											<% if (roles.size() > 0) { %>
    	                                  		<% for (int i = 0;i<roles.size();i++) { %>
    	                                  			<% Role r = (Role)roles.get(i); %>
														<option value="<%= r.getId() %>" ><%=r.getNameRole() %></option>
									       			<%} %>
									       	<%} %>
    	                                	</select>
    	                                </div>
                                		
                                		<div class="form-group">
                                    		<label>Last Name</label>
                                    		<input name="lastName" type="text" class="form-control" required>
                                		</div>
                                		<div class="form-group">
                                    		<label>E-mail</label>
                                    		<input name="email" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" value="example@gmail.com" class="form-control" required>
                                		</div>
                                		<div class="form-group">
                                    		<label>Phone</label>
                                    		<input name="phone" type="number" pattern="[0-9]{6,9}" class="form-control" required>
                                		</div>
                                 		<div class="form-group">
                                    		<label>Address</label>
                                    		<input name="address" type="text" class="form-control" required>
                                		</div>
                                		<div class="form-group">
                                    		<label>Status</label><br>
                                    		<input name="status" type="radio" id="actived" value="true" > Actived<br>
                                    		<input name="status" type="radio" id="disabled" value="false"> Disabled
                                		</div>                
                            		</div>
                            	</th>
                             </tr>
                         </table>
            	        <div class="modal-footer">
            	        	<a href="/users">
            	        		<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
            	        	</a>
                	        <input type="submit" id="btsubmit" class="btn btn-success" value="Add User">
                    	</div>
                </form>
            </table>
         </div>
    </div>
      </div>
    </div>
  <script src="js/custom.min.js"></script>
</body>
</html>