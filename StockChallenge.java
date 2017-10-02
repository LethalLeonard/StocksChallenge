import javax.swing.JOptionPane;

public class StockChallenge
{
   public static void main(String[] args)
   {
      //Creates used variables
      final double TRANSACTION_COMMISSION = 0.02;
      double sharesBuy, priceShareBuy, sharesSell, priceShareSell, profit, commBuy, commSell, totalSell, totalBuy, totalCom, stillOwned, stillOwnedPrice;
      String output;
      boolean isInputValid, isBuyBigger;
      
      //handles all user input
      sharesBuy = Double.parseDouble(JOptionPane.showInputDialog("How many shares did you purchase?"));
      priceShareBuy = Double.parseDouble(JOptionPane.showInputDialog("At what price were the shares purchased?"));
      
      sharesSell = Double.parseDouble(JOptionPane.showInputDialog("How many shares were sold?"));
      priceShareSell = Double.parseDouble(JOptionPane.showInputDialog("At what price were the shares sold?"));
      
      //ensures input is valid
      isInputValid = isValid(sharesBuy, priceShareBuy, sharesSell, priceShareSell);
      isBuyBigger = isValid(sharesBuy, sharesSell);
      
      if(isBuyBigger)
      {
         JOptionPane.showMessageDialog(null, "You can not sell more stocks than owned.");
         System.exit(0);
      }
      if(isInputValid)
      {
         JOptionPane.showMessageDialog(null, "You can not have a less than one stock or sell for free/less than 0");
         System.exit(0);
      }
      
      //Calculates total money for the buy and sell transactions
      totalSell = sharesSell * priceShareSell;
      totalBuy = sharesBuy * priceShareBuy;
      
      //Calculates all commission-related things
      commBuy = totalBuy * TRANSACTION_COMMISSION;
      commSell = totalSell * TRANSACTION_COMMISSION;
      totalCom = commBuy + commSell;
      
      //Begins the format of the output string
      output = String.format("Total Buy Price: $%.2f \n" + 
                             "Total Buy Commission: $%.2f\n" + 
                             "Total Sell Price: $%.2f\n" + 
                             "Total Sell Commission: $%.2f\n",
                             totalBuy,commBuy,totalSell,commSell);
      
      //Profit calculation, and adds a statement to the output based on profit
      profit = ((sharesSell * priceShareSell) - (sharesSell * priceShareBuy)) - totalCom;
      
      if(profit > 0)
      {
         output += String.format("Total Profit/Loss: $%.2f\n" + 
                                 "This was a wise investment.\n", profit);
      }
      else
      {
         output += String.format("Total Profit/Loss: $%.2f\n" + 
                                 "This was an unwise investment.\n", profit);
      }
      
      //Calculates stocks still owned and adds to output
      stillOwned = sharesBuy - sharesSell;
      stillOwnedPrice = stillOwned * priceShareSell;
      
      output += String.format("Stocks still owned: %.0f\n" + 
                              "Current value of owned Stocks: $%.2f",
                              stillOwned,stillOwnedPrice);
                              
      JOptionPane.showMessageDialog(null, output);
   }
   //A series of methods used to check if the input is valid
   public static boolean isValid(double toCheck)
   {
      return toCheck <= 0; //used to ensure each input is greater than 0
   }
   
   public static boolean isValid(double buyShares, double sellShares)
   {
      return sellShares > buyShares;//used to ensure that fewer are being sold than owned
   }
   
   //master method that makes sure all inputs are greater than 0
   public static boolean isValid(double buyShares, double buySharesPrice, double sellShares, double sellSharesPrice)
   {
      boolean tempA, tempB, tempC, tempD;
      tempA = isValid(buyShares);
      tempB = isValid(buySharesPrice);
      tempC = isValid(sellShares);
      tempD = isValid(sellSharesPrice);
      if(tempA || tempB || tempC || tempD)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
}