package apiVarejo.produtos.domain.entities.enums;

import lombok.Getter;

@Getter
public enum TelefoneType {
    PESSOAL("PESSOAL"),
    COMERCIAL("COMERCIAL"),
    RESIDENCIAL("RESIDENCIAL"),
    OUTROS("OUTROS");

    private final String name;

    TelefoneType(String name){
        this.name=name;
    }
}
