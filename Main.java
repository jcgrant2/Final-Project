import java.util.Scanner;
import java.util.HashMap;
import java.io.*; // for reading into a file and writing out to a file

class Main {
  public static void main(String[] args) {
  
    Scanner keyboard = new Scanner(System.in);
    HashMap <String, Double> menu = new HashMap<>();
    File menuList = new File("MenuList.txt");
    try{
      Scanner scanner = new Scanner(menuList); // placing object in instead of System.in
    
      while (scanner.hasNextLine()) {
         String foodName = scanner.nextLine();
         double price = Double.parseDouble(scanner.nextLine());
         menu.put(foodName, price);
      }
      // remember to do error checks, if its not on the menu. i.e. -1
       //Starting Menu
       HashMap <String, FoodMenu> order = new HashMap<>();
       double total = 0;
       System.out.println("Welcome to the Bill Helper");
       System.out.println("Are you finished with your order? Yes or No? ");
       String answer = keyboard.nextLine();
       
       //Continuously takes in orders from the customer until they want to stop
       while(answer.equalsIgnoreCase("No")){
         System.out.println("What is your name for the order and what item(s) would you like? ");
         String name = keyboard.nextLine();
         String food = keyboard.nextLine();
         System.out.println("How many of those items would you like?");
         int quantity = keyboard.nextInt();
         FoodMenu bill = new FoodMenu(quantity, food, menu.get(food)); 
         total += bill.computeSubtotal();
         System.out.println("Are you finished with your order? Yes or No?");
         keyboard.nextLine();
         answer = keyboard.nextLine();
       } 
   
       //Gives them the total for their combined order and gives the amount for tipping 10 percent
       System.out.println("Total cost: $" + total + " Tip 10%: "+ (total*.10));
       
       
      }catch(FileNotFoundException e){
      
         System.out.println("Menu doesn't exist");
      };
  }   
}     
