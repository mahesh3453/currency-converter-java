package in.codesoft.tasks;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("====== CURRENCY CONVERTER ======");

        try {
            CurrencyService.displayAvailableCurrencies();

            System.out.print("\nEnter Base Currency: ");
            String baseCurrency = sc.nextLine().toUpperCase();

            System.out.print("Enter Target Currency: ");
            String targetCurrency = sc.nextLine().toUpperCase();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            double exchangeRate = CurrencyService.getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * exchangeRate;

            System.out.println("\n----- RESULT -----");
            System.out.printf("%.2f %s = %.2f %s\n",
                    amount, baseCurrency,
                    convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}