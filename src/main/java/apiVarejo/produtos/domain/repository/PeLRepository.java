package apiVarejo.produtos.domain.repository;

import apiVarejo.produtos.domain.entities.PeL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PeLRepository extends JpaRepository<PeL, UUID>{
    Optional<PeL> findBySku(String id);
}
