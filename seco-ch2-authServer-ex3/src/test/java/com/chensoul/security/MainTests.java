package com.chensoul.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
        public void testAccessTokenIsObtainedUsingValidClientCredentials() throws Exception {
        mvc.perform(
                        post("/oauth/token")
                                .with(httpBasic("client", "secret"))
                                .queryParam("grant_type", "client_credentials")
                                .queryParam("scope", "info")
                )
                .andExpect(jsonPath("$.access_token").exists())
                .andExpect(status().isOk());
    }
}
