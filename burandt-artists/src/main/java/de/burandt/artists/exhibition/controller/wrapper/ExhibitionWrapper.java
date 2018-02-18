package de.burandt.artists.exhibition.controller.wrapper;

import de.burandt.artists.exhibition.domain.Exhibition;

import java.util.List;

public class ExhibitionWrapper {

    private Exhibition exhibition;

    public ExhibitionWrapper() {}

    public ExhibitionWrapper(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }
}
