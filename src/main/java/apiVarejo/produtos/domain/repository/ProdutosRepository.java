package apiVarejo.produtos.domain.repository;

import apiVarejo.produtos.domain.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutosRepository extends JpaRepository<Produtos, UUID> {

    Optional<Produtos>findBySku(String id);
}
