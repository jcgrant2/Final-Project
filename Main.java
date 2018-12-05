import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.io.*; // for reading into a file and writing out to a file

class Main {
  public static void main(String[] args) {
  
    Scanner keyboard = new Scanner(System.in);
    HashMap <String, Double> menu = new HashMap<>();
    HashMap <String, ArrayList<FoodMenu>> subtotals = new HashMap<>();
    File menuList = new File("MenuList.txt");
    
    //try catch to prevent exception when opening file
    try{
      Scanner scanner = new Scanner(menuList); // placing object in instead of System.in
    
      //reading in the menu file onto hashmap
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
       
         //asking specific person what they want
         System.out.println("What is your name for the order and what item(s) would you like? ");
         String name = keyboard.nextLine();
         String food = keyboard.nextLine();
         System.out.println("How many of those items would you like?");
         int quantity = keyboard.nextInt();
         FoodMenu bill = new FoodMenu(quantity, food, menu.get(food)); 
         
         //if the person already ordered something, add that to the list of things they ordered, otherwise, open new tab
         if(subtotals.containsKey(name)){
            
            ArrayList<FoodMenu> orderedItems = subtotals.get(name);
            orderedItems.add(bill);
            subtotals.put(name, orderedItems);
         } else {
         
            ArrayList<FoodMenu> orderedItems = new ArrayList<FoodMenu>();
            orderedItems.add(bill);
            subtotals.put(name, orderedItems);
         }
         
         //ask them if they want to keep ordering
         System.out.println("Are you finished with your order? Yes or No?");
         keyboard.nextLine();
         answer = keyboard.nextLine();
       } 
   
       //Ask if they want to combine the check 
       System.out.println("Are you paying together?");
       answer = keyboard.nextLine();
       
       //odering separate vs ordering together
       if(answer.equalsIgnoreCase("No")){
       
         paySeparate(subtotals);
       } else {
       
         payTogether(subtotals);
       }
       
      }catch(FileNotFoundException e){
      
         System.out.println("Menu doesn't exist");
      };
  }   

//Method for paying for all food together
public static void payTogether(HashMap<String, ArrayList<FoodMenu>> totals){
   
   double total = 0;

   try{
      //start to wtire out to file
      DecimalFormat formatter = new DecimalFormat("#0.00");
      FileWriter fileWriter = new FileWriter("FinalBill.txt");
      PrintWriter printWriter = new PrintWriter(fileWriter);
      
      //Get keys from hashmap, then use them to separate orders by name
      for(String name: totals.keySet()){
         printWriter.println(name);
         for(FoodMenu food: totals.get(name)){
            //add to total combined bill and show what they ordered
            total += food.computeSubtotal();
            printWriter.println("  "+food);
         }
      }
     
      //show entire order
      printWriter.println();
      printWriter.println("Total: " + formatter.format(total) + " 10% tip: " + formatter.format(total*.1) + " 15% tip: " + formatter.format(total*.15) + " 20% tip: " + formatter.format(total*.2));
      printWriter.close();  
   
   }catch(Exception e){
   
      System.out.print("Receipt doesn't exist");
   };

}
 
public static void paySeparate(HashMap<String, ArrayList<FoodMenu>> totals){

   try{
      //start to wtire out to file
      DecimalFormat formatter = new DecimalFormat("#0.00");
      FileWriter fileWriter = new FileWriter("FinalBill.txt");
      PrintWriter printWriter = new PrintWriter(fileWriter);
      
            
      //Get keys from hashmap, then use them to separate orders by name
      for(String name: totals.keySet()){
         double total = 0;
         printWriter.println(name);
         for(FoodMenu food: totals.get(name)){
            //add to that customers bill and show what they ordered
            total += food.computeSubtotal();
            printWriter.println("  "+food);
         }
         //show entire order for one person
         printWriter.println();
         printWriter.println("Total: " + formatter.format(total) + " 10% tip: " + formatter.format(total*.1) + " 15% tip: " + formatter.format(total*.15) + " 20% tip: " + formatter.format(total*.2));
         printWriter.println();
      }
      
      printWriter.close();
   
   }catch(Exception e){
   
      System.out.print("Receipt doesn't exist");
   };
}
}
