package apiVarejo.produtos.api.controller;

import apiVarejo.produtos.api.dto.PeLRequest;
import apiVarejo.produtos.api.dto.PeLResponse;
import apiVarejo.produtos.api.service.pel.PeLService;
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
@RequestMapping(value = "/api/pel/v1")
public class PeLController {

    private final PeLService peLService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PeLResponse>> buscarListaPeL(){
        return ResponseEntity.status(HttpStatus.OK).body(peLService.buscarListaPeL());
    }
    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PeLResponse>>buscarPeLId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(peLService.buscarPeLId(id));
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void criarPeL(@RequestBody @Valid PeLRequest peLRequest){
       peLService.criarPeL(peLRequest);
    }
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarPeL(@PathVariable UUID id,@RequestBody PeLRequest peLRequest){
       peLService.atualizarPeL(id,peLRequest);
    }
    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPeL(@PathVariable UUID id){
        peLService.deletarPeL(id);
    }

}
