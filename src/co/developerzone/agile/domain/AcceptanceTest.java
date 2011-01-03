package co.developerzone.agile.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * acceptance tests
 * @author jcastellanos
 *
 */

@Entity
@Table(name="acceptance_test")
public class AcceptanceTest implements Serializable {
	
	private static final long serialVersionUID = 5304631356292531095L;
	private Integer id;
	private String test;
	private StoryCard storyCard;
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="acceptanceTestIdSeq")
	@SequenceGenerator(name="acceptanceTestIdSeq", sequenceName="acceptance_test_id_seq")
	public Integer getId() {
		return id;
	}
	
	public void setTest(String test) {
		this.test = test;
	}

	@Column(nullable = true, columnDefinition="TEXT")
	public String getTest() {
		return test;
	}
	
	public void setStoryCard(StoryCard storyCard) {
		this.storyCard = storyCard;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="story_card_id", nullable = true)
	public StoryCard getStoryCard() {
		return storyCard;
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
		if (!(obj instanceof AcceptanceTest)) {
			return false;
		}
		AcceptanceTest other = (AcceptanceTest) obj;
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
		return test;
	}
	
	
	
}
