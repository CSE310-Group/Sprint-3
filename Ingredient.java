class Ingredient {
    private String name;
    private String measurement;
    private int quantity;

    public Ingredient(String name, String measurement, int quantity) {
        this.name = name;
        this.measurement = measurement;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public String getMeasurement() { return measurement; }
    
    @Override
    public String toString() {
        return quantity + " " + measurement + " " + name;
    }
}
