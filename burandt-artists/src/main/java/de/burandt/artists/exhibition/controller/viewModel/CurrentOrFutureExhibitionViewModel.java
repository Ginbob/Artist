package de.burandt.artists.exhibition.controller.viewModel;

import java.util.Date;

public class CurrentOrFutureExhibitionViewModel {

    private String title;
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String paintingFile;

    public CurrentOrFutureExhibitionViewModel() {
    }

    public CurrentOrFutureExhibitionViewModel(Integer id, String title, Date startDate, Date endDate, String paintingFile) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paintingFile = paintingFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaintingFile() {
        return paintingFile;
    }

    public void setPaintingFile(String paintingFile) {
        this.paintingFile = paintingFile;
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

}
