package de.burandt.artists.exhibition.controller.viewModel;

public class PastExhibitionViewModel {

    private Integer id;

    private String exhibitionDate;

    private String title;

    private String link;

    public PastExhibitionViewModel() {
    }

    public PastExhibitionViewModel(Integer id, String exhibitionDate, String title, String link) {
        this.id = id;
        this.exhibitionDate = exhibitionDate;
        this.title = title;
        this.link = link;
    }

    public String getExhibitionDate() {
        return exhibitionDate;
    }

    public void setExhibitionDate(String exhibitionDate) {
        this.exhibitionDate = exhibitionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
