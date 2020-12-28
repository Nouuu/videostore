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

    public String rentalStatement(String customerName) {
        totalAmount = 0;
        frequentRenterPoints = 0;
        Enumeration<Rental> rentals = this.rentals.elements();
        result = new StringBuilder("Rental Record for " + customerName + "\n");

        while (rentals.hasMoreElements()) {
            // determines the amount for each line
            processRental(rentals.nextElement());
        }

        appendFrequentRenterPoints();

        return result.toString();
    }

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
                && rental.getDaysRented() > 1) {
            frequentRenterPoints++;
        }

        appendRentalDetails(rental.getMovie().getTitle(), thisAmount);
        totalAmount += thisAmount;
    }

    private void appendRentalDetails(String movieTitle, double amount) {
        result.append("\t");
        result.append(movieTitle);
        result.append("\t");
        result.append(amount);
        result.append("\n");
    }


    private void appendFrequentRenterPoints() {
        result.append("You owed ");
        result.append(totalAmount);
        result.append("\n");
        result.append("You earned ");
        result.append(frequentRenterPoints);
        result.append(" frequent renter points\n");
    }
}
