package com.onion.repository;

import com.onion.entity.HistoryRoutes;
import com.onion.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRouteRepository extends JpaRepository<HistoryRoutes,Integer> {
    List<HistoryRoutes> findAllByVehicle(Vehicle vehicle);
}
