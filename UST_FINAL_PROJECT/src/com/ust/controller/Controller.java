package com.ust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.beans.Login;
import com.ust.beans.Vendor;
import com.ust.dao.AdminDao;
import com.ust.beans.VendorContact;
import com.ust.beans.ContactPerson;





@RestController
public class Controller {
	@Autowired
	AdminDao dao;
	
	//User validation
		@RequestMapping(value = "/api/login/{username}/{password}", method = RequestMethod.GET)
		@ResponseBody
		public Login selectUser(@PathVariable("username") String username,
		@PathVariable("password") String Password) {
		return dao.validate(username, Password);
		}
		//  view vendor list
		@RequestMapping(value = "/api/viewallvendors", method = RequestMethod.GET)
		@ResponseBody
		public List<Vendor> getVendors()
		{
			List list=dao.getVendors();
			return list;
			
		}
		//View Vendor by Id
		@RequestMapping(value = "/api/vendorById/{vendorId}", method = RequestMethod.GET)
		public VendorContact getVendorById(@PathVariable("vendorId") int vendorId)
		{
			return dao.getVendorById(vendorId);
		}
	
	// view vendor list by name
		@RequestMapping(value = "/api/vendorname/{vendorName}", method = RequestMethod.GET)
		@ResponseBody
		public Vendor getVendorByName(Model m,@PathVariable("vendorName") String vendorName){
			return dao.getVendorByName(vendorName);
			
	}
	

	// disable Vendor
			@RequestMapping(value = "/api/disableVendor/{vendorId}", method = {RequestMethod.PUT,RequestMethod.GET})
			void doctorDisable(@PathVariable("vendorId") int vendorId) {
				dao.disableVendor(vendorId);
			}
	
		
			//view all contact persons under particular vendor
			@RequestMapping(value = "/api/viewallcontacts/{vendorId}", method = RequestMethod.GET)
			@ResponseBody
			public List<ContactPerson> getContacts(@PathVariable ("vendorId")int vendorId)
			{
				List<ContactPerson> list=dao.getContacts(vendorId);
				return list;
				
			}
			
			//View Contact person by Id
			@RequestMapping(value = "/api/contactById/{contactId}", method = RequestMethod.GET)
			public ContactPerson getContactById(@PathVariable("contactId") int contactId)
			{
				return dao.getContactById(contactId);
			}
			
			//Add new ContactPerson
			@RequestMapping(value = "/api/insertContact", method = {RequestMethod.POST,RequestMethod.GET})
			public void insertContact(@RequestBody ContactPerson c)
			{
				dao.insertContact(c);
				
			}
			//Inserting vendor and contactPerson details
			@RequestMapping(value = "/api/VendorPerson/insert", method = RequestMethod.POST)
			@ResponseBody
			public void insertVendorAndPerson(@RequestBody VendorContact vendorcontact)
			{
			dao.saveVendor(vendorcontact);
			}
			

	

			

		

	

}
