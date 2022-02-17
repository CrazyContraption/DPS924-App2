package dps924.assignment2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PurchasableRecyclerAdapter extends RecyclerView.Adapter<PurchasableRecyclerAdapter.ViewHolder> {

    private final ArrayList<Purchase> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    public PurchasableRecyclerAdapter(ArrayList<Purchase> data) {
        values = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.purchasable_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.itemView.setId(position);
        DecimalFormat f = new DecimalFormat("0.00");
        ((TextView)viewHolder.itemView.findViewById(R.id.name)).setText(RegisterView.History.get(position).Name);
        ((TextView)viewHolder.itemView.findViewById(R.id.price)).setText("$" + f.format(RegisterView.History.get(position).getTotal()) + " CAD");
        ((TextView)viewHolder.itemView.findViewById(R.id.quantity)).setText(RegisterView.History.get(position).Quantity + " x");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    /*
    public PurchasableRecyclerAdapter(Context context, ArrayList<Purchasable> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.register_product, parent, false);
        rowView.setId(position * -1 - 2);

        TextView view_name = (TextView) rowView.findViewById(R.id.name);
        view_name.setText(((dps924.assignment2.Purchasable)this.values.get(position)).Name);

        DecimalFormat f = new DecimalFormat("##.00");
        TextView view_price = (TextView) rowView.findViewById(R.id.price);
        view_price.setText("$" + f.format(((dps924.assignment2.Purchasable)this.values.get(position)).Price));

        TextView view_quantity = (TextView) rowView.findViewById(R.id.quantity);
        view_quantity.setText("" + ((dps924.assignment2.Purchasable)this.values.get(position)).Quantity);

        return rowView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    */
}