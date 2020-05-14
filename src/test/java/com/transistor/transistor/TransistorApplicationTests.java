package com.transistor.transistor;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransistorApplicationTests {
    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate template;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void getAllVoters() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = template.exchange(getRootUrl() + "/voters", HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void getVoterById() {
        Voter voter = template.getForObject(getRootUrl() + "/voters/1", Voter.class);
        System.out.println(voter.getName());
        Assert.assertNotNull(voter);
    }

    @Test
    public void postCreateVoter() {
        Voter voter = new Voter();
        voter.setName("Zeeshan");
        voter.setState("Maharashtra");

        ResponseEntity<Voter> postresponse = template.postForEntity(getRootUrl() + "/voters", voter, Voter.class);
        Assert.assertNotNull(postresponse);
        Assert.assertNotNull(postresponse.getBody());
    }

    @Test
    public void putVoter() {
        int id = 1;
        Voter voter = template.getForObject(getRootUrl() + "/voters" + id, Voter.class);
        voter.setName("test");
        voter.setState("bih");

        template.put(getRootUrl() + "/voters" + id, Voter.class);
        Voter updatedVoter = template.getForObject(getRootUrl() + "/voters" + id, Voter.class);
        Assert.assertNotNull(updatedVoter);
    }

    @Test
    public void deleteVoter() {
        int id = 2;
        Voter voter = template.getForObject(getRootUrl() + "/voters" + id, Voter.class);
        Assert.assertNotNull(voter);

        template.delete(getRootUrl() + "/voters" + id, Voter.class);
        try {
            voter = template.getForObject(getRootUrl() + "/voters" + id, Voter.class);

        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }

    }

}
