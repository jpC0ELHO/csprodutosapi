package apiVarejo.produtos.api.dto;

import apiVarejo.produtos.domain.entities.PeL;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PeLRequest(
        @NotBlank
        String sku,
        @NotBlank
        String nomeProduto,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.SCALAR,pattern = "dd/MM/yyyy")
        LocalDate dataCompra,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.SCALAR,pattern = "dd/MM/yyyy")
        LocalDate dataVenda,
        @NotNull
        BigDecimal totalVenda,
        @NotNull
        BigDecimal totalCompra,
        @NotNull
        BigDecimal lucroBruto,
        @NotNull
        BigDecimal gastosTotais,
        @NotNull
        BigDecimal gastosExternos,
        @NotBlank
        String tipoGastoExterno,
        @NotNull
        BigDecimal lucroLiquido,
        @NotBlank
        String tratativa
) {
    public static PeL toEntidade(PeLRequest peLRequest){
        if (peLRequest==null){
            return null;
        }
        return new PeL(
                peLRequest.sku,
                peLRequest.nomeProduto,
                peLRequest.dataCompra,
                peLRequest.dataVenda,
                peLRequest.totalVenda,
                peLRequest.totalCompra,
                peLRequest.lucroBruto,
                peLRequest.gastosTotais,
                peLRequest.gastosExternos,
                peLRequest.tipoGastoExterno,
                peLRequest.lucroLiquido,
                peLRequest.tratativa
        );
    }
}
