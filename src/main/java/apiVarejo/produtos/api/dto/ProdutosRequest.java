package apiVarejo.produtos.api.dto;

import apiVarejo.produtos.domain.entities.Produtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProdutosRequest(
        @NotBlank
        String sku,
        @NotBlank
        String nomeProduto,
        @NotNull
        Integer quantCompra,
        @NotNull
        Integer quantVendida,
        @NotNull
        Integer valorCompra,
        @NotNull
        Integer valorVenda,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.SCALAR,pattern = "dd/MM/yyyy")
        LocalDate dataCompra,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.SCALAR,pattern = "dd/MM/yyyy")
        LocalDate dataVenda,
        @NotBlank
        String localCompra,
        @NotBlank
        String localVenda,
        @NotBlank
        String nomeVendedorExterno,
        @NotBlank
        String nomeVendedorInterno,
        @NotBlank
        String nomeCliente,
        @NotNull
        BigDecimal gastosTransporte,
        @NotNull
        BigDecimal impostosProduto,
        @NotNull
        BigDecimal gastoManutencao,
        @NotBlank
        String tipoManutencao

) {
    public static Produtos toEntidade(ProdutosRequest produtosRequest){
        if (produtosRequest==null){
            return null;
        }
        return new Produtos(
                produtosRequest.sku,
                produtosRequest.nomeProduto,
                produtosRequest.quantCompra,
                produtosRequest.quantVendida,
                produtosRequest.valorCompra,
                produtosRequest.valorVenda,
                produtosRequest.dataCompra,
                produtosRequest.dataVenda,
                produtosRequest.localCompra,
                produtosRequest.localVenda,
                produtosRequest.nomeVendedorExterno,
                produtosRequest.nomeVendedorInterno,
                produtosRequest.nomeCliente,
                produtosRequest.gastosTransporte,
                produtosRequest.impostosProduto,
                produtosRequest.gastoManutencao,
                produtosRequest.tipoManutencao
        );
    }
}