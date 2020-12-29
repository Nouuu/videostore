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
            processRental(rentals.nextElement());
        }

        appendFrequentRenterPoints();

        return result.toString();
    }

    private void processRental(Rental rental) {
        // determines the amount for each line
        double thisAmount = getRentalAmount(rental);

        addFrequentRenterPoints();

        if (rental.getMovie().getPriceCode().equals(MovieType.NEW_RELEASE)
                && rental.getDaysRented() > 1) {
            addFrequentRenterPoints();
        }

        appendRentalDetails(rental.getMovie().getTitle(), thisAmount);
        totalAmount += thisAmount;
    }

    private double getRentalAmount(Rental rental) {
        switch (rental.getMovie().getPriceCode()) {
            case REGULAR:
                return getRegularRentalAmount(rental.getDaysRented());
            case NEW_RELEASE:
                return getNewReleaseRentalAmount(rental.getDaysRented());
            case CHILDRENS:
                return getChildrenRentalAmount(rental.getDaysRented());
        }
        return -1;
    }

    private double getRegularRentalAmount(int daysRented) {
        return 2 + (daysRented > 2 ? (daysRented - 2) * 1.5 : 0);
    }

    private double getNewReleaseRentalAmount(int daysRented) {
        return daysRented * 3;
    }

    private double getChildrenRentalAmount(int daysRented) {
        return 1.5 + (daysRented > 3 ? (daysRented - 3) * 1.5 : 0);
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

    private void addFrequentRenterPoints() {
        frequentRenterPoints++;
    }
}
