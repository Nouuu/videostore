import java.util.Vector;

public class Customer {
    private final String name;
    private final Vector<Rental> rentals = new Vector<>();
    private final CustomerStatement customerStatement = new CustomerStatement(rentals);

    public Customer(String name) {
        this.name = name;
    }

    public Customer addRental(Rental rental) {
        rentals.addElement(rental);
        return this;
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return customerStatement.rentalStatement(getName());
    }
}