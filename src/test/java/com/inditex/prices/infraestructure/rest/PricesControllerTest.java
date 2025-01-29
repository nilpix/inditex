package com.inditex.prices.infraestructure.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricesControllerTest {
    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";

    @Autowired
    private MockMvc mockMvc;

    private static Object[] getTestParameters() {
        return new Object[][]{
                // filterDate, expectedPrice, expectedPriceList, expectedStartDate, expectedEndDate
                {"2020-06-14T10:00:00", 35.5F, 1, "2020-06-14T00:00:00", "2020-12-31T23:59:59"},
                {"2020-06-14T16:00:00", 25.45F, 2, "2020-06-14T15:00:00", "2020-06-14T18:30:00"},
                {"2020-06-14T21:00:00", 35.50F, 1, "2020-06-14T00:00:00", "2020-12-31T23:59:59"},
                {"2020-06-15T10:00:00", 30.50F, 3, "2020-06-15T00:00:00", "2020-06-15T11:00:00"},
                {"2020-06-16T21:00:00", 38.95F, 4, "2020-06-15T16:00:00", "2020-12-31T23:59:59"}
        };
    }

    @ParameterizedTest
    @MethodSource("getTestParameters")
    void shouldReturnPriceForDateProductAndBrand(
            String filterDate,
            float expectedPrice,
            int expectedPriceList,
            String expectedStartDate,
            String expectedEndDate) throws Exception {

        mockMvc.perform(get("/api/prices/filter")
                        .param("date", filterDate)
                        .param("productId", PRODUCT_ID)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(Integer.parseInt(PRODUCT_ID)))
                .andExpect(jsonPath("$.brandId").value(Integer.parseInt(BRAND_ID)))
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.priceList").value(expectedPriceList))
                .andExpect(jsonPath("$.startDate").value(expectedStartDate))
                .andExpect(jsonPath("$.endDate").value(expectedEndDate));
    }

    @Test
    void shouldReturn404WhenProductNotFound() throws Exception {
        String nonExistentProductId = "99999";
        String testDate = "2020-06-14T10:00";
        String expectedErrorMessage = "Product not found " + nonExistentProductId + " on date " + testDate;

        mockMvc.perform(get("/api/prices/filter")
                        .param("date", testDate)
                        .param("productId", nonExistentProductId)
                        .param("brandId", BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.error").value(equalTo(expectedErrorMessage)))
                .andExpect(result -> {
                    String responseBody = result.getResponse().getContentAsString();
                    System.out.println("Error Response: " + responseBody);
                });
    }

    @Test
    void shouldReturnCannotBeNullBadRequest() throws Exception {
        String testDate = "2020-06-14T10:00";
        //String expectedErrorMessage = "Product not found " + nonExistentProductId + " on date " + testDate;

        mockMvc.perform(get("/api/prices/filter")
                        .param("date", testDate)
                        .param("productId", (String) null)
                        .param("brandId", (String) null)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.productId").value(equalTo("must not be null")))
                .andExpect(jsonPath("$.errors.brandId").value(equalTo("must not be null")))
                .andExpect(result -> {
                    String responseBody = result.getResponse().getContentAsString();
                    System.out.println("Error Response: " + responseBody);
                });
    }




}
