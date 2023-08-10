package de.zeroco.companycontact.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.zeroco.companycontact.entity.Contact;

@Repository
public class ContactDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	public final String INSERT_QUERY = "INSERT INTO `zerocode`.`company_contact` (`name`, `email`, `number`, `date_of_birth`, `address`, company_id) VALUES (?, ?, ?, ?, ?, ?);";
	public final String INSERT_QUERY = "INSERT INTO `zerocode`.`company_contact` (`name`, `email`, `number`, `date_of_birth`, `address`, `company_id`) VALUES (?, ?, ?, ?, ?, (SELECT company.pk_id FROM zerocode.company WHERE company.name = ?));";
//	public final String UPDATE_QUERY = "UPDATE `zerocode`.`company_contact` SET `name` = ?, `email` = ?, `number` = ?, `date_of_birth` = ?, `address` = ?, `company_id` = ? WHERE `pk_id` = ?;";
	public final String UPDATE_QUERY = "UPDATE `zerocode`.`company_contact` SET `name` = ?, `email` = ?, `number` = ?, `date_of_birth` = ?, `address` = ?, `company_id` = (SELECT company.pk_id FROM zerocode.company WHERE company.name = ?) WHERE `pk_id` = ?;";
	public final String DELETE_QUERY = "DELETE FROM `zerocode`.`company_contact` WHERE `pk_id` = ?;";
	public final String GET_QUERY = "SELECT company_contact.pk_id id, company_contact.name name, company_contact.email email, company_contact.number number, company_contact.date_of_birth DateOfBirth, company_contact.address address, company.name companyname FROM `zerocode`.`company_contact` left join `zerocode`.`company` on company_contact.company_id = company.pk_id WHERE company_contact.pk_id = ? GROUP BY company_contact.pk_id;";
	public final String LIST_QUERY = "SELECT company_contact.pk_id id, company_contact.name name, company_contact.email email, company_contact.number number, company_contact.date_of_birth DateOfBirth, company_contact.address address, company.name companyname FROM `zerocode`.`company_contact` left join `zerocode`.`company` on company_contact.company_id = company.pk_id GROUP BY company_contact.pk_id;";
	
	public int save(Contact contact) {
		return jdbcTemplate.update(INSERT_QUERY, contact.getName(), contact.getEmail(), contact.getNumber(), contact.getDateOfBirth(), contact.getAddress(), contact.getCompanyName());
	}
	
	public int update(Contact contact) {
		return jdbcTemplate.update(UPDATE_QUERY, contact.getName(), contact.getEmail(), contact.getNumber(), contact.getDateOfBirth(), contact.getAddress(), contact.getCompanyName(), contact.getId());
	}
	
	public int delete(int id) {
		return jdbcTemplate.update(DELETE_QUERY, id);
	}
	
	public Map<String, Object> get(int id) {
		return jdbcTemplate.queryForMap(GET_QUERY, id);
	}
	
	public List<Map<String, Object>> list() {
		return jdbcTemplate.queryForList(LIST_QUERY);
	}
	
//	public List<Map<String, Object>> get(String columnName, String conditionColumn, Object value) {
//		try {
//			return jdbcTemplate.queryForList("SELECT `" + columnName +"` FROM `zerocode`.`company_contact` WHERE `" + conditionColumn +"` = ?;", value);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
