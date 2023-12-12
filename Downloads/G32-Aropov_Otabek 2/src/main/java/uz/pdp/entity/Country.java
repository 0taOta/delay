package uz.pdp.entity;

import java.time.LocalDate;

public class Country {

    private Integer id;
    private String name;
    private String flagUrl;
    private String filePath;
    private LocalDate establishment;
    private Long square;
    private Region region;

    public Country(Integer id, String name, String flagUrl, String filePath,
                   LocalDate establishment, Long square, Region region) {
        this.id = id;
        this.name = name;
        this.flagUrl = flagUrl;
        this.filePath = filePath;
        this.establishment = establishment;
        this.square = square;
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDate getEstablishment() {
        return establishment;
    }

    public void setEstablishment(LocalDate establishment) {
        this.establishment = establishment;
    }

    public Long getSquare() {
        return square;
    }

    public void setSquare(Long square) {
        this.square = square;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flagUrl='" + flagUrl + '\'' +
                ", filePath='" + filePath + '\'' +
                ", establishment=" + establishment +
                ", square=" + square +
                ", region=" + region +
                '}';
    }
}
