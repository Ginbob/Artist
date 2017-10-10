package de.burandt.artists.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Exhibition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date exhibitionStartDate;
	private Date exhibitionEndDate;
	private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExhibitionStartDate() {
        return exhibitionStartDate;
    }

    public void setExhibitionStartDate(Date exhibitionStartDate) {
        this.exhibitionStartDate = exhibitionStartDate;
    }

    public Date getExhibitionEndDate() {
        return exhibitionEndDate;
    }

    public void setExhibitionEndDate(Date exhibitionEndDate) {
        this.exhibitionEndDate = exhibitionEndDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}