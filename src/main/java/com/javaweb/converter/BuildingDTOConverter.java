package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict());
		List<RentAreaEntity> rentAreas = areaRepository.getValueByBuildingId(item.getId());
//		chuyển list thành chuỗi với mỗi phần tử cách nhau dấu phẩy
		String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		
//		sử dụng ModelMapper nên k cần các dòng này nữa
//		building.setName(item.getName());
//		building.setManagerName(item.getManagername());
//		building.setManagerPhone(item.getManagerphone());
//		building.setFloorArea(item.getFloorarea());
//		building.setNumberOfBasement(item.getNumberOfBasement());
//		building.setRentPrice(item.getRentprice());
//		building.setServiceFee(item.getServicefee());
//		building.setBrokerageFee(item.getBrokeragefee());
		
		return building;
	}
}
