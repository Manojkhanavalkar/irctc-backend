package com.substring.irctc.repository;

import com.substring.irctc.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station,Long> {
}
