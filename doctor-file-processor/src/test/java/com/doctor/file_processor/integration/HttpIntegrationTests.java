package com.doctor.file_processor.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class HttpIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testThatHttpVerifiesValidRecord() throws Exception {
        String expectedResponseBody = "ApartmentDto[id=1, name=CentralParkView, address=AddressDto[id=101, " +
                "streetName=ElmStreet, streetNumber=42A, streetNumberSecondary=Unit5, postalCode=1000, " +
                "cityName=Ljubljana, country=Slovenia], petsAllowed=true, numberOfRooms=4, " +
                "yearBuilt=2015, squareMeters=120, owners=[OwnerDto[id=201, name=AnaNovak], " +
                "OwnerDto[id=202, name=MarkoKranjc]], typeOfTransaction=PURCHASE, typeOfApartment=APARTMENT, price=250000]";
        String event1valid = "{\"id\":1,\"name\":\"CentralParkView\",\"address\":" +
                "{\"id\":101,\"streetName\":\"ElmStreet\",\"streetNumber\":\"42A\"," +
                "\"streetNumberSecondary\":\"Unit5\",\"postalCode\":\"1000\",\"cityName\":\"Ljubljana\"," +
                "\"country\":\"Slovenia\"},\"petsAllowed\":true,\"numberOfRooms\":4,\"yearBuilt\":2015," +
                "\"squareMeters\":120,\"owners\":[{\"id\":201,\"name\":\"AnaNovak\"},{\"id\":202," +
                "\"name\":\"MarkoKranjc\"}],\"typeOfTransaction\":\"PURCHASE\",\"typeOfApartment\":\"APARTMENT\",\"price\":250000}";

        mockMvc
                .perform(post("/api/apartment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(event1valid)
                        .with(request -> {
                            request.setRemoteAddr("192.168.1.1"); // set client IP
                            return request;
                        }))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .string(expectedResponseBody)
                );
    }

    @Test
    void testThatHttpVerifiesInvalidRecord() throws Exception {
        String expectedResponseEndTime = "name: must not be empt";
        String expectedResponseTitle = "address: must not be null";
        String expectedResponseStartTime ="yearBuilt: must not be null";
        String event1invalid = "{\"id\":1,\"name\":\"\",\"petsAllowed\":true,\"numberOfRooms\":4,\"squareMeters\":120,\"owners\":[{\"id\":201,\"name\":\"AnaNovak\"},{\"id\":202,\"name\":\"MarkoKranjc\"}],\"typeOfTransaction\":\"PURCHASE\",\"typeOfApartment\":\"APARTMENT\",\"price\":250000}";

        mockMvc
                .perform(post("/api/apartment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(event1invalid)
                        .with(request -> {
                            request.setRemoteAddr("192.168.1.2"); // set client IP
                            return request;
                        }))
                .andExpect(status()
                        .isBadRequest())
                .andExpect(content().string(containsString(expectedResponseTitle)))
                .andExpect(content().string(containsString(expectedResponseStartTime)))
                .andExpect(content().string(containsString(expectedResponseEndTime)))
        ;
    }

    @Test
    void testThatHttpReachesRateLimit() throws Exception {
        String event1valid = "{\"id\":1,\"name\":\"CentralParkView\",\"address\":" +
                "{\"id\":101,\"streetName\":\"ElmStreet\",\"streetNumber\":\"42A\"," +
                "\"streetNumberSecondary\":\"Unit5\",\"postalCode\":\"1000\",\"cityName\":\"Ljubljana\"," +
                "\"country\":\"Slovenia\"},\"petsAllowed\":true,\"numberOfRooms\":4,\"yearBuilt\":2015," +
                "\"squareMeters\":120,\"owners\":[{\"id\":201,\"name\":\"AnaNovak\"},{\"id\":202," +
                "\"name\":\"MarkoKranjc\"}],\"typeOfTransaction\":\"PURCHASE\",\"typeOfApartment\":\"APARTMENT\",\"price\":250000}";

        int numberOfThreads = 2;
        try (ExecutorService service = Executors.newFixedThreadPool(10)) {
            CountDownLatch latch = new CountDownLatch(numberOfThreads);
            for (int i = 0; i < numberOfThreads; i++) {
                service.submit(() -> {
                    try {
                        mockMvc
                                .perform(post("/api/apartment")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(event1valid)
                                        .with(request -> {
                                            request.setRemoteAddr("192.168.1.3"); // set client IP
                                            return request;
                                        }))
                                .andExpect(status()
                                        .isOk());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    latch.countDown();
                });
            }
            latch.await();
        }
    }

    @Test
    public void testCounterWithConcurrency() {
        String event1valid = "{\"id\":1,\"name\":\"CentralParkView\",\"address\":" +
                "{\"id\":101,\"streetName\":\"ElmStreet\",\"streetNumber\":\"42A\"," +
                "\"streetNumberSecondary\":\"Unit5\",\"postalCode\":\"1000\",\"cityName\":\"Ljubljana\"," +
                "\"country\":\"Slovenia\"},\"petsAllowed\":true,\"numberOfRooms\":4,\"yearBuilt\":2015," +
                "\"squareMeters\":120,\"owners\":[{\"id\":201,\"name\":\"AnaNovak\"},{\"id\":202," +
                "\"name\":\"MarkoKranjc\"}],\"typeOfTransaction\":\"PURCHASE\",\"typeOfApartment\":\"APARTMENT\",\"price\":250000}";
        int numberOfThreads = 10;

        try(ExecutorService service = Executors.newFixedThreadPool(10)) {
            final int[] counter = {0};
            CountDownLatch latch = new CountDownLatch(numberOfThreads);
            for (int i = 0; i < numberOfThreads; i++) {
                log.info(String.valueOf(i));
                service.submit(() -> {
                    try {
                        mockMvc
                                .perform(post("/api/apartment")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(event1valid)
                                        .with(request -> {
                                            request.setRemoteAddr("192.168.1.4"); // set client IP
                                            return request;
                                        }))
                                .andExpect(status()
                                        .isOk())
                                .andReturn();
                        counter[0]++;

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        latch.countDown();
                    }
                });
            }
            latch.await();
            assertThat(counter[0]).isEqualTo(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
