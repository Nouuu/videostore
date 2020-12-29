

import junit.framework.*;

public class VideoStoreTest extends TestCase {
    private Customer customer;

    private final Rental newMovieRental1 = new Rental(new Movie("New1", MovieType.NEW_RELEASE), 3);
    private final Rental newMovieRental2 = new Rental(new Movie("New2", MovieType.NEW_RELEASE), 3);

    private final Rental newChildrenRental1 = new Rental(new Movie("Children1", MovieType.CHILDRENS), 3);

    private final Rental newRegularMovie1 = new Rental(new Movie("Regular1", MovieType.REGULAR), 1);
    private final Rental newRegularMovie2 = new Rental(new Movie("Regular2", MovieType.REGULAR), 2);
    private final Rental newRegularMovie3 = new Rental(new Movie("Regular3", MovieType.REGULAR), 3);

    private final String expectedStatement1 = "Rental Record for Fred\n" +
            "\tNew1" + "\t9.0\n" +
            "You owed 9.0\n" +
            "You earned 2 frequent renter points\n";

    private final String expectedStatement2 = "Rental Record for Fred\n" +
            "\tNew1" + "\t9.0\n" +
            "\tNew2" + "\t9.0\n" +
            "You owed 18.0\n" +
            "You earned 4 frequent renter points\n";

    private final String expectedStatement3 = "Rental Record for Fred\n" +
            "\tChildren1" + "\t1.5\n" +
            "You owed 1.5\n" +
            "You earned 1 frequent renter points\n";

    private final String expectedStatement4 = "Rental Record for Fred\n" +
            "\tRegular1" + "\t2.0\n" +
            "\tRegular2" + "\t2.0\n" +
            "\tRegular3" + "\t3.5\n" +
            "You owed 7.5\n" +
            "You earned 3 frequent renter points\n";

    public VideoStoreTest(String name) {
        super(name);
    }

    protected void setUp() {
        customer = new Customer("Fred");
    }

    public void testSingleNewReleaseStatement() {
        customer.addRental(newMovieRental1);

        assertEquals(expectedStatement1, customer.statement());
    }

    public void testDualNewReleaseStatement() {
        customer.addRental(newMovieRental1);
        customer.addRental(newMovieRental2);

        assertEquals(expectedStatement2, customer.statement());
    }

    public void testSingleChildrensStatement() {
        customer.addRental(newChildrenRental1);

        assertEquals(expectedStatement3, customer.statement());
    }

    public void testMultipleRegularStatement() {
        customer.addRental(newRegularMovie1);
        customer.addRental(newRegularMovie2);
        customer.addRental(newRegularMovie3);

        assertEquals(expectedStatement4, customer.statement());
    }
}