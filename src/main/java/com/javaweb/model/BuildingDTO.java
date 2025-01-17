package com.javaweb.model;

public class BuildingDTO {
	private String name;
	private String address;
	private String managerName;
	private String managerPhone;
	private Long floorArea;
	private Long numberOfBasement;
	private String rentArea;
//	private String emptyArea;
	private Long rentPrice;
	private String serviceFee;
	private Double brokerageFee;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}
	public Long getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
//	public String getEmptyArea() {
//		return emptyArea;
//	}
//	public void setEmptyArea(String emptyArea) {
//		this.emptyArea = emptyArea;
//	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public Double getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(Double brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	
}
