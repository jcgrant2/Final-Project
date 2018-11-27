public class FoodMenu {

  String foodName;
  double price;
  
  public FoodMenu (int quantity, String foodName, double price){

    this.quantity = quantity;
    this.foodName = foodName;
    this.price = price;
  }

  public int getQuantity(){

    return quantity;
  }

  public String getName(){

    return foodName;
  }

  public double getPrice(){

    return price;
  }

  public double computeSubtotal(){
    return quantity * price;
  }
}
