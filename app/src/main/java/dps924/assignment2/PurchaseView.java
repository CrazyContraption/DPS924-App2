package dps924.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PurchaseView extends AppCompatActivity {

    private int ProductType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_view);

        Intent intent = getIntent();
        ProductType = (int)intent.getIntExtra("ProductType", -1);

        Purchase purchase = RegisterView.History.get(ProductType);
        DecimalFormat f = new DecimalFormat("0.00");

        ((TextView)findViewById(R.id.purchase_name)).setText(" " + purchase.Name);
        ((TextView)findViewById(R.id.purchase_price)).setText(" $" + f.format(purchase.Price) + " CAD");
        ((TextView)findViewById(R.id.purchase_quantity)).setText(" " + purchase.Quantity + " x");
        ((TextView)findViewById(R.id.purchase_date)).setText(" " + purchase.Time.toString());
    }
}