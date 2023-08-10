package de.zeroco.companycontact.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.zeroco.companycontact.entity.Company;

@Repository
public class CompanyDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public final String INSERT_QUERY = "INSERT INTO `zerocode`.`company` (`name`, `email`, `number`, `address`) VALUES (?, ?, ?, ?);";
	public final String UPDATE_QUERY = "UPDATE `zerocode`.`company` SET`name` = ?, `email` = ?, `number` = ?, `address` = ? WHERE `pk_id` = ?;";
	public final String DELETE_QUERY = "DELETE FROM `zerocode`.`company` WHERE `pk_id` = ?;";
	public final String GET_QUERY = "SELECT company.pk_id id, company.name name, company.email email, company.number number, company.address address, JSON_ARRAYAGG(JSON_OBJECT('Id', company_contact.pk_id, 'name', company_contact.name, 'email', company_contact.email, 'number', company_contact.number, 'Date Of Birth', company_contact.date_of_birth)) contact FROM zerocode.company LEFT JOIN zerocode.company_contact ON company.pk_id = company_contact.company_id WHERE company.pk_id = ? GROUP BY company.pk_id;";
	public final String LIST_QUERY = "SELECT company.pk_id id, company.name name, company.email email, company.number number, company.address address, JSON_ARRAYAGG(JSON_OBJECT('Id', company_contact.pk_id, 'name', company_contact.name, 'email', company_contact.email, 'number', company_contact.number, 'Date Of Birth', company_contact.date_of_birth)) contact FROM zerocode.company LEFT JOIN zerocode.company_contact ON company.pk_id = company_contact.company_id GROUP BY company.pk_id;";
	
	public int save(Company company) {
		return jdbcTemplate.update(INSERT_QUERY, company.getName(), company.getEmail(), company.getNumber(), company.getAddress());
	}
	
	public int update(Company company) {
		return jdbcTemplate.update(UPDATE_QUERY, company.getName(), company.getEmail(), company.getNumber(), company.getAddress(), company.getId());
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
	
	public Map<String, Object> get(String columnName, Object columnValue) {
		try {
			return jdbcTemplate.queryForMap("SELECT * FROM `zerocode`.`company` WHERE `" + columnName + "` = ?;", columnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
