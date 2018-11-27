import java.util.Scanner;
import java.util.HashMap;
import java.io.*; // for reading into a file and writing out to a file

class Main {
  public static void main(String[] args) {
  
    Scanner keyboard = new Scanner(System.in);
    HashMap <String, Double> menu = new HashMap<>();
    File menu = new File("MenuList.txt");
    Scanner scanner = new Scanner(menu); // placing object in instead of System.in
    
    while (scanner.hasNextLine()) {
      String foodName = scanner.nextLine();
      scanner.nextLine();
      double price = scanner.nextDouble();
      menu.put(foodName, price);
    }
    // remember to do error checks, if its not on the menu. i.e. -1
    //Starting Menu
    HashMap <String, FoodMenu> order = new HashMap<>();
    System.out.println("Hi welcome to Olive Garden");
    System.out.println("Enter \"quit\" to end order)");
    double total = 0;
    System.out.println("Are you finished with your order? Yes or No? ");
    String order = scanner.nextLine();
    
    //Continuously takes in orders from the customer until they want to stop
    while(!order.equals("No")){
      System.out.println("What is your name for the order and what item(s) would you like? ");
      String name = keyboard.nextLine();
      String food = keyboard.nextLine();
      System.out.println("How many of those items would you like?");
      int quantity = keyboard.nextInt();
      FoodMenu bill = new FoodMenu(quantity, food, menu.get(foodName)); 
      total += menu.computeSubtotal();
      System.out.println("Are you finished with your order");
      order = keyboard.nextLine();
    } 

    //Gives them the total for their combined order and gives the amount for tipping 10 percent
    System.out.println("Total cost: $" + total + " Tip 10%: "+ (total*.10));
  }
}
