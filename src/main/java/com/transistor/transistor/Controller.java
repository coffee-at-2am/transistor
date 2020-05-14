package com.transistor.transistor;

import com.netflix.discovery.DiscoveryClient;
import com.transistor.transistor.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/")
public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    @Autowired
    private VoterRepository voterRepository;
    private DiscoveryClient discoveryClient;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/")
    public String index() {

        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        for (Integer ele:list){
            System.out.println(ele);
        }

        return "Hello Universe";
    }


    @RequestMapping(value = "/{name}/{age}", method = GET)
    @ResponseBody
    public String empInfo(@PathVariable("name") String name, @PathVariable("age") String age) {
        String msg = String.format("%s is %s years old", name, age);
        return msg;
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloThere() {
        return "Hello There!";
    }

    @PostMapping("/voters")
    public Voter createVoter(@Valid @RequestBody Voter voter) {
        return voterRepository.save(voter);
    }

    @GetMapping(path = "/voters", produces = "application/json")
    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    @PutMapping(path = "/voters/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable(value = "id") long voterId,
                                             @Valid @RequestBody Voter voterDetails) throws ResourceNotFoundException {
        Voter voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new ResourceNotFoundException("voter not found on :: " + voterId));
        voter.setName(voterDetails.getName());
        voter.setState(voterDetails.getState());
        voter.setId(voterDetails.getId());
        final Voter updatedVoter = voterRepository.save(voter);
        return ResponseEntity.ok(updatedVoter);

    }

  /*  @PatchMapping(path = "/voters/{id}")
    public ResponseEntity<Voter> patchVoter(@PathVariable("id") Long voterId,
                                           @Valid @RequestBody Voter voterDetails) throws ResourceNotFoundException{
        Voter voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new ResourceNotFoundException("voter not found on :: " + voterId));
        voterRepository.updatePartially(voterDetails, voterId);
        return new ResponseEntity<>(voter, HttpStatus.OK);

    }*/


    @DeleteMapping(path = "/voters/{id}")
    public Map<String, Boolean> deleteVoter(@PathVariable(value = "id") Long voterId) throws Exception {
        Voter voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new ResourceNotFoundException("Voter not found on :: " + voterId));

        voterRepository.delete(voter);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
