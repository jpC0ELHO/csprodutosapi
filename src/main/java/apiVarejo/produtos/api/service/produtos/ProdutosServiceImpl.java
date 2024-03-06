package apiVarejo.produtos.api.service.produtos;

import apiVarejo.produtos.api.dto.ProdutosRequest;
import apiVarejo.produtos.api.dto.ProdutosResponse;
import apiVarejo.produtos.domain.exceptions.ProdutosNotFoundException;
import apiVarejo.produtos.domain.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static apiVarejo.produtos.api.dto.ProdutosRequest.toEntidade;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProdutosServiceImpl implements ProdutosService{
    private final ProdutosRepository produtoRepository;
    @Override
    public List<ProdutosResponse> buscarListaProdutos() {
        try{
            var buscarListaDeProdutos=produtoRepository.findAll();
            log.info("Lista de produtos encontrada!");
            return buscarListaDeProdutos
                    .stream().
                    map(ProdutosResponse::toResponse)
                    .toList();
        }catch (RuntimeException e){
            log.info("Error:{}",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<ProdutosResponse> buscarProdutoId(UUID id, String sku) {
        var buscarProdutos=produtoRepository.findById(id);
        var buscarProdutosSku=produtoRepository.findBySku(sku);
        if (buscarProdutos.isEmpty()||buscarProdutosSku.isEmpty()){
            log.warn("Produto não encontrado verifique o ID");
            throw new ProdutosNotFoundException("Produto não encontrado...");
        }
        return buscarProdutos.map(ProdutosResponse::toResponse);
    }

    @Override
    public void criarProduto(ProdutosRequest produtosRequest) {
        var buscarProdutosSku=produtoRepository.findBySku(produtosRequest.sku());
        if (buscarProdutosSku.isPresent()){
            log.warn("Produto com o sku:{} já existe!",produtosRequest.sku());
            throw new ProdutosNotFoundException("Produto com sku digitado já existe!");
        }
        produtoRepository.save(toEntidade(produtosRequest));
    }

    @Override
    public void atualizProduto(UUID id, ProdutosRequest produtosRequest) {
        var buscarProdutos=produtoRepository.findById(id);
        if (buscarProdutos.isEmpty()){
            log.warn("Produto com id{} não encontrado...",id);
            throw new ProdutosNotFoundException("Produto não encontrado...");
        }
        buscarProdutos.map(produtos -> {
            produtos.setSku(produtos.getSku());
            produtos.setNomeProduto(produtos.getNomeProduto());
            produtos.setQuantCompra(produtos.getQuantCompra());
            produtos.setQuantVenda(produtos.getQuantVenda());
            produtos.setValorCompra(produtos.getValorCompra());
            produtos.setValorVenda(produtos.getValorVenda());
            produtos.setDataCompra(produtos.getDataCompra());
            produtos.setDataVenda(produtos.getDataVenda());
            produtos.setLocalCompra(produtos.getLocalCompra());
            produtos.setLocalVenda(produtos.getLocalVenda());
            produtos.setNomeVendedorExterno(produtos.getNomeVendedorExterno());
            produtos.setNomeVendedorInterno(produtos.getNomeVendedorInterno());
            produtos.setNomeCliente(produtos.getNomeCliente());
            produtos.setGastoTransporte(produtos.getGastoTransporte());
            produtos.setImpostosProduto(produtos.getImpostosProduto());
            produtos.setGastoManutencao(produtos.getGastoManutencao());
            produtos.setTipoManutencao(produtos.getTipoManutencao());
            return produtoRepository.save(produtos);
        });
        log.info("Produto com id{} atualizado com sucesso...",id);
    }

    @Override
    public void deletarProduto(UUID id) {
        try {
            var buscarProdutos=produtoRepository
                    .findById(id)
                    .orElseThrow(()->new ProdutosNotFoundException("Produto não encontrado!"));
            produtoRepository.delete(buscarProdutos);
            log.info("Produto com id{} deletado com sucesso!",id);
        }catch (RuntimeException e){
            log.warn("Error{}",e.getMessage());
        }
    }
}
