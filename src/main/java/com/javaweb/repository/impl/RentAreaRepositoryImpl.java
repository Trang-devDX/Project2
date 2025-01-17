package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.mysql.cj.protocol.Resultset;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> getValueByBuildingId(Long Id) {
		String sql = "SELECT * FROM rentarea WHERE rentarea.buildingid = " + Id;
		List<RentAreaEntity> rentAreas = new ArrayList<>();
		try(Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next()) {
				RentAreaEntity area = new RentAreaEntity();
				area.setId(rs.getLong("id"));
				area.setValue(rs.getString("value"));
				area.setBuildingId(rs.getLong("buildingid"));
				rentAreas.add(area);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rentAreas;
	}
	
}
