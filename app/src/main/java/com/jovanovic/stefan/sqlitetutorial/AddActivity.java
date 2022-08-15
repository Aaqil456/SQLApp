package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText ItemName_input, ItemStatus_input, Quantity_input,Datein_input,Dateout_input,Location_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ItemName_input = findViewById(R.id.ItemName_input2);
        ItemStatus_input = findViewById(R.id.ItemStatus_input2);
        Quantity_input = findViewById(R.id.Quantity_input2);
        Datein_input=findViewById(R.id.DateIn_input2);
        Dateout_input=findViewById(R.id.DateOut_input2);
        Location_input=findViewById(R.id.Location_input2);

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addItem(
                        ItemName_input.getText().toString().trim(),
                        ItemStatus_input.getText().toString().trim(),
                        Integer.valueOf(Quantity_input.getText().toString().trim()),
                        Datein_input.getText().toString().trim(),
                        Dateout_input.getText().toString().trim(),
                        Location_input.getText().toString().trim());
            }
        });
    }
}
