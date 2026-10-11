class Manga {

    private String title;
    private double price;

    public Manga(String title, double price) {
        this.price = price;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return title + "(" + price + "€" + ")";
    }
}