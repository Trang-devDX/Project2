package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
																			.setName(MapUtil.getObject(params, "name", String.class))
																			.setStreet(MapUtil.getObject(params, "street", String.class))
																			.setWard(MapUtil.getObject(params, "ward", String.class))
																			.setDistrict(MapUtil.getObject(params, "district", String.class))
																			.setStructure(MapUtil.getObject(params, "structure", String.class))
																			.setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Long.class))
																			.setFloorarea(MapUtil.getObject(params, "floorarea", Long.class))
																			.build();
		return buildingSearchBuilder;
	}
}
