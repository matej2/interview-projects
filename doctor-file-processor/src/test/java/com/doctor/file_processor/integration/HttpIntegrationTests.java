package com.doctor.file_processor.integration;

import com.doctor.file_processor.repositories.AccessInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    @Autowired
    private AccessInfoRepository accessInfoRepository;

    @Test
    void testThatHttpVerifiesValidRecord() throws Exception {
        String expectedResponseBody = "EventDto[id=null, title=IntroductiontoAI," +
                        " startTime=Thu Oct 09 20:08:32 CEST 2025," +
                        " endTime=Sat Oct 11 20:08:32 CEST 2025," +
                        " location=null, eventStatus=SCHEDULED, description=null," +
                        " participantList=[ParticipantDto[participantId=1, role=Observer," +
                        " confirmationStatus=PENDING]]]";
        String event1valid = "{\"id\":null,\"title\":\"IntroductiontoAI\",\"startTime\":1760033312987,\"endTime\":1760206112987,\"location\":null,\"eventStatus\":\"SCHEDULED\",\"description\":null,\"participantList\":[{\"participantId\":1,\"role\":\"Observer\",\"confirmationStatus\":\"PENDING\"}]}";

        mockMvc
                .perform(post("/api/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(event1valid))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .string(expectedResponseBody)
                );
    }

    @Test
    void testThatHttpVerifiesInvalidRecord() throws Exception {
        String expectedResponseEndTime = "endTime: must not be null";
        String expectedResponseTitle = "title: must not be blank";
        String expectedResponseStartTime ="startTime: must be a date in the present or in the future";
        String event1invalid = "{\"id\":null,\"title\":\"\",\"startTime\":1759775574205,\"endTime\":null,\"location\":null,\"eventStatus\":null,\"description\":null,\"participantList\":[{\"participantId\":1,\"role\":\"\",\"confirmationStatus\":null}]}";

        mockMvc
                .perform(post("/api/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(event1invalid))
                .andExpect(status()
                        .isBadRequest())
                .andExpect(content().string(containsString(expectedResponseTitle)))
                .andExpect(content().string(containsString(expectedResponseStartTime)))
                .andExpect(content().string(containsString(expectedResponseEndTime)))
        ;
    }

    @Test
    void testThatHttpReachesRateLimit() throws Exception {
        String event1valid = "{\"id\":null,\"title\":\"IntroductiontoAI\",\"startTime\":1870037312987,\"endTime\":1970037312987,\"location\":null,\"eventStatus\":\"SCHEDULED\",\"description\":null,\"participantList\":[{\"participantId\":1,\"role\":\"Observer\",\"confirmationStatus\":\"PENDING\"}]}";

        int numberOfThreads = 2;
        try (ExecutorService service = Executors.newFixedThreadPool(10)) {
            CountDownLatch latch = new CountDownLatch(numberOfThreads);
            for (int i = 0; i < numberOfThreads; i++) {
                service.submit(() -> {
                    try {
                        mockMvc
                                .perform(post("/api/file")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(event1valid))
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
        String event1valid = "{\"id\":null,\"title\":\"IntroductiontoAI\",\"startTime\":1870037312987,\"endTime\":1970037312987,\"location\":null,\"eventStatus\":\"SCHEDULED\",\"description\":null,\"participantList\":[{\"participantId\":1,\"role\":\"Observer\",\"confirmationStatus\":\"PENDING\"}]}";

        int numberOfThreads = 10;

        try(ExecutorService service = Executors.newFixedThreadPool(10)) {
            CountDownLatch latch = new CountDownLatch(numberOfThreads);
            for (int i = 0; i < numberOfThreads; i++) {
                log.info(String.valueOf(i));
                service.submit(() -> {
                    try {
                        MvcResult result = mockMvc
                                .perform(post("/api/file")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(event1valid))
                                .andExpect(status()
                                        .isOk())
                                .andReturn();
                        log.info(result.getResponse().getContentAsString());
                        log.info(String.valueOf(result.getResponse().getStatus()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    latch.countDown();
                });
            }
            log.info(accessInfoRepository.findAll().toString());
        }
    }
}
