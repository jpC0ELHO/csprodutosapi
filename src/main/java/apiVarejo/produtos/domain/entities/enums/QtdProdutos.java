package apiVarejo.produtos.domain.entities.enums;

import lombok.Getter;

@Getter
public enum QtdProdutos {
    ATACADO("ATACADO"),
    VAREJO("VAREJO"),
    OUTROS("OUTROS");

    private final String name;

    QtdProdutos(String name){
        this.name=name;
    }
}
