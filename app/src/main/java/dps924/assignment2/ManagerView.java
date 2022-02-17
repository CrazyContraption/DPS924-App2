package dps924.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManagerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_view);
        getSupportActionBar().setTitle("Manager Functions");
    }

    public void navigate(View view) {
        try {
            String id = view.getResources().getResourceName(view.getId()).replace("dps924.assignment2:id/", "");
            switch (id) {

                case "button_history":
                    Intent intent1 = new Intent(this, HistoryView.class);
                    startActivity(intent1);
                    break;

                case "button_restock":
                    Intent intent2 = new Intent(this, RestockView.class);
                    startActivity(intent2);
                    break;

            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}