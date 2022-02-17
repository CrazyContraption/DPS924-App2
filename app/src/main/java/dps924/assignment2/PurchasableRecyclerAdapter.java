package dps924.assignment2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PurchasableRecyclerAdapter extends RecyclerView.Adapter<PurchasableRecyclerAdapter.ViewHolder> {

    private final ArrayList<Purchase> values;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    public PurchasableRecyclerAdapter(ArrayList<Purchase> data) {
        values = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.purchasable_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
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
}