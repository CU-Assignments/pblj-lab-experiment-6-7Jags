To develop a Java program that processes a large dataset of products using Streams class to:
  - Group products by category
  - Find the most expensive product in each category
  - Calculate the average price of all products


Instruction
Step 1: Create the Product Class
- Define a Product class with attributes:
    name (String)
    category (String)
    price (double)
  
or (Reads product data from a CSV file)
For Example: "Laptop", "Electronics", 1200
             "Phone", "Electronics", 800


Step 2: Create the ProductProcessor Class
- Create a list of products with multiple categories and prices.
- Use Streams API to:
    Group products by category using Collectors.groupingBy().
    Find the most expensive product in each category using Collectors.maxBy().
    Calculate the average price of all products using Collectors.averagingDouble().
- Display the results.


  import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class ProductProcessor {

    // Product Class
    public static class Product {
        private String name;
        private String category;
        private double price;

        // Constructor to initialize values
        public Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        // Getter methods
        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }

        // Display method for printing product details
        @Override
        public String toString() {
            return name + " (" + category + ", " + price + ")";
        }
    }

    public static void main(String[] args) {
        // Sample dataset of products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "Electronics", 1200));
        products.add(new Product("Phone", "Electronics", 800));
        products.add(new Product("Shirt", "Clothing", 50));
        products.add(new Product("Trousers", "Clothing", 60));
        products.add(new Product("Sneakers", "Footwear", 100));
        products.add(new Product("Boots", "Footwear", 150));

        // Case 1: Group products by category
        System.out.println("Products Grouped by Category:");
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        // Case 2: Find the most expensive product in each category
        System.out.println("\nMost Expensive Product in Each Category:");
        Map<String, Optional<Product>> mostExpensiveProducts = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))));
        mostExpensiveProducts.forEach((category, productOpt) -> {
            System.out.println(category + ": " + productOpt.orElse(null));
        });

        // Case 3: Calculate the average price of all products
        System.out.println("\nAverage Price of All Products:");
        double averagePrice = products.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));
        System.out.println("Average Price: " + averagePrice);
    }
}


  
    Test Cases
    Test Case	                                     Input Data	                                                                           Expected Output
    Case 1: Normal Case             	     Sample dataset with Electronics, Clothing, and Footwear	                      Grouped products, Most Expensive per category, Average price
    Case 2: Single Category Only           All products in "Electronics"	                                                One category, Most Expensive in Electronics, Average of all
    Case 3: Same Price in a Category	     Two products with the same highest price in "Footwear"	                        Any of the most expensive ones is displayed
    Case 4: Only One Product	             One product "Laptop" in "Electronics"	                                        Laptop as the most expensive, Laptop as the only average
    Case 5: Empty List	                   No products	                                                                  No grouping, No most expensive product, Average price = 0
