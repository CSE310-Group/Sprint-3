-- Create 'categories' table
CREATE TABLE categories (
    categoryID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Create 'recipes' table
CREATE TABLE recipes (
    recipeID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    servings INT,
    categoryID INT,
    FOREIGN KEY (categoryID) REFERENCES categories(categoryID)
);

-- Create 'measurements' table

-- Create 'ingredients' table
CREATE TABLE ingredients (
    ingredientID INT AUTO_INCREMENT PRIMARY KEY,
    recipeID INT,
    name VARCHAR(255) NOT NULL,
    quantity VARCHAR(100), -- Change to amount(number) and measurement(id, calls table, add measurementID foreign key)
    FOREIGN KEY (recipeID) REFERENCES recipes(recipeID)
);

-- Create 'steps' table
CREATE TABLE steps (
    stepID INT AUTO_INCREMENT PRIMARY KEY,
    recipeID INT,
    stepNumber INT,
    description TEXT,
    FOREIGN KEY (recipeID) REFERENCES recipes(recipeID)
);

-- Insert measurements
-- Cups, Teaspoons, Tablespoons

-- Breakfast Category (categoryID = 1)
INSERT INTO categories (name) VALUES ('Breakfast');

-- ? (recipeID = 1)
-- Recipe
INSERT INTO recipes (name, description, servings, categoryID) VALUES ('?', '?', 1, 1);
-- Ingredients
INSERT INTO ingredients (recipeID, name, quantity) VALUES (1, '?', '?'); -- Edit once table is edited
-- Steps
INSERT INTO steps (recipeID, stepNumber, description) VALUES (1, 1, '?');

-- Lunch Category (categoryID = 2)
INSERT INTO categories (name) VALUES ('Lunch');

-- Dinner Category (categoryID = 3)
INSERT INTO categories (name) VALUES ('Dinner');

-- Dessert Category (categoryID = 4)
INSERT INTO categories (name) VALUES ('Dessert');