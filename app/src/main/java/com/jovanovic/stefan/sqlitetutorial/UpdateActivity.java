package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText ItemName_input, ItemStatus_input, Quantity_input,Datein_input,Dateout_input,Location_input;
    Button update_button, delete_button;

    String id, name, status, quantity,datein,dateout,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ItemName_input = findViewById(R.id.ItemName_input2);
        ItemStatus_input = findViewById(R.id.ItemStatus_input2);
        Quantity_input = findViewById(R.id.Quantity_input2);
        Datein_input=findViewById(R.id.DateIn_input2);
        Dateout_input=findViewById(R.id.DateOut_input2);
        Location_input=findViewById(R.id.Location_input2);

        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = ItemName_input.getText().toString().trim();
                status = ItemStatus_input.getText().toString().trim();
                quantity = Quantity_input.getText().toString().trim();
                datein =Datein_input.getText().toString().trim();
                dateout=Dateout_input.getText().toString().trim();
                location=Location_input.getText().toString().trim();

                myDB.updateData(id, name, status, quantity,datein,dateout,location);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("status") &&
                getIntent().hasExtra("quantity")&&
                getIntent().hasExtra("datein")&&
                getIntent().hasExtra("dateout")&&
                getIntent().hasExtra("location")){

            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            status = getIntent().getStringExtra("status");
            quantity = getIntent().getStringExtra("quantity");
            datein = getIntent().getStringExtra("datein");
            dateout = getIntent().getStringExtra("dateout");
            location = getIntent().getStringExtra("location");


            //Setting Intent Data
            ItemName_input.setText(name);
            ItemStatus_input.setText(status);
            Quantity_input.setText(quantity);
            Datein_input.setText(datein);
            Dateout_input.setText(dateout);
            Location_input.setText(location);

            Log.d("stev", name +" "+ status +" "+ quantity);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
