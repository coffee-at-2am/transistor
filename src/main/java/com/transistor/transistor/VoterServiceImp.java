/*
package com.transistor.transistor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoterServiceImp {
    private static List<Voter> voters;

    static {
        voters = dummyVoters();
    }

    private static List<Voter> dummyVoters() {
        List<Voter> voters = new ArrayList<Voter>();
        voters.add(new Voter(10, "zee", "mah"));
        voters.add(new Voter(20, "zee", "mah"));
        voters.add(new Voter(30, "zee", "mah"));
        voters.add(new Voter(40, "zee", "mah"));
        voters.add(new Voter(50, "zee", "mah"));
        return voters;

    }

    public void updatePartially(Voter voter, Long voterId) {
        for (Voter voter1 : voters) {
            if (voter.getId() == voterId) {
                if (voter.getState() != null) {
                    voter.setId(voterId);
                    voter.setState(voter.getState());
                }
                voter.setName(voter.getName());
                update(voter);
            }
        }
    }

    private void update(Voter voter) {
        int index = voters.indexOf(voter);
        voters.set(index, voter);
    }
}
*/
