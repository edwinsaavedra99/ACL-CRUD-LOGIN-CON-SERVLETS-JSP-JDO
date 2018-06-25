package model.entity;
import javax.jdo.annotations.IdGeneratorStrategy; 
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable; 
import javax.jdo.annotations.Persistent; 
import javax.jdo.annotations.PrimaryKey; 
@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class Access {
	 @PrimaryKey    
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;     
	 @Persistent private Long idRole;
	 @Persistent private Long idResource;
	 @Persistent private String fecha;
	 @Persistent private boolean status=false;
	 
	 public Access (Long idRole,Long idResource,String fecha,boolean status) {
		this.idRole=idRole;
		this.idResource = idResource;
		this.fecha=fecha;
		this.status=status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public Long getIdResource() {
		return idResource;
	}

	public void setIdResource(Long idResource) {
		this.idResource = idResource;
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

	
	 
}