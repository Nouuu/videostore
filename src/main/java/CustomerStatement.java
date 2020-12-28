import java.util.Enumeration;
import java.util.Vector;

public class CustomerStatement {
    private final Vector<Rental> rentals;
    private double totalAmount;
    int frequentRenterPoints;
    StringBuilder result;

    public CustomerStatement(Vector<Rental> rentals) {
        this.rentals = rentals;
    }

    public String statement(String customerName) {
        totalAmount = 0;
        frequentRenterPoints = 0;
        Enumeration<Rental> rentals = this.rentals.elements();
        result = new StringBuilder("Rental Record for " + customerName + "\n");

        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = rentals.nextElement();

            // determines the amount for each line
            switch (each.getMovie().getPriceCode()) {
                case REGULAR:
                    thisAmount += 2;
    private void processRental(Rental rental) {
        double thisAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }

        frequentRenterPoints++;

        if (rental.getMovie().getPriceCode().equals(MovieType.NEW_RELEASE)
                && rental.getDaysRented() > 1)
            frequentRenterPoints++;

        result.append("\t");
        result.append(rental.getMovie().getTitle());
        result.append("\t");
        result.append(thisAmount);
        result.append("\n");
        totalAmount += thisAmount;
    }

        result.append("You owed ");
        result.append(totalAmount);
        result.append("\n");
        result.append("You earned ");
        result.append(frequentRenterPoints);
        result.append(" frequent renter points\n");


        return result.toString();
    }
}
