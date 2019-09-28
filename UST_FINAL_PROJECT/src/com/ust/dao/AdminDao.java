package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ust.beans.ContactPerson;
import com.ust.beans.Login;
import com.ust.beans.Vendor;
import com.ust.beans.VendorContact;


public class AdminDao {
	JdbcTemplate jdbcTemplate;

	public void setTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public Login validate(String username, String password) {
		String sql = "select username,password from login where username='"+username+"' and Password='"+password+"'";
		return jdbcTemplate.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Login>(Login.class));
	}
	//View all vendors
		public List<Vendor> getVendors() {
			return jdbcTemplate.query("select vendorId,vendorName,vendorAddress,vendorLocation,service,pincode,isActive from vendor where isActive='1' ",
							new RowMapper<Vendor>() {
								public Vendor mapRow(ResultSet rs, int row)
										throws SQLException {
									Vendor v = new Vendor();
									v.setVendorId(rs.getInt(1));
									v.setVendorName(rs.getString(2));
									v.setVendorAddress(rs.getString(3));
									v.setVendorLocation(rs.getString(4));
									v.setService(rs.getString(5));
									v.setPincode(rs.getInt(6));
									v.setIsActive(rs.getInt(7));
									return v;
								}
							});
		}
		//View Vendor By Id
		public Vendor getVendorByIdd(int vendorId) {
			String sql="select vendorId,vendorName,vendorAddress,vendorLocation,service,pincode,isActive from vendor "
							+ " where vendorId=" + vendorId ;
			return jdbcTemplate.queryForObject(sql, new Object[]{},
					new BeanPropertyRowMapper<Vendor>(Vendor.class));
		}
		
		//View vendor and contact details by vendor id
		public VendorContact getVendorById(int vendorId) {
			String sql ="select vc.vendorId,vc.vendorName,vc.vendorAddress,vc.vendorLocation,vc.service,vc.pincode,cd.contactName,cd.department,cd.email,cd.phone,cd.contactId from vendor vc join contactperson cd on vc.vendorId=cd.vendorId where vc.isActive=1 and vc.vendorId="+ vendorId + "";
			return jdbcTemplate.queryForObject(sql,new Object[]{},
					new BeanPropertyRowMapper<VendorContact>(VendorContact.class));
		}

		//Add new Vendor
		public int insertVendor(Vendor vendor)
		{
			String sql="insert into vendor(vendorName,vendorAddress,vendorLocation,service,pincode,isActive)values('"+vendor.getVendorName()+"','"+vendor.getVendorAddress()+"','"+vendor.getVendorLocation()+"','"+vendor.getService()+"',"+vendor.getPincode()+",1)";
			return jdbcTemplate.update(sql);
		}
		
		//Update details of existing vendor
		public int updateVendor(Vendor vendor)
		{
			String sql="update vendor set vendorName='"+vendor.getVendorName()+"',vendorAddress='"+vendor.getVendorAddress()+"',vendorLocation='"+vendor.getVendorLocation()+"',service='"+vendor.getService()+"',pincode="+vendor.getPincode()+" where vendorId="+vendor.getVendorId();
			return jdbcTemplate.update(sql);
		}
		// disable vendor
				public int disableVendor(int vendorId) {

					String sql = "update vendor set isActive=0 where vendorId="
							+ vendorId + "";

					return jdbcTemplate.update(sql);
				}
				//-----------------------Insert vendor and contact person details--------------------------------------
				
				//to add new vendor and contact person
				public int saveVendor(VendorContact vc) {

					String sql1 = "insert into vendor(vendorName,vendorAddress,vendorLocation,service,pincode,isActive) values "
							+ "('"
							+ vc.getVendorName()
							+ "','"
							+ vc.getVendorAddress()
							+ "','"
							+ vc.getVendorLocation()
							+ "','"
							+ vc.getService()
							+ "','"
							+ vc.getPincode()
							+ "',"
							+ '1'
							+ ")";

					 jdbcTemplate.update(sql1);
					 
					 Integer maxId = getSequence();
					 String sql2="insert into contactperson(vendorId,contactName,department,email,phone) values ("
							 + maxId
								+ ",'"
								+ vc.getContactName()
								+ "','"
								+ vc.getDepartment()
								+ "','"
								+ vc.getEmail()
								+ "'," + vc.getPhone() + ")";
					 return jdbcTemplate.update(sql2);

							 
					 
				}

				//new vendorId
				private Integer getSequence() {
				Integer seq;
				String sql = "select MAX(vendorId)from vendor";
				seq = jdbcTemplate.queryForObject(sql, new Object[] {},Integer.class);
				return seq;
				}
				
				//to update
				public int updateVendor(int vendorId, VendorContact vc) {

					String sql1="update vendor set vendorName='"+vc.getVendorName()+"',vendorAddress='"+vc.getVendorAddress()+"',vendorLocation='"+vc.getVendorLocation()+"',service='"+vc.getService()+"',pincode="+vc.getPincode()+" where vendorId="+vc.getVendorId();
					jdbcTemplate.update(sql1);

					// Integer maxId = getSequence();

					String sql="update contactperson set contactName='"+vc.getContactName()+"',vendorId='"+vc.getVendorId()+"',department='"+vc.getDepartment()+"',email='"+vc.getEmail()+"',phone="+vc.getPhone()+" where contactId="+vc.getContactId();


					return jdbcTemplate.update(sql);
				}
		
				//view contact person
				public List<ContactPerson> getContacts(int vendorId) {
					return jdbcTemplate.query("select contactId,contactName,vendorId,department,email,phone from contactperson",
									new RowMapper<ContactPerson>() {
										public ContactPerson mapRow(ResultSet rs, int row)
												throws SQLException {
											ContactPerson c = new ContactPerson();
											c.setContactId(rs.getInt(1));
											c.setContactName(rs.getString(2));
											c.setVendorId(rs.getInt(3));
											c.setDepartment(rs.getString(4));
											c.setEmail(rs.getString(5));
											c.setPhone(rs.getString(6));
											return c;
										}
									});
				}



	//view vendor by name
	public Vendor getVendorByName(String vendorName) {
		
		String sql="select vendorId,vendorName,address,location,service,pincode from vendor_details where vendorName='"+vendorName+"'";
		return jdbcTemplate.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Vendor>(Vendor.class));
	}


	
	//View contact person By Id
	public ContactPerson getContactById(int contactId) {
		String sql="select contactId,contactName,vendorId,department,email,phone,isActive from contactperson "
						+ " where contactId=" + contactId ;
		return jdbcTemplate.queryForObject(sql, new Object[]{},
				new BeanPropertyRowMapper<ContactPerson>(ContactPerson.class));
	}
	
	//Add new contact
	public int insertContact(ContactPerson contact)
	{
		String sql="insert into contactperson(contactName,vendorId,department,email,phone,isActive)values('"+contact.getContactName()+"','"+contact.getVendorId()+"','"+contact.getDepartment()+"','"+contact.getEmail()+"',"+contact.getPhone()+",1)";
		return jdbcTemplate.update(sql);
	}
	
	//Update details of existing contact person
	public int updateContact(ContactPerson contact)
	{
		String sql="update contactperson set contactName='"+contact.getContactName()+"',vendorId='"+contact.getVendorId()+"',department='"+contact.getDepartment()+"',email='"+contact.getEmail()+"',phone="+contact.getPhone()+" where contactId="+contact.getContactId();
		return jdbcTemplate.update(sql);
	}
	
	// disable contact person
		public int disableContact(int contactId) {

			String sql = "update contactperson set isActive=0 where contactId="
					+ contactId + "";

			return jdbcTemplate.update(sql);
		}
	

}


		
		
		 



