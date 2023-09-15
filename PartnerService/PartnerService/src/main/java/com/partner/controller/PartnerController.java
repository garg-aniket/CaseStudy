package com.partner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.partner.model.Partner;
import com.partner.services.PartnerService;
import com.partner.Response.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {
	
	@Autowired
	PartnerService partnerService;
	
	/**
	 * Create a new partner.
	 * 
	 * @param partner The partner to be created.
	 * @return A response containing the created partner.
	 */
	@PostMapping(value="/newPartner")
	public ResponseEntity<Partner> createPartner(@Valid @RequestBody Partner partner){
		Partner res=partnerService.savePartner(partner);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
	/**
	 * Retrieve a list of all partners.
	 * 
	 * @return A response containing a list of all partners.
	 */
	@GetMapping(value="/getAllPartner")
	public ResponseEntity<List<Partner>> findAllPartner(){
		List<Partner> res=partnerService.getAllPartner();
		return ResponseEntity.ok(res);
	}
	
	/**
	 * Update an existing partner by ID.
	 * 
	 * @param partner The updated partner information.
	 * @param partnerId The ID of the partner to be updated.
	 * @return A response containing the updated partner.
	 */
	@PutMapping(value = "/updatePartner/{partnerId}")
    private ResponseEntity<Partner> updatePartner(@Valid @RequestBody Partner partner, @PathVariable int partnerId) {
        Partner res = partnerService.updatePartner(partner, partnerId);
        return ResponseEntity.ok(res);
    }
	
	/**
	 * Delete a partner by ID.
	 * 
	 * @param partnerId The ID of the partner to be deleted.
	 * @return A response indicating the success of the delete operation.
	 */
	@DeleteMapping("deletePartner/{partnerId}")
    public ResponseEntity<ApiResponse> deletePartner(@PathVariable int partnerId) {
        partnerService.deletePartner(partnerId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(" deleted Successfully", true),HttpStatus.OK);
    }
	
	/**
	 * Get a partner by ID.
	 * 
	 * @param partnerId The ID of the partner to retrieve.
	 * @return A response containing the partner with the specified ID.
	 */
    @GetMapping("getPartnerById/{partnerId}")
    public ResponseEntity<Partner> getPartnerById(@PathVariable int partnerId) {
    	Partner res =  partnerService.getPartnerById(partnerId);
        return ResponseEntity.ok(res);
    }

}
