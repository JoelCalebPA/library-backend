package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Suscription;

@Repository
public interface SuscriptionRepository extends JpaRepository<Suscription, Long> {

}
