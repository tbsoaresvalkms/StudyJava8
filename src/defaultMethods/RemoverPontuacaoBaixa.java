package defaultMethods;

import lambda.Usuario;

import java.util.function.Predicate;

public class RemoverPontuacaoBaixa implements Predicate<Usuario> {
    private Integer pontuacaoMinima;

    RemoverPontuacaoBaixa(Integer pontuacaoMinima) {

        this.pontuacaoMinima = pontuacaoMinima;
    }

    @Override
    public boolean test(Usuario usuario) {
        return usuario.getPontos() <= pontuacaoMinima;
    }
}
