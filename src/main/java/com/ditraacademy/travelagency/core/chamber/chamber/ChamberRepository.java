package com.ditraacademy.travelagency.core.chamber.chamber;


import com.ditraacademy.travelagency.core.chamber.categorieChamber.CategorieChamber;
import com.ditraacademy.travelagency.core.chamber.typeChamber.TypeChamber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChamberRepository extends JpaRepository<Chamber, Integer> {
    Optional<Chamber> findByCategorieChamberAndTypeChamber(CategorieChamber categorieChamber , TypeChamber typeChamber );
}
