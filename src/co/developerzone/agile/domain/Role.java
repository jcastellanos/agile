package co.developerzone.agile.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name="role")
public class Role implements Serializable {

	private static final long serialVersionUID = -2860263942666582931L;
	private Integer id;
	private String rolName;
	private String name;
	private Date created;
	private User owner;
	private Set<User> users = new HashSet<User>();
	
	public void setId(Integer id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="rolIdSeq")
	@SequenceGenerator(name="rolIdSeq", sequenceName="rol_id_seq")
	public Integer getId() {
		return id;
	}
	
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
		
	@Column(name="rol_name",length=70)
	public String getRolName() {
		return rolName;
	}

	@Column(length=100, nullable=false, unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@ManyToMany()
	@JoinTable(name = "user_role", joinColumns = {@JoinColumn(name="role_id")}, inverseJoinColumns = {@JoinColumn(name="user_id")})		
	public Set<User> getUsers() {
		return users;
	}
	
	public String toString() {
		return getName();
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
		if (!(obj instanceof Role)) {
			return false;
		}
		Role other = (Role) obj;
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
