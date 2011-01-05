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
	private Date created;
	private User owner;
	
	private Set<StoryCard> storyCards = new HashSet<StoryCard>();
	
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
	
	
	public void setStoryCards(Set<StoryCard> stories) {
		this.storyCards = stories;
	}
	
	@ManyToMany()
	@JoinTable(name = "story_card_note_card", joinColumns = {@JoinColumn(name="note_card_id")}, inverseJoinColumns = {@JoinColumn(name="story_card_id")})
	public Set<StoryCard> getStoryCards() {
		return storyCards;
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
