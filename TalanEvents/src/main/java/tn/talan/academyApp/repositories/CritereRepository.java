package tn.talan.academyApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.talan.academyApp.entities.Critere;

@Repository
public interface CritereRepository extends JpaRepository<Critere, Long> {

}
