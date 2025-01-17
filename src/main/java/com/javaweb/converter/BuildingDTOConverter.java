package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private RentAreaRepository areaRepository;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = new BuildingDTO();
		building.setName(item.getName());
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict());
		building.setManagerName(item.getManagername());
		building.setManagerPhone(item.getManagerphone());
		building.setFloorArea(item.getFloorarea());
		building.setNumberOfBasement(item.getNumberOfBasement());
		List<RentAreaEntity> rentAreas = areaRepository.getValueByBuildingId(item.getId());
//		chuyển list thành chuỗi với mỗi phần tử cách nhau dấu phẩy
		String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		building.setRentPrice(item.getRentprice());
		building.setServiceFee(item.getServicefee());
		building.setBrokerageFee(item.getBrokeragefee());
		
		return building;
	}
}
