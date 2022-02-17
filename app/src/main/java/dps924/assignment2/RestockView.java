package dps924.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RestockView extends AppCompatActivity {

    public void buttonAction(View view) {
        try {
            String buttonText = view.getResources().getResourceName(view.getId()).replace("dps924.assignment2:id/", "");
            if (buttonText.equals("button_okay")) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);


                int quantity = Integer.parseInt(((EditText)findViewById(R.id.input_quantity)).getText().toString());
                if (RegisterView.ProductType < 0)
                    ToastController.showToast(this, "A product to restock must be selected first!");
                else if (quantity == 0)
                    ToastController.showToast(this, "Cannot restock a quantity of zero!");
                else if (quantity < 0) {
                    RegisterView.Inventory.get(RegisterView.ProductType).Quantity += quantity;
                    builder.setMessage(
                            "Restocking a negative quantity is reserved for issuing stock as being owed by another inventory."
                    ).setTitle("Warning!");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    RegisterView.adapter_Inventory.notifyDataSetChanged();
                } else {
                    RegisterView.Inventory.get(RegisterView.ProductType).Quantity += quantity;
                    builder.setMessage(
                            "Restocked " + quantity + " x " + RegisterView.Inventory.get(RegisterView.ProductType).Name + " to store inventory."
                    ).setTitle("Success!");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    RegisterView.adapter_Inventory.notifyDataSetChanged();
                }

            } else
                finish();
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restock_view);
        getSupportActionBar().setTitle("Restock");

        ListView productList = findViewById(R.id.restock_products);
        productList.setAdapter(RegisterView.adapter_Inventory);
        RegisterView.adapter_Inventory.notifyDataSetChanged();

        RegisterView.ProductType = -1;
    }
}