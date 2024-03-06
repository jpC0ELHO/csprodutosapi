package apiVarejo.produtos.api.dto;

import apiVarejo.produtos.domain.entities.PeL;
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
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "sku", "nomProduto", "dataCompra"
        , "dataVenda", "totalVenda", "totalCompra","lucroBruto"
        ,"gastosTotais","gastosExternos","tipoGastoExterno"
        ,"lucroLiquido","tratativa","createdBy", "lastModifiedBy"
        , "createdAt", "updatedAt",})
public record PeLResponse(
        UUID id,
        String sku,
        String nomeProduto,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.SCALAR,pattern = "dd/MM/yyyy")
        LocalDate dataCompra,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.SCALAR,pattern = "dd/MM/yyyy")
        LocalDate dataVenda,
        BigDecimal totalVenda,
        BigDecimal totalCompra,
        BigDecimal lucroBruto,
        BigDecimal gastosTotais,
        BigDecimal gastosExternos,
        String tipoGastoExterno,
        BigDecimal lucroLiquido,
        String tratativa,
        String createdBy,
        String lastModifiedBy,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.SCALAR,pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = JsonSerializer.class)
        @JsonDeserialize(using = JsonDeserializer.class)
        LocalDateTime createdAt,
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.SCALAR,pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonSerialize(using = JsonSerializer.class)
        @JsonDeserialize(using = JsonDeserializer.class)
        LocalDateTime updateAt
) {
    public static PeLResponse toResponse(PeL peL){
        if (peL==null){
            return null;
        }
        return new PeLResponse(
                peL.getId(),
                peL.getSku(),
                peL.getNomeProduto(),
                peL.getDataCompra(),
                peL.getDataVenda(),
                peL.getTotalVenda(),
                peL.getTotalCompra(),
                peL.getLucroBruto(),
                peL.getGastosTotais(),
                peL.getGastosExternos(),
                peL.getTipoGastoExterno(),
                peL.getLucroLiquido(),
                peL.getTratativa(),
                peL.getCreatedBy(),
                peL.getLastModifiedBy(),
                peL.getCreatedAt(),
                peL.getUpdateAt()

        );
    }
}
