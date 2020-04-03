package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the plus button is clicked
     */
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }

    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);

    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.summaryTextView);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.summaryTextView);
        orderSummaryTextView.setText(message);
    }
    /**
     * This method calculates the price of the order
     * returns total price
     */
    private int calculatePrice(){
        int price = quantity * 10;
        return price;
    }
    /**
     * This method displays the order summary.
     */
    private String createOrderSummary(int price){
        String orderMessage = "Name: Beatrice";
        orderMessage += "\nQuantity: " + quantity;
        orderMessage += "\nTotal: $" + price;
        orderMessage += "\nThank you!";

        return orderMessage;
    }
}
