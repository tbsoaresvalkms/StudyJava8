package modeloPagamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class Payment {
    private Customer customer;
    private LocalDateTime date;
    private List<Product> products;

    public Payment(List<Product> products, LocalDateTime date, Customer customer) {
        this.customer = customer;
        this.date = date;
        this.products = Collections.unmodifiableList(products);
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "[Payment: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")) +
                " - Customer: " + customer +
                " - Products: " + products;
    }
}
