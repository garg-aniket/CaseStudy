package com.partner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.partner.model.Partner;
import com.partner.services.PartnerService;
import com.partner.controller.PartnerController;

import jakarta.validation.Validator;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class PartnerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PartnerController partnerController;

    @Mock
    private PartnerService partnerService;

    @Autowired
    private Validator validator; 

    @Before
    public void setup() {
    	// Initialize Mockito annotations and configure the MockMvc instance
    	 MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(partnerController).build();
    }

    @Test
    public void testCreatePartner() throws Exception {
        // Create a sample Partner object
        Partner partner = new Partner();
        partner.setPartnerName("Test Partner");

        // Mock the service method
        when(partnerService.savePartner(any())).thenReturn(partner);

        // Perform a POST request to create a partner
        mockMvc.perform(post("/api/partner/newPartner")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"partnerName\":\"Test Partner\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.partnerName", is("Test Partner")));
    }

    @Test
    public void testGetAllPartner() throws Exception {
        // Create a list of sample partners
        List<Partner> partners = new ArrayList<>();
        partners.add(new Partner(1, "Partner 1","Good Partner","https://www.example.com/image.jpg"));
        partners.add(new Partner(2, "Partner 2","Good Partner2","https://www.example2.com/image.jpg"));

        // Mock the service method
        when(partnerService.getAllPartner()).thenReturn(partners);

        // Perform a GET request to retrieve all partners
        mockMvc.perform(get("/api/partner/getAllPartner"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].partnerName", is("Partner 1")))
                .andExpect(jsonPath("$[1].partnerName", is("Partner 2")));
    }

    @Test
    public void testUpdatePartner() throws Exception {
        // Create a sample Partner object
        Partner partner = new Partner();
        partner.setPartnerName("Updated Partner");

        // Mock the service method
        when(partnerService.updatePartner(any(), anyInt())).thenReturn(partner);

        // Perform a PUT request to update a partner
        mockMvc.perform(put("/api/partner/updatePartner/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"partnerName\":\"Updated Partner\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.partnerName", is("Updated Partner")));
    }

    @Test
    public void testDeletePartner() throws Exception {
        // Mock the service method
        doNothing().when(partnerService).deletePartner(anyInt());

        // Perform a DELETE request to delete a partner
        mockMvc.perform(delete("/api/partner/deletePartner/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("deleted Successfully")))
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void testGetPartnerById() throws Exception {
        // Create a sample Partner object
        Partner partner = new Partner(1, "Partner 1","Good Partner","https://www.example.com/image.jpg");

        // Mock the service method
        when(partnerService.getPartnerById(anyInt())).thenReturn(partner);

        // Perform a GET request to retrieve a partner by ID
        mockMvc.perform(get("/api/partner/getPartnerById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.partnerName", is("Test Partner")));
    }
}