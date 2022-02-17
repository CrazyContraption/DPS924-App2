package dps924.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PurchasableViewListAdapter<Purchasable> extends ArrayAdapter<Purchasable> {
    private final Context context;
    private final ArrayList<Purchasable> values;

    public PurchasableViewListAdapter(Context context, ArrayList<Purchasable> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.purchasable_product, parent, false);
        rowView.setId(position * -1 - 2);

        TextView view_name = (TextView) rowView.findViewById(R.id.name);
        view_name.setText(((dps924.assignment2.Purchasable)this.values.get(position)).Name);

        DecimalFormat f = new DecimalFormat("0.00");
        TextView view_price = (TextView) rowView.findViewById(R.id.price);
        view_price.setText("$" + f.format(((dps924.assignment2.Purchasable)this.values.get(position)).Price) + " CAD");

        TextView view_quantity = (TextView) rowView.findViewById(R.id.quantity);
        view_quantity.setText("x " + ((dps924.assignment2.Purchasable)this.values.get(position)).Quantity);

        //rowView.setSelected(false);

        return rowView;
    }
}