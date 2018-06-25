package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy; 
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable; 
import javax.jdo.annotations.Persistent; 
import javax.jdo.annotations.PrimaryKey; 
@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class Resource {
	 @PrimaryKey    
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;     
	 @Persistent private String nameResource;  
	 @Persistent private String fecha;  
	 @Persistent private String url; 
	 @Persistent private String description; 
	 @Persistent private boolean status=false; 
	
	 
	 public Resource (String nameResource,String fecha,String url,String description,boolean status) {
		 this.nameResource=nameResource;
		 this.fecha=fecha;
		 this.status=status;
		 this.url=url;
		 this.description=description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameResource() {
		return nameResource;
	}
	public void setNameResource(String nameResource) {
		this.nameResource = nameResource;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}