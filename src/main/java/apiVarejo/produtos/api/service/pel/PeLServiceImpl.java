package apiVarejo.produtos.api.service.pel;

import apiVarejo.produtos.api.dto.PeLRequest;
import apiVarejo.produtos.api.dto.PeLResponse;
import apiVarejo.produtos.api.dto.ProdutosRequest;
import apiVarejo.produtos.domain.exceptions.PeLNotFoundException;
import apiVarejo.produtos.domain.repository.PeLRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static apiVarejo.produtos.api.dto.PeLRequest.toEntidade;

@Log4j2
@Service
@RequiredArgsConstructor
public class PeLServiceImpl implements PeLService{
    private final PeLRepository peLRepository;
    private ProdutosRequest produtosRequest;
    @Override
    public List<PeLResponse> buscarListaPeL() {
        try{
            var buscarListaFinancas=peLRepository.findAll();
            buscarListaFinancas.stream().map(PeLResponse::toResponse).toList();

        }catch (RuntimeException e){
            log.warn("Error{}",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<PeLResponse> buscarPeLId(UUID id) {
        var buscarFinanceiro=peLRepository.findById(id);
        if(buscarFinanceiro.isEmpty()){
            log.info("Dados de financeiro não encontrados...");
            throw new PeLNotFoundException("Error:");
        }
        return buscarFinanceiro.map(PeLResponse::toResponse);
    }

    @Override
    public void criarPeL(PeLRequest peLRequest) {
        var buscarFinanceiro=peLRepository.findBySku(peLRequest.sku());
        if (buscarFinanceiro.isPresent()){
            log.info("Os dados financeiros ja existem no banco!");
            throw new PeLNotFoundException("Os dados digitados com o id{} ja existem");
        }try {
            buscarFinanceiro.map(financeiro->{
                financeiro.setSku(produtosRequest.sku());
                financeiro.setNomeProduto(produtosRequest.nomeProduto());
                financeiro.setDataCompra(produtosRequest.dataCompra());
                financeiro.setDataVenda(produtosRequest.dataVenda());
                financeiro.setTotalVenda(new BigDecimal(produtosRequest.quantVendida()*produtosRequest.valorVenda()));
                financeiro.setTotalCompra(new BigDecimal(produtosRequest.quantCompra()*produtosRequest.valorCompra()));
                financeiro.setLucroBruto(financeiro.getTotalVenda());
                financeiro.getGastosExternos()
                        .add(produtosRequest.impostosProduto())
                        .add(produtosRequest.gastoManutencao())
                        .add(produtosRequest.gastosTransporte());
                financeiro.setGastosTotais(financeiro.getTotalCompra().add(financeiro.getGastosExternos()));
                financeiro.setTipoGastoExterno(financeiro.getTipoGastoExterno());
                financeiro.setLucroLiquido(financeiro.getLucroBruto().subtract(financeiro.getGastosTotais()));
                financeiro.setTratativa(financeiro.getTratativa());
                return peLRepository.save(financeiro);
            });

        }catch (RuntimeException e){
            log.warn("Error:{}",e.getMessage());
        }finally {
            peLRepository.save(toEntidade(peLRequest));
        }
    }

    @Override
    public void atualizarPeL(UUID id, PeLRequest peLRequest) {
        var buscarFinanceiro=peLRepository.findById(id);
        if (buscarFinanceiro.isEmpty()){
            log.warn("Dados financeiros com id{} não encontrados...",id);
            throw new PeLNotFoundException("Dados naõ encontrados...");
        }
        buscarFinanceiro.map(financeiro->{
            financeiro.setId(financeiro.getId());
            financeiro.setTotalVenda(financeiro.getTotalVenda());
            financeiro.setTotalCompra(financeiro.getTotalCompra());
            financeiro.setLucroBruto(financeiro.getLucroBruto());
            financeiro.setGastosTotais(financeiro.getGastosTotais());
            financeiro.setLucroLiquido(financeiro.getLucroLiquido());
            financeiro.setLucroLiquido(financeiro.getLucroLiquido());
            financeiro.setTratativa(financeiro.getTratativa());
            return peLRepository.save(financeiro);
        });
        log.info("Dados financeiros atualizados com sucesso!");
    }

    @Override
    public void deletarPeL(UUID id) {
        try{
            var buscarFinanceiro=peLRepository.findById(id)
                    .orElseThrow(()-> new PeLNotFoundException("Dados não encontrados..."));
            peLRepository.delete(buscarFinanceiro);
            log.info("Dados com id{} deletados com sucesso!",id);

        }catch (RuntimeException e){
            log.warn("Error{}",e.getMessage());
        }
    }
}
