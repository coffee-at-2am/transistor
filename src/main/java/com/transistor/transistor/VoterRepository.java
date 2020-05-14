package com.transistor.transistor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    //public default void updatePartially(Voter voterDetails, Long voterId) {

    }
