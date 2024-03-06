package apiVarejo.produtos.domain.exceptions;

public class PeLNotFoundException extends ModelNotFoundException{
    public PeLNotFoundException(){
        super("Relatorio de ganhos e perdas não encontrado!");
    }
    public PeLNotFoundException(String message){
        super(message);
    }
}
