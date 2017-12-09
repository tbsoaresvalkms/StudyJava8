package interfacesFuncionais;

@FunctionalInterface
public interface Validator<T> {
    boolean valida(T t);
}
