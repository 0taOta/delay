package uz.pdp.payload;

public class RegionDTO {

    private Integer regionId;
    private String name;
    private String country;

    public RegionDTO(Integer regionId, String name, String country) {
        this.regionId = regionId;
        this.name = name;
        this.country = country;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "RegionDTO{" +
                "regionId=" + regionId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
