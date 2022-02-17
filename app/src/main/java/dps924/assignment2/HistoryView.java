package dps924.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HistoryView extends AppCompatActivity {

    private PurchasableRecyclerAdapter adapter_History;

    public void handleAction(View view) {
        Intent intent = new Intent(this, PurchaseView.class);
        intent.putExtra("ProductType", view.getId());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_view);
        getSupportActionBar().setTitle("Purchase History");

        RecyclerView list_history = findViewById(R.id.list_history);
        adapter_History = new PurchasableRecyclerAdapter(RegisterView.History);
        list_history.setAdapter(adapter_History);
        list_history.setLayoutManager(new LinearLayoutManager(this));
        adapter_History.notifyDataSetChanged();
    }
}