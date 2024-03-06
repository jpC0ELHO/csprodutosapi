package apiVarejo.produtos.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "profit_and_loss")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@EntityListeners(AuditingEntityListener.class)
public class PeL extends Entidade{

    @Column(name = "sku",unique = true,nullable = false)
    private String sku;
    @Column(name = "nome_produto",nullable = false)
    private String nomeProduto;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dataCompra;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dataVenda;
    @Column(precision = 10,scale = 2)
    private BigDecimal totalVenda;
    @Column(precision = 10,scale = 2)
    private BigDecimal totalCompra;
    @Column(precision = 10,scale = 2)
    private BigDecimal lucroBruto;
    @Column(precision = 10,scale = 2)
    private BigDecimal gastosTotais;
    @Column(precision = 10,scale = 2)
    private BigDecimal gastosExternos;
    @Column(name = "tipo_gastos_externos")
    private String tipoGastoExterno;
    @Column(precision = 10,scale = 2)
    private BigDecimal lucroLiquido;
    @Column(precision = 10,scale = 2)
    private String tratativa;
}
