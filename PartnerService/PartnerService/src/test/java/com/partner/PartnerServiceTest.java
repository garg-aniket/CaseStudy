package com.partner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.partner.exception.ResourceNotFoundException;
import com.partner.model.Partner;
import com.partner.repository.PartnerRepository;
import com.partner.services.PartnerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PartnerServiceTest {

    @InjectMocks
    private PartnerServiceImpl partnerService;

    @Mock
    private PartnerRepository partnerRepository;

    @Before
    public void setUp() {
    	 MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPartnerById() {
        // Create a sample Partner
        Partner samplePartner = new Partner();
        samplePartner.setPartnerId(1);
        samplePartner.setPartnerName("Test Partner");

        // Mock the behavior of partnerRepository.findById
        when(partnerRepository.findById(1)).thenReturn(Optional.of(samplePartner));

        // Test the getPartnerById method
        Partner result = partnerService.getPartnerById(1);

        // Assertions
        assertEquals(1, result.getPartnerId());
        assertEquals("Test Partner", result.getPartnerName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetPartnerByIdNotFound() {
        // Mock the behavior of partnerRepository.findById
        when(partnerRepository.findById(1)).thenReturn(Optional.empty());

        // Test the getPartnerById method with an invalid ID
        partnerService.getPartnerById(1);
    }

    @Test
    public void testGetAllPartner() {
        // Create a list of sample partners
        List<Partner> samplePartners = new ArrayList<>();
        samplePartners.add(new Partner(1, "Partner 1", "Description 1", "Image URL 1"));
        samplePartners.add(new Partner(2, "Partner 2", "Description 2", "Image URL 2"));

        // Mock the behavior of partnerRepository.findAll
        when(partnerRepository.findAll()).thenReturn(samplePartners);

        // Test the getAllPartner method
        List<Partner> result = partnerService.getAllPartner();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Partner 1", result.get(0).getPartnerName());
        assertEquals("Partner 2", result.get(1).getPartnerName());
    }

    @Test
    public void testSavePartner() {
        // Create a sample Partner
        Partner samplePartner = new Partner();
        samplePartner.setPartnerId(1);
        samplePartner.setPartnerName("Test Partner");

        // Mock the behavior of partnerRepository.save
        when(partnerRepository.save(any(Partner.class))).thenReturn(samplePartner);

        // Test the savePartner method
        Partner partnerToSave = new Partner();
        partnerToSave.setPartnerName("Test Partner");
        Partner result = partnerService.savePartner(partnerToSave);

        // Assertions
        assertEquals(1, result.getPartnerId());
        assertEquals("Test Partner", result.getPartnerName());
    }

    @Test
    public void testUpdatePartner() {
        // Create a sample Partner and an updated Partner
        Partner samplePartner = new Partner();
        samplePartner.setPartnerId(1);
        samplePartner.setPartnerName("Test Partner");

        Partner updatedPartner = new Partner();
        updatedPartner.setPartnerId(1);
        updatedPartner.setPartnerName("Updated Partner");

        // Mock the behavior of partnerRepository.findById and partnerRepository.save
        when(partnerRepository.findById(1)).thenReturn(Optional.of(samplePartner));
        when(partnerRepository.save(any(Partner.class))).thenReturn(updatedPartner);

        // Test the updatePartner method
        Partner result = partnerService.updatePartner(updatedPartner, 1);

        // Assertions
        assertEquals(1, result.getPartnerId());
        assertEquals("Updated Partner", result.getPartnerName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdatePartnerNotFound() {
        // Mock the behavior of partnerRepository.findById to simulate a not found scenario
        when(partnerRepository.findById(1)).thenReturn(Optional.empty());

        // Test the updatePartner method with an invalid ID
        partnerService.updatePartner(new Partner(), 1);
    }

    @Test
    public void testDeletePartner() {
        // Create a sample Partner
        Partner samplePartner = new Partner();
        samplePartner.setPartnerId(1);

        // Mock the behavior of partnerRepository.findById and partnerRepository.delete
        when(partnerRepository.findById(1)).thenReturn(Optional.of(samplePartner));
        doNothing().when(partnerRepository).delete(samplePartner);

        // Test the deletePartner method
        partnerService.deletePartner(1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeletePartnerNotFound() {
        // Mock the behavior of partnerRepository.findById to simulate a not found scenario
        when(partnerRepository.findById(1)).thenReturn(Optional.empty());

        // Test the deletePartner method with an invalid ID
        partnerService.deletePartner(1);
    }
}

