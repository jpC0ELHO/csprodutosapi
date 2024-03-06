package apiVarejo.produtos.api.dto;

import apiVarejo.produtos.domain.entities.Produtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "sku", "nomeProduto", "quantCompra",
        "quantVendida", "valorCompra","valorVenda"
        ,"dataCompra","dataVenda","localCompra","localVenda"
        ,"nomeVendedorExterno","nomeVendedorInterno","nomeCliente",
        "gastosTransporte","impostosProduto","gastosManutencao","tipoManutencao"
        , "createdBy", "lastModifiedBy","createdAt", "updatedAt"})
public record ProdutosResponse(
        UUID id,
        String sku,
        String nomeProduto,
        Integer quantCompra,
        Integer quantVendida,
        Integer valorCompra,
        Integer valorVenda,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyyy")
        LocalDate dataCompra,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        LocalDate dataVenda,
        String localCompra,
        String localVenda,
        String nomeVendedorExterno,
        String nomeVendedorInterno,
        String nomeCliente,
        BigDecimal gastosTransporte,
        BigDecimal impostosProduto,
        BigDecimal gastoManutencao,
        String tipoManutencao,
        String createdBy,
        String lastModifiedBy,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = JsonSerializer.class)
        @JsonDeserialize(using = JsonDeserializer.class)
        LocalDateTime createdAt,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = JsonSerializer.class)
        @JsonDeserialize(using = JsonDeserializer.class)
        LocalDateTime updateAt


) {
    public static ProdutosResponse toResponse(Produtos produtos){
        if (produtos==null){
            return null;
        }
        return new ProdutosResponse(
                produtos.getId(),
                produtos.getSku(),
                produtos.getNomeProduto(),
                produtos.getQuantCompra(),
                produtos.getQuantVenda(),
                produtos.getValorCompra(),
                produtos.getValorVenda(),
                produtos.getDataCompra(),
                produtos.getDataVenda(),
                produtos.getLocalCompra(),
                produtos.getLocalVenda(),
                produtos.getNomeVendedorExterno(),
                produtos.getNomeVendedorInterno(),
                produtos.getNomeCliente(),
                produtos.getGastoTransporte(),
                produtos.getImpostosProduto(),
                produtos.getGastoManutencao(),
                produtos.getTipoManutencao(),
                produtos.getCreatedBy(),
                produtos.getLastModifiedBy(),
                produtos.getCreatedAt(),
                produtos.getUpdateAt()
        );
    }
}

