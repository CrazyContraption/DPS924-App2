package dps924.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RegisterView extends AppCompatActivity {
    
    public static ArrayList<Purchase> History = new ArrayList<Purchase>();
    public static ArrayList<Purchasable> Inventory = new ArrayList<Purchasable>();

    public static PurchasableViewListAdapter<Purchasable> adapter_Inventory;

    public static Toast MyToast;

    private static boolean isAlive = false;

    public static int ProductType = -1;

    public void handleAction(View view) {
        try {
            if (view.getClass().getName().contains("Button")) {
                String id = view.getResources().getResourceName(view.getId()).replace("dps924.assignment2:id/", "");
                switch (id) {

                    case "button_manager":
                        Intent intent = new Intent(this, ManagerView.class);
                        //intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                        break;

                    case "button_buy":
                        doPurchase();
                        break;

                }
            } else { // Product Selection
                ProductType = Math.abs(view.getId()) -2;

                //view.setSelected(true);
                ToastController.showToast(this, "Selected " + Inventory.get(ProductType).Name);

                if (((View)view.getParent()).getId() == R.id.restock_products) {
                    ListView list = findViewById(R.id.restock_products);
                    list.setSelection(ProductType);
                } else {
                    ListView list = findViewById(R.id.list_products);
                    list.setSelection(ProductType);

                    TextView view_ProductType = findViewById(R.id.display_productType);
                    view_ProductType.setText(Inventory.get(ProductType).Name);

                    NumberPicker picker = findViewById(R.id.input_quantityPicker);
                    picker.setMaxValue(Math.min(Inventory.get(ProductType).Quantity, picker.getDisplayedValues().length - 1));

                    onValueChange(picker, picker.getValue(), picker.getValue());
                }
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if (ProductType < 0)
            return;

        if (newVal > picker.getMaxValue())
            picker.setValue(picker.getMaxValue());
        if (newVal < picker.getMinValue())
            picker.setValue(picker.getMinValue());

        int quantity = Inventory.get(ProductType).Quantity;

        if (newVal > quantity || oldVal > quantity) {
            ToastController.showToast(this, "Not enough quantity in stock!");
            picker.setValue(quantity);
        }

        Purchasable temp_item =  new Purchasable(Inventory.get(ProductType), picker.getValue());
        DecimalFormat f = new DecimalFormat("0.00");
        ((TextView)findViewById(R.id.display_total)).setText("$" + f.format(temp_item.getTotal()));
    }

    private void doPurchase() {
        if (ProductType == -1) {
            ToastController.showToast(this, "Cannot make an empty purchase!");
            return;
        }
        int quantity = ((NumberPicker)findViewById(R.id.input_quantityPicker)).getValue();
        if (quantity > Inventory.get(ProductType).Quantity)
            ToastController.showToast(this, "Not enough quantity in stock!");
        else if (quantity == 0)
            ToastController.showToast(this, "Cannot make an empty purchase!");
        else {
            History.add(
                    0,
                    new Purchase(
                            Inventory.get(ProductType),
                            quantity
                    )
            );
            Inventory.get(ProductType).Quantity -= quantity;
            adapter_Inventory.notifyDataSetChanged();

            DecimalFormat f = new DecimalFormat("0.00");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(
                    "Thank you for your purchase!\n" +
                    quantity + "x - " + History.get(0).Name + " @ $" + f.format(History.get(0).Price) + " each.\n\n" +
                    "Total: $" + f.format(History.get(0).getTotal())
                ).setTitle("Success!");
            AlertDialog dialog = builder.create();
            dialog.show();

            reset();
        }
    }

    private void fullReset () {
        setupQuantityPicker();

        ListView productList = findViewById(R.id.list_products);
        adapter_Inventory = new PurchasableViewListAdapter<Purchasable>(this, Inventory);
        productList.setAdapter(adapter_Inventory);
        setupProducts();
        adapter_Inventory.notifyDataSetChanged();

        reset();
    }

    private void reset () {
        ((TextView)findViewById(R.id.display_productType)).setText("");
        ((TextView)findViewById(R.id.display_total)).setText("$0.00");
        NumberPicker picker = findViewById(R.id.input_quantityPicker);
        picker.setValue(0);
        picker.setMaxValue(0);
    }

    private void setupProducts() {
        if (isAlive)
            return;
        Inventory.add(new Purchasable("Pants", 10,  20.44 ));
        Inventory.add(new Purchasable("Shoes", 100, 10.44 ));
        Inventory.add(new Purchasable("Hats",  30,  5.9 ));
        isAlive = true;
    }

    private void setupQuantityPicker() {
        String[] data = new String[101];
        for (int index = 0; index < 101; index++ )
            data[index] = "" + index;

        NumberPicker picker = findViewById(R.id.input_quantityPicker);
        picker.setDisplayedValues(data);
        picker.setMinValue(0);

        picker.setOnValueChangedListener(this::onValueChange);
    }

    public RegisterView() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);
        getSupportActionBar().setTitle("Cash Register");
        fullReset();
    }
}