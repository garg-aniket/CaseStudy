package com.partner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partner.exception.ResourceNotFoundException;
import com.partner.model.Partner;
import com.partner.repository.PartnerRepository;

@Service
public class PartnerSeviceImpl implements PartnerService {
        
	@Autowired
	PartnerRepository partnerRepository;
	
	@Override
	public Partner getPartnerById(int partnerId) {
		// TODO Auto-generated method stub
		return partnerRepository.findById(partnerId).orElseThrow(() -> new ResourceNotFoundException("Partner", "Id" ,partnerId));
	}

	@Override
	public List<Partner> getAllPartner() {
		// TODO Auto-generated method stub
		return partnerRepository.findAll();
	}

	@Override
	public Partner savePartner(Partner partner) {
		// TODO Auto-generated method stub
		return partnerRepository.save(partner);
	}

	@Override
	public Partner updatePartner(Partner partner,int partnerId) {
		// TODO Auto-generated method stub
		Partner prevPartner=partnerRepository.findById(partnerId).orElseThrow(() -> new ResourceNotFoundException("Partner", "Id" ,partnerId));
		prevPartner.setPartnerName(partner.getPartnerName());
		prevPartner.setPartnerDescription(partner.getPartnerDescription());
		prevPartner.setPartnerImageUrl(partner.getPartnerImageUrl());
		Partner updatedPartner = partnerRepository.save(prevPartner);
		return updatedPartner;
	}

	@Override
	public void deletePartner(int partnerId) {
		// TODO Auto-generated method stub
		Partner partner=partnerRepository.findById(partnerId).orElseThrow(() -> new ResourceNotFoundException("Partner", "Id" ,partnerId));
	 partnerRepository.delete(partner);
	}

	
	
	
}
