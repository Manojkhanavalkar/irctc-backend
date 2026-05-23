package com.substring.irctc.repository;

import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRouteRepository extends JpaRepository<TrainRoute,Long> {
    List<TrainRoute> findByTrain(Train train);
}
