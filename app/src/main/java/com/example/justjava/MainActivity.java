package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the plus button is clicked
     */
    public void increment(View view) {
        if(quantity == 100){
            //show error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }
    public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCheckbox);
        boolean haswhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCheckbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameField = (EditText) findViewById(R.id.nameText);
        String name = nameField.getText().toString();

        int price = calculatePrice(haswhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(name, price, haswhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just coffee order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        //check if there is an activity to handle this intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        displayMessage(priceMessage);

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
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.summaryTextView);
//        orderSummaryTextView.setText(message);
//    }
    /**
     * This method calculates the price of the order
     * returns total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        //price of 1 cup of coffee
        int basePrice = 10;

        //Add $2 if the user wants whippedCream
        if(addWhippedCream){
            basePrice = basePrice + 2;
        }
        //Add $2 if the user wants whippedCream
        if(addChocolate){
            basePrice = basePrice + 5;
        }
        return quantity * basePrice;
    }
    /**
     * This method displays the order summary.
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String orderMessage = getString(R.string.order_summary_name,name);
        orderMessage += "\nAdd whipped cream? " + addWhippedCream;
        orderMessage += "\nAdd chocolate?" + addChocolate;
        orderMessage += "\nQuantity: " + quantity;
        orderMessage += "\nTotal: $" + price;
        orderMessage += "\n" + getString(R.string.thank_you);

        return orderMessage;
    }


}
