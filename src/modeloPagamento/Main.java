package modeloPagamento;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        List<Payment> payments = getPayments();

        payments.stream()
                .sorted(Comparator.comparing(Payment::getDate))
                .forEach(System.out::println);

        double totalPayment1 = payments.get(0)
                .getProducts()
                .stream()
                .mapToDouble(value -> value.getPrice().doubleValue())
                .sum();

        System.out.println("Total payment1: " + totalPayment1);

        double totalPayments = payments.stream()
                .flatMap(payment -> payment.getProducts().stream())
                .mapToDouble(product -> product.getPrice().doubleValue())
                .sum();

        System.out.println("Total payments: " + totalPayments);

        Map<Product, Long> countSold = payments.stream()
                .flatMap(payment -> payment.getProducts().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        countSold.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(System.out::println);

        payments.stream()
                .flatMap(p -> p.getProducts().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingDouble(p -> p.getPrice().doubleValue())))
                .forEach((product, value) -> System.out.println(product + " " + value));

        payments.stream()
                .flatMap(p -> p.getProducts().stream())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Product::getPrice,
                        BigDecimal::add
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(System.out::println);

        payments.stream()
                .collect(Collectors.groupingBy(Payment::getCustomer))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .flatMap(p -> p.getProducts().stream())
                                .collect(Collectors.toList())
                ))
                .forEach((customer, products) -> System.out.println(customer + " " + products));

        payments.stream()
                .collect(Collectors.groupingBy(Payment::getCustomer))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        p -> p.getValue().stream()
                                .flatMap(py -> py.getProducts().stream())
                                .mapToDouble(pr -> pr.getPrice().doubleValue())
                                .sum()
                ))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(System.out::println);


        payments.stream()
                .collect(Collectors.groupingBy(Main::getYearAndMonth))
                .forEach((date, paymentsGruped) -> System.out.println(date + " " + paymentsGruped));

        payments.stream()
                .collect(Collectors.groupingBy(o -> YearMonth.from(o.getDate())))
                .forEach((date, paymentsGruped) -> System.out.println(date + " " + paymentsGruped));

        payments.stream()
                .collect(Collectors.groupingBy(
                        o -> YearMonth.from(o.getDate()),
                        Collectors.summingDouble(
                                value -> value.getProducts().stream()
                                        .mapToDouble(pr -> pr.getPrice().doubleValue())
                                        .sum())
                ))
                .forEach((date, paymentsGruped) -> System.out.println(date + " " + paymentsGruped));

    }

    private static LocalDate getYearAndMonth(Payment payment) {
        return payment.getDate().toLocalDate().withDayOfMonth(1);
    }

    private static List<Payment> getPayments() {
        Customer paulo = new Customer("Paulo Silveira");
        Customer rodrigo = new Customer("Rodrigo Turini");
        Customer guilherme = new Customer("Guilherme Silveira");
        Customer adriano = new Customer("Adriano Almeida");
        Product bach = new Product("Bach Completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
        Product poderosas = new Product("Poderosas Anita", Paths.get("/music/poderosas.mp3"), new BigDecimal(90));
        Product bandeira = new Product("Bandeira Brasil", Paths.get("/images/brasil.jpg"), new BigDecimal(50));
        Product beauty = new Product("Beleza Americana", Paths.get("beauty.mov"), new BigDecimal(150));
        Product vingadores = new Product("Os Vingadores", Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
        Product amelie = new Product("Amelie Poulain", Paths.get("/movies/amelie.mov"), new BigDecimal(100));
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        LocalDateTime lastMonth = today.minusMonths(1);
        LocalDateTime lastYear = today.minusYears(1);
        Payment payment1 = new Payment(asList(bach, poderosas), today, paulo);
        Payment payment2 = new Payment(asList(bach, bandeira, amelie), yesterday, rodrigo);
        Payment payment3 = new Payment(asList(beauty, vingadores, bach), today, adriano);
        Payment payment4 = new Payment(asList(bach, poderosas, amelie), lastMonth, guilherme);
        Payment payment5 = new Payment(asList(beauty, amelie), yesterday, paulo);
        Payment payment6 = new Payment(asList(beauty, amelie), lastYear, paulo);
        return asList(payment1, payment2, payment3, payment4, payment5, payment6);
    }
}
