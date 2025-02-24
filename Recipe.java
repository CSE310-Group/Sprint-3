class Recipe {
    private String name;
    private String description;
    private String[] ingredients;
    private String[] instructions;

    // Constructor
    public Recipe(String name, String description, String ingredients, String instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients.split(","); // Split by comma
        this.instructions = instructions.split(",");
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String[] getIngredients() { return ingredients; }
    public String[] getInstructions() { return instructions; }

    // Display Recipe (For Testing)
    public void printRecipe() {
        System.out.println("Recipe Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Ingredients:");
        for (String ingredient : ingredients) {
            System.out.println(" - " + ingredient.trim());
        }
        System.out.println("Instructions:");
        for (String step : instructions) {
            System.out.println(" - " + step.trim());
        }
    }
}
