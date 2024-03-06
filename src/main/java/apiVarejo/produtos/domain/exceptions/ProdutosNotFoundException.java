package apiVarejo.produtos.domain.exceptions;

public class ProdutosNotFoundException extends ModelNotFoundException{
    public ProdutosNotFoundException(){
        super("Produto não encontrado!");
    }
    public ProdutosNotFoundException(String message){
        super(message);
    }
}
