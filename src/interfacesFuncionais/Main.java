package interfacesFuncionais;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Runnable t1 = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        };

        new Thread(t1).start();


        List<String> ceps = List.of("02635-150", "01232-000", "3212-102", "10321-111", "132123212");
        Validator<String> validatorCep = c -> c.matches("[0-9]{5}-[0-9]{3}");

        ceps.forEach(c -> System.out.println(validatorCep.valida(c)));



        /*
            O compilador descobre o tipo da função lambda pelo sua declaração
            O codigo abaixo, nao compila
            Object o = () -> System.out.println("Que lambda eu sou?");
         */

        Runnable o = () -> System.out.println("Que lambda eu sou?");

        System.out.println(o);
        System.out.println(o.getClass());

        /*
            Pode ser utilizado variaveis do mesmo contexto no lambda, porem
            precisam ser final. Nao tem necessidade de declarar como final,
            basta apenas nao alterar a variavel no decorrer do codigo
         */

        String regex = "[0-9]{5}-[0-9]{3}";
        Validator<String> validatorCepTwo = c -> c.matches(regex);

        /*
            Se, tentar mudar o valor da variavel regex,
            o codigo nao compila
         */

    }
}
