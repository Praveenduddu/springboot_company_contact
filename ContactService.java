package de.zeroco.companycontact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.zeroco.companycontact.dao.ContactDao;
import de.zeroco.companycontact.entity.Contact;

@Service
public class ContactService {

	@Autowired
	ContactDao contactDao;
	@Autowired
	Contact contact;
	@Autowired
	CompanyService companyService;
	
	public Contact getObjectFromMap(Map<String, Object> map) {
		contact.setName((String) map.get("name"));
		contact.setEmail((String) map.get("email"));
		contact.setNumber((long) map.get("number"));
		contact.setDateOfBirth((String) map.get("dateOfBirth"));
		contact.setAddress((String) map.get("address"));
//		contact.setCompanyId((int) companyService.get("name", map.get("company")).get("pk_id"));
		contact.setCompanyName((String) map.get("company"));
		return contact;
	}
	
	public Contact getObjectFromMap(int id, Map<String, Object> map) {
		contact.setId(id);
		contact.setName((String) map.get("name"));
		contact.setEmail((String) map.get("email"));
		contact.setNumber((long) map.get("number"));
		contact.setDateOfBirth((String) map.get("dateOfBirth"));
		contact.setAddress((String) map.get("address"));
//		contact.setCompanyId((int) companyService.get("name", map.get("company")).get("pk_id"));
		contact.setCompanyName((String) map.get("company"));
		return contact;
	}
	
	public String insert(Contact contact) {
		return contactDao.save(contact) > 0 ? "Inserted Successfully" : "Not Inserted";
	}
	
	public String update(Contact contact) {
		return contactDao.update(contact) > 0 ? "Updated Successfully" : "Not Inserted";
	}
	
	public String delete(int id) {
		return contactDao.delete(id) > 0 ? "Deleted Successfully" : "Not Inserted";
	}
	
	public Map<String, Object> get(int id) {
		Map<String, Object> map = contactDao.get(id);
//		map.put("company name", companyService.get("pk_id", map.remove("company_id")).get("name"));
		return map;
	}
	
	public List<Map<String, Object>> list() {
		List <Map<String, Object>> list = contactDao.list();
//		for (int i = 0; i < list.size(); i++) {
//			Map<String, Object> map = list.remove(0);
//			map.put("company name", companyService.get("pk_id", map.remove("company_id")).get("name"));
//			list.add(map);
//		}
		return list;
	}
}
