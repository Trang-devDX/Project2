package com.javaweb.builder;

public class BuildingSearchBuilder {
    private String name;
    private String street;
    private String ward;
    private String district;
    private String structure;
    private Long numberOfBasement;
    private Long floorarea;

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.street = builder.street;
        this.ward = builder.ward;
        this.district = builder.district;
        this.structure = builder.structure;
        this.numberOfBasement = builder.numberOfBasement;
        this.floorarea = builder.floorarea;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getWard() {
        return ward;
    }

    public String getDistrict() {
        return district;
    }

    public String getStructure() {
        return structure;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public Long getFloorarea() {
        return floorarea;
    }

    public static class Builder {
        private String name;
        private String street;
        private String ward;
        private String district;
        private String structure;
        private Long numberOfBasement;
        private Long floorarea;

        // Thay đổi kiểu trả về của các setter thành Builder
        public Builder setName(String name) {
            this.name = name;
            return this; // Trả về chính đối tượng Builder
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setStructure(String structure) {
            this.structure = structure;
            return this;
        }

        public Builder setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setFloorarea(Long floorarea) {
            this.floorarea = floorarea;
            return this;
        }

        // Xây dựng đối tượng BuildingSearchBuilder
        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
