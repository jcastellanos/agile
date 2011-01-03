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
@Table(name="note_card")
public class NoteCard implements Serializable {
	
	private static final long serialVersionUID = 5373331051160643056L;
	private Integer id;
	private String note;
	private Date fechaCreacion;
	private User propietario;
	
	private Set<StoryCard> stories = new HashSet<StoryCard>();
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="noteCardIdSeq")
	@SequenceGenerator(name="noteCardIdSeq", sequenceName="note_card_id_seq")
	public Integer getId() {
		return id;
	}
	
	public void setNote(String note) {
		this.note = note;
	}

	@Column(nullable = true, columnDefinition="TEXT")
	public String getNote() {
		return note;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	@Column(name="fecha_creacion", nullable = false, columnDefinition="TIMESTAMP WITH TIME ZONE")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setPropietario(User propietario) {
		if(propietario != null) {
			this.propietario = propietario;
		}	
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="propietario_id", nullable = false)
	public User getPropietario() {
		return this.propietario;
	}
	
	
	public void setStories(Set<StoryCard> stories) {
		this.stories = stories;
	}
	
	@ManyToMany()
	@JoinTable(name = "story_card_note_card", joinColumns = {@JoinColumn(name="note_card_id")}, inverseJoinColumns = {@JoinColumn(name="story_card_id")})
	public Set<StoryCard> getStories() {
		return stories;
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
		if (!(obj instanceof NoteCard)) {
			return false;
		}
		NoteCard other = (NoteCard) obj;
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
		return getNote();
	}
	
	
}
