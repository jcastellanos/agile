package co.developerzone.agile.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company implements Serializable {

	private static final long serialVersionUID = -1600214161211576680L;
	private Integer id;
	private String name;
	private Date created;
	private User owner;
	private Set<Project> projects = new HashSet<Project>();
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="companyIdSeq")
	@SequenceGenerator(name="companyIdSeq", sequenceName="company_id_seq")
	public Integer getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, length=200)
	public String getName() {
		return name;
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
	
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	@OrderBy("name")
	public Set<Project> getProjects() {
		return projects;
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
		if (!(obj instanceof Company)) {
			return false;
		}
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return name;
	}
	
	
}
