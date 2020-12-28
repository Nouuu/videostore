import java.util.Vector;

public class Customer {
    private final String name;
    private final Vector<Rental> rentals = new Vector<>();
    private final CustomerStatement customerStatement = new CustomerStatement(rentals);

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return customerStatement.rentalStatement(getName());
    }
}