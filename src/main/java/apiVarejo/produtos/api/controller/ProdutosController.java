package apiVarejo.produtos.api.controller;

import apiVarejo.produtos.api.dto.ProdutosRequest;
import apiVarejo.produtos.api.dto.ProdutosResponse;
import apiVarejo.produtos.api.service.produtos.ProdutosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/produtos/v1")
public class ProdutosController {
    private final ProdutosService produtosService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdutosResponse>> buscarListasDeProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtosService.buscarListaProdutos());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<ProdutosResponse>>buscarProduto(@PathVariable UUID id, @PathVariable String sku){
        return ResponseEntity.status(HttpStatus.OK).body(produtosService.buscarProdutoId(id,sku));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void criarProduto(@RequestBody@Valid ProdutosRequest produtosRequest){
        produtosService.criarProduto(produtosRequest);
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarProduto(@PathVariable UUID id,@RequestBody ProdutosRequest produtosRequest){
        produtosService.atualizProduto(id,produtosRequest);
    }
    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable UUID id){
        produtosService.deletarProduto(id);
    }
}