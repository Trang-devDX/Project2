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
    	if (typeCode != null && !typeCode.isEmpty()) {
            List<String> listCode = new ArrayList<>();
            for (String item : typeCode) {
                listCode.add("'" + item + "'");
                where.append(" AND b.type LIKE '%" + item + "%' ");
            }
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
		StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
		// JOIN
		joinTable(params, typeCode, sql);
		// WHERE
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");		
		queryNormal(params, typeCode, where);
		querySpecial(params, typeCode, where);	
		sql.append(" ").append(where);
		
		
        List<BuildingEntity> result = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString()) ) {
            
            while (rs.next()) {
            	BuildingEntity building = new BuildingEntity();
            	building.setId(rs.getLong("id"));
            	building.setName(rs.getString("name"));
            	building.setStreet(rs.getString("street"));
            	building.setWard(rs.getString("ward"));
            	building.setDistrict(rs.getString("district"));
            	building.setStructure(rs.getString("structure"));
            	building.setNumberOfBasement(rs.getLong("numberofbasement"));
            	building.setFloorarea(rs.getLong("floorarea"));
            	building.setDirection(rs.getString("direction"));
            	building.setLevel(rs.getString("level"));
            	building.setRentprice(rs.getLong("rentprice"));
            	building.setRentpricedescription(rs.getString("rentpricedescription"));
            	building.setServicefee(rs.getString("servicefee"));
            	building.setCarfee(rs.getString("carfee"));
            	building.setMotofee(rs.getString("motofee"));
            	building.setOvertimefee(rs.getString("overtimefee"));
            	building.setWaterfee(rs.getString("waterfee"));
            	building.setElectricityfee(rs.getString("electricityfee"));
            	building.setDeposit(rs.getString("deposit"));
            	building.setPayment(rs.getString("payment"));
            	building.setRenttime(rs.getString("renttime"));
            	building.setDecorationtime(rs.getString("decorationtime"));
            	building.setBrokeragefee(rs.getDouble("brokeragefee"));
            	building.setType(rs.getString("type"));
            	building.setNote(rs.getString("note"));
            	building.setLinkofbuilding(rs.getString("linkofbuilding"));
            	building.setMap(rs.getString("map"));
            	building.setAvatar(rs.getString("avatar"));
            	building.setCreateddate(rs.getDate("createddate"));
            	building.setModifieddate(rs.getDate("modifieddate"));
            	building.setCreatedby(rs.getString("createdby"));
            	building.setModifiedby(rs.getString("modifiedby"));
            	building.setManagername(rs.getString("managername"));
            	building.setManagerphone(rs.getString("managerphone"));
                
                result.add(building);            
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("connect database failed...");
        }
        
        return result;
	}

}
