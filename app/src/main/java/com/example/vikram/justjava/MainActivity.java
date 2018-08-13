/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.vikram.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * ();This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedcream = (CheckBox) findViewById(R.id.checkbox) ;
        boolean haswippedcream = whippedcream.isChecked();
        CheckBox choclate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean choclatecream = choclate.isChecked();
        EditText editText= (EditText) findViewById(R.id.name) ;
       String value= editText.getText().toString();
        CheckBox coffee= (CheckBox) findViewById(R.id.drink) ;
        boolean hascoffee = coffee.isChecked();
        CheckBox tea = (CheckBox) findViewById(R.id.drink1) ;
        boolean hastea = tea.isChecked();
        CheckBox cold = (CheckBox) findViewById(R.id.drink2) ;
        boolean hascold = cold.isChecked();
        int price = calculatePrice(haswippedcream,choclatecream,hascoffee,hastea,hascold);
        String priceMessage = calculateOrderSummary(price,haswippedcream,choclatecream,hascoffee,hastea,hascold,value)  ;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+ value);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


    public void increment(View view) {
        if (quantity==100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return ;}

       quantity=quantity+1;

        display(quantity);


    }
    public void decrement(View view) {


        
        if (quantity==1 || quantity==0) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity = quantity - 1;
        display(quantity);


    }

    private int calculatePrice(boolean addwipped, boolean creams,boolean coffee,boolean tea,boolean cold){
        int pricePerCup=5;
        if(addwipped==true){
            pricePerCup+=1;
        } if (creams==true)
        {
            pricePerCup += 2;

        } if(coffee == true){
            pricePerCup += 3;
        }if( tea==true){
            pricePerCup+= 4;

        } if(cold == true){
            pricePerCup+= 5;
        }
        return quantity*pricePerCup;
    }
    public String calculateOrderSummary(int price,boolean addwipped, boolean creams,boolean coffee,boolean tea,boolean cold,String edittext){
        String orderMeassage = "Name: "+edittext+"\nadd whippedCream "+ addwipped+"\nAdd choloclate "+creams+ "\nQuantity "+ quantity ;
       orderMeassage += "\nAdd drinks "+ coffee + tea + cold;
        orderMeassage += "\n\nTOTAL PRICE = $"+price+ "\n\nThank you";
        return orderMeassage;
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    }
