package de.zeroco.companycontact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.zeroco.companycontact.dao.CompanyDao;
import de.zeroco.companycontact.dao.ContactDao;
import de.zeroco.companycontact.entity.Company;

@Service
public class CompanyService {

	@Autowired
	CompanyDao companyDao;
	@Autowired
	Company company;
	@Autowired
	ContactDao contactDao;
	
	public Company getObjectFromMap(Map<String, Object> map) {
		company.setName((String) map.get("name"));
		company.setEmail((String) map.get("email"));
		company.setNumber((long) map.get("number"));
		company.setAddress((String) map.get("address"));
		return company;
	}
	
	public Company getObjectFromMap(int id, Map<String, Object> map) {
		company.setId(id);
		company.setName((String) map.get("name"));
		company.setEmail((String) map.get("email"));
		company.setNumber((long) map.get("number"));
		company.setAddress((String) map.get("address"));
		return company;
	}
	
	public String insert(Company company) {
		return companyDao.save(company) > 0 ? "Inserted Successfully" : "Not Inserted";
	}
	
	public String update(Company company) {
		return companyDao.update(company) > 0 ? "Updated Successfully" : "Not Inserted";
	}
	
	public String delete(int id) {
		return companyDao.delete(id) > 0 ? "Deleted Successfully" : "Not Inserted";
	}
	
	public Map<String, Object> get(int id) {
		Map<String, Object> map = companyDao.get(id);
		map.put("contact", getJsonFromString((String) map.remove("contact")));
//		map.put("contacts", getContacts("number", "company_id", map.get("pk_id")));
		return map;
	}
	
	public List<Map<String, Object>> list() {
		List <Map<String, Object>> list = companyDao.list();
//		for (int i = 0; i < list.size(); i++) {
//			Map<String, Object> map = list.remove(0);
//			map.put("contacts", getContacts("number", "company_id", map.get("pk_id")));
//			list.add(map);
//		}
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.remove(0);
			map.put("contact", getJsonFromString((String) map.remove("contact")));
			list.add(map);
		}
		return list;
	}
	
	public Map<String, Object> get(String columnName, Object value) {
		return companyDao.get(columnName, value);
	}
//	
//	public List<Object> getContacts(String columnName, String conditionColumn, Object value) {
//		List<Map<String, Object>> list = contactDao.get(columnName, conditionColumn, value);
//		List<Object> contactList = new ArrayList<Object>();
//		for (int j = 0; j < list.size(); j++) {
//			contactList.add(list.get(j).get("number"));
//		}
//		return contactList;
//	}
	
	public JsonNode getJsonFromString(String value) {
		try {
			return new ObjectMapper().readTree(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
