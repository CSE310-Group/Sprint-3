-- Create 'users' table
CREATE TABLE users (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
)

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
CREATE TABLE measurements (
    measurementID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    abbreviarion VARCHAR(100) NOT NULL
);

-- Create 'ingredients' table
CREATE TABLE ingredients (
    ingredientID INT AUTO_INCREMENT PRIMARY KEY,
    recipeID INT,
    name VARCHAR(255) NOT NULL,
    quantity DECIMAL NOT NULL,
    measurementID INT,
    FOREIGN KEY (recipeID) REFERENCES recipes(recipeID),
    FOREIGN KEY (measurementID) REFERENCES measurements(measurementID)
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
INSERT INTO measurements (name, abbreviarion) VALUES ('Cups', 'c');
INSERT INTO measurements (name, abbreviarion) VALUES ('Teaspoons', 'tsp');
INSERT INTO measurements (name, abbreviarion) VALUES ('Tablespoons', 'tbsp');

-- Breakfast Category (categoryID = 1)  
INSERT INTO categories (name) VALUES ('Breakfast');  

-- Pancakes (recipeID = 1)  
INSERT INTO recipes (name, description, servings, categoryID)  
VALUES ('Pancakes', 'Fluffy homemade pancakes perfect for breakfast.', 4, 1);  

-- Ingredients for Pancakes  
INSERT INTO ingredients (recipeID, name, quantity, measurementID) VALUES  
(1, 'Flour', 1.5, 1),  
(1, 'Milk', 1.0, 1),  
(1, 'Egg', 1.0, NULL),  
(1, 'Baking Powder', 1.0, 3),  
(1, 'Sugar', 2.0, 2),  
(1, 'Salt', 0.5, 2);  

-- Steps for Pancakes  
INSERT INTO steps (recipeID, stepNumber, description) VALUES  
(1, 1, 'In a bowl, mix flour, sugar, baking powder, and salt.'),  
(1, 2, 'Add milk and egg, then stir until combined.'),  
(1, 3, 'Heat a pan and pour batter to form pancakes.'),  
(1, 4, 'Cook until bubbles form, then flip and cook the other side.'),  
(1, 5, 'Serve warm with syrup or toppings.');  

-- Lunch Category (categoryID = 2)  
INSERT INTO categories (name) VALUES ('Lunch');  

-- Grilled Cheese Sandwich (recipeID = 2)  
INSERT INTO recipes (name, description, servings, categoryID)  
VALUES ('Grilled Cheese Sandwich', 'A classic crispy and cheesy sandwich.', 1, 2);  

-- Ingredients for Grilled Cheese Sandwich  
INSERT INTO ingredients (recipeID, name, quantity, measurementID) VALUES  
(2, 'Bread', 2.0, NULL),  
(2, 'Cheese', 1.0, NULL),  
(2, 'Butter', 1.0, 3);  

-- Steps for Grilled Cheese Sandwich  
INSERT INTO steps (recipeID, stepNumber, description) VALUES  
(2, 1, 'Butter one side of each bread slice.'),  
(2, 2, 'Place cheese between the slices with buttered sides facing out.'),  
(2, 3, 'Cook in a pan on medium heat until golden brown on both sides.'),  
(2, 4, 'Serve warm and enjoy.');  

-- Dinner Category (categoryID = 3)  
INSERT INTO categories (name) VALUES ('Dinner');  

-- Spaghetti Bolognese (recipeID = 3)  
INSERT INTO recipes (name, description, servings, categoryID)  
VALUES ('Spaghetti Bolognese', 'A delicious Italian pasta dish with meat sauce.', 4, 3);  

-- Ingredients for Spaghetti Bolognese  
INSERT INTO ingredients (recipeID, name, quantity, measurementID) VALUES  
(3, 'Spaghetti', 8.0, NULL),  
(3, 'Ground Beef', 1.0, NULL),  
(3, 'Tomato Sauce', 2.0, 1),  
(3, 'Onion', 1.0, NULL),  
(3, 'Garlic', 2.0, NULL),  
(3, 'Olive Oil', 1.0, 3),  
(3, 'Salt', 1.0, 2),  
(3, 'Pepper', 1.0, 2);  

-- Steps for Spaghetti Bolognese  
INSERT INTO steps (recipeID, stepNumber, description) VALUES  
(3, 1, 'Boil spaghetti according to package instructions.'),  
(3, 2, 'Heat olive oil in a pan and sauté onions and garlic.'),  
(3, 3, 'Add ground beef and cook until browned.'),  
(3, 4, 'Pour in tomato sauce and simmer for 10 minutes.'),  
(3, 5, 'Season with salt and pepper, then serve over spaghetti.');  

-- Dessert Category (categoryID = 4)  
INSERT INTO categories (name) VALUES ('Dessert');  

-- Chocolate Chip Cookies (recipeID = 4)  
INSERT INTO recipes (name, description, servings, categoryID)  
VALUES ('Chocolate Chip Cookies', 'Classic homemade cookies with chocolate chips.', 12, 4);  

-- Ingredients for Chocolate Chip Cookies  
INSERT INTO ingredients (recipeID, name, quantity, measurementID) VALUES  
(4, 'Flour', 2.5, 1),  
(4, 'Sugar', 1.0, 1),  
(4, 'Brown Sugar', 1.0, 1),  
(4, 'Butter', 1.0, 1),  
(4, 'Egg', 2.0, NULL),  
(4, 'Vanilla Extract', 1.0, 2),  
(4, 'Baking Soda', 1.0, 2),  
(4, 'Salt', 0.5, 2),  
(4, 'Chocolate Chips', 2.0, 1);  

-- Steps for Chocolate Chip Cookies  
INSERT INTO steps (recipeID, stepNumber, description) VALUES  
(4, 1, 'Preheat oven to 350°F (175°C).'),  
(4, 2, 'Mix butter, sugar, and brown sugar until smooth.'),  
(4, 3, 'Add eggs and vanilla extract, then mix well.'),  
(4, 4, 'Combine flour, baking soda, and salt in a separate bowl, then mix into the wet ingredients.'),  
(4, 5, 'Stir in chocolate chips.'),  
(4, 6, 'Drop spoonfuls of dough onto a baking sheet.'),  
(4, 7, 'Bake for 10-12 minutes or until golden brown.'),  
(4, 8, 'Let cool and enjoy.');  
