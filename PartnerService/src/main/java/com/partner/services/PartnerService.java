package com.partner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partner.model.Partner;
import com.partner.repository.PartnerRepository;



public interface PartnerService {

	
	Partner savePartner(Partner var);
	
	Partner getPartnerById(int partnerId);
	
	List<Partner> getAllPartner();
	
	
	
	Partner updatePartner(Partner parnter,int partnerId);
	
	void deletePartner(int partnerId);
	
	
	
}
