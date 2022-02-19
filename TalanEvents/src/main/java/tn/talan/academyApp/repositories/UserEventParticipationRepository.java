package tn.talan.academyApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.talan.academyApp.entities.UserEventParticipation;

@Repository
public interface UserEventParticipationRepository extends JpaRepository<UserEventParticipation, Long> {

}
