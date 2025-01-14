package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.NumberUtils;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/estateadvance"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "27032003";     
    
    public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
    	String staffId = (String)params.get("staffId");
    	if(StringUtil.checkString(staffId) == true) {
    		sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
    	}
    	
    	String areaFrom = (String)params.get("areaFrom");
    	String areaTo = (String)params.get("areaTo");
    	if(StringUtil.checkString(areaFrom) == true || StringUtil.checkString(areaTo) == true) {
    		sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
    	}
    }
    
    // function common query k cần join
    public static void queryNormal(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
    	for(Map.Entry<String, Object> item : params.entrySet()) {
    		if(!item.getKey().equals("staffId") && !item.getKey().startsWith("area") && !item.getKey().startsWith("rentPrice") && !item.getKey().equals("typeCode")) {
    			String value = item.getValue().toString();
    			if(NumberUtil.isNumber(value) == true) {
    				where.append(" AND b." + item.getKey() + " = " + value);
    			}else {
    				where.append(" AND b." + item.getKey() + " LIKE '%" + item.getValue() + "%' ");
    			}
    		}
    	}
    	if(typeCode != null && typeCode.size() != 0) {
    		List<String> listCode  = new ArrayList<String>();
    		for(String item : typeCode) {
    			listCode.add("'" + item + "'");
    		}
    		where.append(" AND b.type IN(" + String.join(",", listCode) + ")" );
    	}
    }
    // function common query cần phải join với bảng khác
    public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
    	String staffId = (String)params.get("staffId");
    	if(StringUtil.checkString(staffId) == true) {
    		where.append(" AND assignmentbuilding.staffid = " + staffId);
    	}
    	String areaFrom = (String)params.get("areaFrom");
    	String areaTo =(String)params.get("areaTo");
    	if(StringUtil.checkString(areaFrom) == true || StringUtil.checkString(areaTo) == true) {
    		if(StringUtil.checkString(areaFrom) == true) {
    			where.append(" AND rentarea.value >= " + areaFrom);
    		}
    		if(StringUtil.checkString(areaTo) == true) {
    			where.append(" AND rentarea.value <= " + areaTo);
    		}
    	}
    	String rentPriceFrom = (String)params.get("rentPriceFrom");
    	String rentPriceTo = (String)params.get("rentPriceTo");
    	if(StringUtil.checkString(rentPriceFrom) == true || StringUtil.checkString(rentPriceTo) == true) {
    		if(StringUtil.checkString(rentPriceFrom) == true) {
    			where.append(" AND b.rentprice >= " + rentPriceFrom);
    		}
    		if(StringUtil.checkString(rentPriceTo) == true) {
    			where.append(" AND b.rentprice <= " + rentPriceTo);
    		}
    	}
    }
    
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		// SELECT
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.street, b.ward, b.district, b.numberofbasement, b.floorarea, b.rentprice, b.type, b.managername, b.managerphone FROM building b ");
		// JOIN
		joinTable(params, typeCode, sql);
		// WHERE
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");		
		queryNormal(params, typeCode, where);
		querySpecial(params, typeCode, where);	
		// where.append(" GROUP BY b.id");
		sql.append(" ").append(where);
		
		
        List<BuildingEntity> result = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString()) ) {
            
            while (rs.next()) {
            	BuildingEntity building = new BuildingEntity();
                building.setName(rs.getString("name"));
                building.setDistrict(rs.getString("district"));
                building.setWard(rs.getString("ward"));
                building.setStreet(rs.getString("street"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setFloorarea(rs.getInt("floorarea"));
                building.setRentprice(rs.getInt("rentprice"));
                building.setType(rs.getString("type"));
                result.add(building);            
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("connect database failed...");
        }
        
        return result;
	}

}
