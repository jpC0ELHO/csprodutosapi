package apiVarejo.produtos.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_produtos")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produtos extends Entidade{


    @Column(name = "SKU",unique = true)
    private String sku;
    @Column(name = "nome_produto",nullable = false,unique = true)
    private String nomeProduto;
    @Column(name="quantidade_comprada",length = 200)
    private Integer quantCompra;
    @Column(name = "quantidade_vendida")
    private Integer quantVenda;
    @Column(name = "valor_compra")
    private Integer valorCompra;
    @Column(name = "valor_venda")
    private Integer valorVenda;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate dataVenda;
    @Column(name = "local_de_compra")
    private String localCompra;
    @Column(name = "local_de_venda")
    private String localVenda;
    @Column(name = "nome_vendedor_externo")
    private String nomeVendedorExterno;
    @Column(name = "nome_vendedor_interno")
    private String nomeVendedorInterno;
    @Column(name = "nome_cliente")
    private String nomeCliente;
    @Column(name = "gastos_transporte",scale = 2,precision = 10)
    private BigDecimal gastoTransporte;
    @Column(name = "impostos_produto",scale = 2,precision = 10)
    private BigDecimal impostosProduto;
    @Column(name = "gasto_manutencao",scale = 2,precision = 10)
    private BigDecimal gastoManutencao;
    @Column(name = "tipo_manutencao")
    private String tipoManutencao;


}
