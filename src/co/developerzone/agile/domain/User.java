package co.developerzone.agile.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="users") // No se puede utilizar la tabla user en postgres ya que es una palabra reservada ver: http://stackoverflow.com/questions/4350874/unable-to-use-table-named-user-in-postgresql-hibernate
public class User implements Serializable {
	
	private static final long serialVersionUID = -860888016607739375L;
	private Integer id;
	private String userName;
	private String password;
	private String plain;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean enabled;
	private Date lastLoginDate;
	private String lastLoginIp;
	private Date created;
	private User owner;
	
	private List<Role> roles = new ArrayList<Role>();

	public void setId(Integer id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="userIdSeq")
	@SequenceGenerator(name="userIdSeq", sequenceName="user_id_seq")
	public Integer getId() {
		return id;
	}	
    
	public void setUserName(String username) {
		if(username != null && !username.trim().isEmpty()) {
			this.userName = username.trim();
		}
		
	}

	@Column(name="user_name", length=50, nullable=false, unique=true)
	public String getUserName() {
		return userName;
	}
	public void setPassword(String password) {
		if(password != null && !password.trim().isEmpty()) {
			this.password = password.trim();
		}
		
	}
	@Column(nullable = false, length=32)
	public String getPassword() {
		return password;
	}
	public void setFirstName(String firstName) {
		if(firstName != null && !firstName.trim().isEmpty()) {
			this.firstName = firstName.trim();
		}
		
	}
	@Column(name="first_name", nullable = false, length=100)
	public String getFirstName() {
		return firstName;
	}
	public void setLastName(String lastName) {
		if(lastName != null && !lastName.trim().isEmpty()) {
			this.lastName = lastName.trim();
		}
	}
	@Column(name="last_name", nullable = false, length=100)
	public String getLastName() {
		return lastName;
	}
	public void setEmail(String email) {
		if(email != null && !email.trim().isEmpty()) {
			this.email = email.trim();
		}		
	}
	@Column(nullable = false, length=70)
	public String getEmail() {
		return email;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	@Column(nullable = false)
	public Boolean isEnabled() {
		return enabled;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	@Column(name="last_login_date")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	@Column(name="last_login_ip")
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Column(nullable = false, columnDefinition="TIMESTAMP WITH TIME ZONE")
	public Date getCreated() {
		return created;
	}	
	
	public void setOwner(User owner) {
		if(owner != null) {
			this.owner = owner;
		}	
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id", nullable = false)
	public User getOwner() {
		return this.owner;
	}
	
	
	@Column(nullable = false)
	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}
	
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@ManyToMany()
	@JoinTable(name = "users_role", joinColumns = {@JoinColumn(name="users_id")}, inverseJoinColumns = {@JoinColumn(name="role_id")})		
	public List<Role> getRoles() {
		return roles;
	}
	
	public String toString() {
		return getUserName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	

}
