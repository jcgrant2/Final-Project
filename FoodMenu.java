import java.text.DecimalFormat;

public class FoodMenu {

  int quantity;
  String foodName;
  double price;
  
  //constructor
  public FoodMenu (int quantity, String foodName, double price){

    this.quantity = quantity;
    this.foodName = foodName;
    this.price = price;
  }

  //compute subtotal from price and quantity
  public double computeSubtotal(){
    return quantity * price;
  }
  
  //toString
  public String toString(){
  
      DecimalFormat formatter = new DecimalFormat("#0.00");
      return foodName + " x" + quantity + ": " + formatter.format(computeSubtotal());
  }
}
