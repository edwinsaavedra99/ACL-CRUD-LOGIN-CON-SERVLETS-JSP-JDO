package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy; 
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable; 
import javax.jdo.annotations.Persistent; 
import javax.jdo.annotations.PrimaryKey; 
@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class User {
	 @PrimaryKey    
	//--------------------------------------------------------
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	 //--------------------------------------------------------
	 @Persistent private String userName;  
	 @Persistent private String name;  
	 @Persistent private String lastName;  
	 @Persistent private String email;     
	 @Persistent private String phone;
	 @Persistent private String address;
	//--------------------------------------------------------
	 @Persistent private boolean status;
	//--------------------------------------------------------
	 @Persistent private long idRole;
	//--------------------------------------------------------
	 
	 
	 public User (long idRole,String name,String lastName,String email,String phone,String address,boolean status) {
		 this.userName=userName;
		 this.name=name;
		 this.lastName=lastName;
		 this.email=email;
		 this.phone=phone;
		 this.address=address;
		 this.status=status;
		 this.idRole=idRole;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public long getIdRole() {
		return idRole;
	}


	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}	 
	 
	
}