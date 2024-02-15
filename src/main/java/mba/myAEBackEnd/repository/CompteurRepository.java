package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.Compteur;
import mba.myAEBackEnd.enums.ParamEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteurRepository extends JpaRepository<Compteur,Long> {

    Optional<Compteur> findByCode(ParamEnum code);
}
