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

-- Create 'ingredients' table
CREATE TABLE ingredients (
    ingredientID INT AUTO_INCREMENT PRIMARY KEY,
    recipeID INT,
    name VARCHAR(255) NOT NULL,
    quantity VARCHAR(100),
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

-- Breakfast Category (categoryID = 1)
INSERT INTO categories (name) VALUES ('Breakfast');

-- ? (recipeID = 1)
-- Recipe
INSERT INTO recipes (name, description, servings, categoryID) VALUES ('?', '?', ?, 1);
-- Ingredients
INSERT INTO ingredients (recipeID, name, quantity) VALUES (1, '?', '?');
-- Steps
INSERT INTO steps (recipeID, stepNumber, description) VALUES (1, 1, '?');

-- Lunch Category (categoryID = 2)
INSERT INTO categories (name) VALUES ('Lunch');

-- ? (recipeID = 2)
-- Recipe
INSERT INTO recipes (name, description, servings, categoryID) VALUES ('?', '?', ?, 2)
-- Ingredients
INSERT INTO ingredients (recipeID, name, quantity) VALUES (2, '?', '?');
-- Steps
INSERT INTO steps (recipeID, stepNumber, description) VALUES (2, 1, '?');

-- Dinner Category (categoryID = 3)
INSERT INTO categories (name) VALUES ('Dinner');

-- ? (recipeID = 3)
-- Recipe
INSERT INTO recipes (name, description, servings, categoryID) VALUES ('?', '?', ?, 3);
-- Ingredients
INSERT INTO ingredients (recipeID, name, quantity) VALUES (3, '?', '?');
-- Steps
INSERT INTO steps (recipeID, stepNumber, description) VALUES (3, 1, '?');

-- Dessert Category (categoryID = 4)
INSERT INTO categories (name) VALUES ('Dessert');

-- ? (recipeID = 4)
-- Recipe
INSERT INTO recipes (name, description, servings, categoryID) VALUES ('?', '?', ?, 4);
-- Ingredients
INSERT INTO ingredients (recipeID, name, quantity) VALUES (4, '?', '?');
-- Steps
INSERT INTO steps (recipeID, stepNumber, description) VALUES (4, 1, '?');