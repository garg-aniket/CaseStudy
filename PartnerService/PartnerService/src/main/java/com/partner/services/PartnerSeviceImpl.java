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
	
	/**
	 * Retrieve a partner by its unique identifier.
	 *
	 * @param partnerId The ID of the partner to retrieve.
	 * @return The partner entity if found, or throw a ResourceNotFoundException.
	 * @throws ResourceNotFoundException if the partner with the specified ID is not found.
	 */
	@Override
	public Partner getPartnerById(int partnerId) {
		return partnerRepository.findById(partnerId).orElseThrow(() -> new ResourceNotFoundException("Partner", "Id" ,partnerId));
	}

	/**
	 * Retrieve a list of all partners.
	 *
	 * @return A list of all partners.
	 */
	@Override
	public List<Partner> getAllPartner() {
		return partnerRepository.findAll();
	}

	/**
	 * Create a new partner.
	 *
	 * @param partner The partner entity to be created.
	 * @return The created partner entity.
	 */
	@Override
	public Partner savePartner(Partner partner) {
		return partnerRepository.save(partner);
	}

	/**
	 * Update an existing partner by ID.
	 *
	 * @param partner    The updated partner information.
	 * @param partnerId  The ID of the partner to be updated.
	 * @return The updated partner entity.
	 * @throws ResourceNotFoundException if the partner with the specified ID is not found.
	 */
	@Override
	public Partner updatePartner(Partner partner,int partnerId) {
		Partner prevPartner=partnerRepository.findById(partnerId).orElseThrow(() -> new ResourceNotFoundException("Partner", "Id" ,partnerId));
		prevPartner.setPartnerName(partner.getPartnerName());
		prevPartner.setPartnerDescription(partner.getPartnerDescription());
		prevPartner.setPartnerImageUrl(partner.getPartnerImageUrl());
		Partner updatedPartner = partnerRepository.save(prevPartner);
		return updatedPartner;
	}

	/**
	 * Delete a partner by ID.
	 *
	 * @param partnerId The ID of the partner to be deleted.
	 * @throws ResourceNotFoundException if the partner with the specified ID is not found.
	 */
	@Override
	public void deletePartner(int partnerId) {
		Partner partner=partnerRepository.findById(partnerId).orElseThrow(() -> new ResourceNotFoundException("Partner", "Id" ,partnerId));
		
		// Delete the partner entity
		partnerRepository.delete(partner);
	}

	
	
	
}
