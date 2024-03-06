package apiVarejo.produtos.api.service.produtos;

import apiVarejo.produtos.api.dto.ProdutosRequest;
import apiVarejo.produtos.api.dto.ProdutosResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutosService {
    List<ProdutosResponse> buscarListaProdutos();
    Optional<ProdutosResponse> buscarProdutoId(UUID id, String sku);
    void criarProduto(ProdutosRequest produtosRequest);
    void atualizProduto(UUID id,ProdutosRequest produtosRequest);
    void deletarProduto(UUID id);

}
