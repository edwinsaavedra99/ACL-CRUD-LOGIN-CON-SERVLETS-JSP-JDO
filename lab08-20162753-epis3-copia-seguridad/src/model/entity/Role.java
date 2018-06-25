package model.entity;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy; 
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable; 
import javax.jdo.annotations.Persistent; 
import javax.jdo.annotations.PrimaryKey; 
@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class Role {
	 @PrimaryKey    
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;	 
	 @Persistent private String nameRole;  
	 @Persistent private boolean status=false;
	 @Persistent private String description;	
	 @Persistent private String fecha;	 
	
	 
	 public Role (String nameRole,String fecha,boolean status,String description) {
		this.status=status;
		 this.nameRole=nameRole;
		this.fecha=fecha;		
		this.description=description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}