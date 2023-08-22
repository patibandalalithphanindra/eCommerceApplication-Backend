package com.lalith.backendproject.eCommerceApplication;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lalith.backendproject.eCommerceApplication.controller.InventoryManagementController;
import com.lalith.backendproject.eCommerceApplication.service.InventoryManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@WebMvcTest(InventoryManagementController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryManagementService inventoryManagementService;

    @Test
    public void testGetAllProducts() throws Exception {
        when(inventoryManagementService.getAllProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/inventory/getAllProducts"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testDeleteInventory() throws Exception {
        String productIdToDelete = "P1";

        when(inventoryManagementService.deleteProduct(productIdToDelete)).thenReturn(productIdToDelete + " Product has been deleted successfully!");

        mockMvc.perform(delete("/inventory/" + productIdToDelete))
                .andExpect(status().isOk())
                .andExpect(content().string(productIdToDelete + " Product has been deleted successfully!"));
    }

}
