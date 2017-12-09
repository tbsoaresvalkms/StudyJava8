package lambda;

import java.util.function.Consumer;

public class MostradorConsumer implements Consumer<Usuario> {
    @Override
    public void accept(Usuario usuario) {
        System.out.println(usuario.getNome());
    }
}
