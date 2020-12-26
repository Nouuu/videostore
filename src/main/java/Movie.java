public class Movie {

    private final String title;
    private MovieType priceCode;

    public Movie(String title, MovieType priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public MovieType getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(MovieType code) {
        priceCode = code;
    }

    public String getTitle() {
        return title;
    }

}