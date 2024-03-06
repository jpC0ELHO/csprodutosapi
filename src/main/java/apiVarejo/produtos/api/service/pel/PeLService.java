package apiVarejo.produtos.api.service.pel;


import apiVarejo.produtos.api.dto.PeLRequest;
import apiVarejo.produtos.api.dto.PeLResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PeLService {

    List<PeLResponse> buscarListaPeL();
    Optional<PeLResponse>buscarPeLId(UUID id);
    void criarPeL(PeLRequest peLRequest);
    void atualizarPeL(UUID id,PeLRequest peLRequest);
    void deletarPeL(UUID id);
}
