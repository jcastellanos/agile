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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Historia de usuario
 * @author jcastellanos
 *
 */

@Entity
@Table(name="story_card")
public class StoryCard implements Serializable {

	private static final long serialVersionUID = 347631819393337171L;
	private Integer id;
	private String description;
	private Project project;
	private Boolean epic;
	private StoryCard storyCard;  // Padre (Epic)
	private Date created;
	private User owner;
	private Set<NoteCard> noteCards = new HashSet<NoteCard>();
	private Set<StoryCard> storyCards = new HashSet<StoryCard>();
	private Set<AcceptanceTest> acceptanceTests = new HashSet<AcceptanceTest>();
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="storyCardIdSeq")
	@SequenceGenerator(name="storyCardIdSeq", sequenceName="story_card_id_seq")
	public Integer getId() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable = false, columnDefinition="TEXT")
	public String getDescription() {
		return description;
	}
	
	public void setProject(Project project) {
		if(project != null) {
			this.project = project;
		}	
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="project_id", nullable = false)
	public Project getProject() {
		return this.project;
	}
	
	public void setEpic(Boolean epic) {
		this.epic = epic;
	}

	public Boolean getEpic() {
		return epic;
	}
	
	public void setStoryCard(StoryCard storyCard) {
		this.storyCard = storyCard;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="story_card_id", nullable = true)
	public StoryCard getStoryCard() {
		return storyCard;
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
	
	public void setNoteCards(Set<NoteCard> noteCards) {
		this.noteCards = noteCards;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "story_card_note_card", joinColumns = {@JoinColumn(name="story_card_id")}, inverseJoinColumns = {@JoinColumn(name="note_card_id")})		
	public Set<NoteCard> getNoteCards() {
		return noteCards;
	}
	
	public void addNoteCard(NoteCard noteCard) {
		if(noteCard != null) {
			this.noteCards.add(noteCard);
		}
	}
	
	public void setStoryCards(Set<StoryCard> storyCards) {
		this.storyCards = storyCards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "storyCard")
	@OrderBy("description")
	public Set<StoryCard> getStoryCards() {
		return storyCards;
	}
	
	public void setAcceptanceTests(Set<AcceptanceTest> acceptanceTests) {
		this.acceptanceTests = acceptanceTests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "storyCard")
	@OrderBy("test")
	public Set<AcceptanceTest> getAcceptanceTests() {
		return acceptanceTests;
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
		if (!(obj instanceof StoryCard)) {
			return false;
		}
		StoryCard other = (StoryCard) obj;
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
		return description;
	}
	
}
