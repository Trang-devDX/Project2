package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/estateadvance"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "27032003";     
    
	@Override
	public List<BuildingEntity> findAll(String name, String districtId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM building b WHERE 1=1 ");
		if(name != null && !name.equals("")) {
			sql.append("AND b.name LIKE '%" + name + "%' ");
		}
		if(districtId != null && !districtId.equals("")) {
			sql.append("AND b.district LIKE '%" + districtId + "%' ");
		}
        List<BuildingEntity> result = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString()) ) {
            
            while (rs.next()) {
            	BuildingEntity building = new BuildingEntity();
                building.setName(rs.getString("name"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setWard(rs.getString("ward"));
                building.setStreet(rs.getString("street"));
                result.add(building);            
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("connect database failed...");
        }
        
        return result;
	}

}
