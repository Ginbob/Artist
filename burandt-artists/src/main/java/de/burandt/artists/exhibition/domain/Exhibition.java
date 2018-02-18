package de.burandt.artists.exhibition.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
public class Exhibition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private String title;
	private String description;
	private boolean currentFuture;
	private String link;
	@OneToMany(mappedBy = "exhibition", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<ExhibitionPainting> paintings;

    public Exhibition() {
    }

    public Exhibition(Date startDate, Date endDate, String title, String link, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.currentFuture = Date.from(Instant.now()).before(endDate);
        this.link = link;
        this.description = description;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCurrentFuture() {
        return currentFuture;
    }

    public void setCurrentFuture(boolean currentFuture) {
        this.currentFuture = currentFuture;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExhibitionPainting> getPaintings() {
        return paintings;
    }

    public void setPaintings(List<ExhibitionPainting> paintings) {
        this.paintings = paintings;
    }
}