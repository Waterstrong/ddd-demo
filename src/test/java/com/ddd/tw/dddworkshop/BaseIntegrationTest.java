package com.ddd.tw.dddworkshop;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.lang.String.format;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {
    private static final String PAYLOAD_RESOURCE_FORMART = "payload/%s/%s";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    }

    protected String loadPayload(String service, String fileName) throws IOException {
        String filePath = format(PAYLOAD_RESOURCE_FORMART, service, fileName);
        InputStream resource = getSystemClassLoader().getResourceAsStream(filePath);
        return IOUtils.toString(resource, defaultCharset());
    }

    @Test
    public void contextLoads() {
    }

}
