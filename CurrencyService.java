package in.codesoft.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONObject;

public class CurrencyService {

    @SuppressWarnings("deprecation")
	public static double getExchangeRate(String base, String target) throws Exception {

        String apiURL = "https://api.exchangerate-api.com/v4/latest/" + base;

        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Invalid Base Currency Code!");
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        JSONObject rates = jsonObject.getJSONObject("rates");

        if (!rates.has(target)) {
            throw new RuntimeException("Invalid Target Currency Code!");
        }

        return rates.getDouble(target);
    }

    public static void displayAvailableCurrencies() throws Exception {

        String apiURL = "https://api.exchangerate-api.com/v4/latest/USD";

        @SuppressWarnings("deprecation")
		URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        JSONObject rates = jsonObject.getJSONObject("rates");

        System.out.println("\n===== AVAILABLE CURRENCIES =====");

        Iterator<String> keys = rates.keys();
        int count = 0;

        while (keys.hasNext()) {
            String key = keys.next();
            System.out.print(key + "  ");
            count++;

            if (count % 10 == 0)
                System.out.println();
        }

        System.out.println("\n================================");
    }
}