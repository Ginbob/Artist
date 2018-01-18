package de.burandt.artists.exhibition.domain;

import javax.persistence.*;

@Entity
@Table(name = "exhibition_painting")
public class ExhibitionPainting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String datei;
    @ManyToOne(optional = false)
    @JoinColumn(name = "exhibition")
    private Exhibition exhibition;

    public ExhibitionPainting() {}

    public ExhibitionPainting(String datei, Exhibition exhibition) {
        this.datei = datei;
        this.exhibition = exhibition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatei() {
        return datei;
    }

    public void setDatei(String datei) {
        this.datei = datei;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }
}
