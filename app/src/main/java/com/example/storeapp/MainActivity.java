package com.example.storeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText etname,etmarks,etid;
    Button insertbtn,showbtn,updatebtn,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        etname = findViewById(R.id.name);
        etmarks = findViewById(R.id.marks);
        etid = findViewById(R.id.ids);
        insertbtn = findViewById(R.id.Button);
        showbtn = findViewById(R.id.show);
        updatebtn = findViewById(R.id.Update);
        delete=findViewById(R.id.delete);
        insertdata();
        showdata();
        update();
        delete();
    }
    public void insertdata()
    {
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean Inserted = mydb.insertData(etname.getText().toString(),etmarks.getText().toString());
                if(Inserted)
                {
                    Toast.makeText(MainActivity.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error Inserting", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void showdata()
    {
        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = mydb.Showdata();
                if(cursor.getCount() == 0)
                {
                    message("Error","No data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext())
                {
                    buffer.append("Id : " + cursor.getString(0)+"\n")
                            .append("Name : "+ cursor.getString(1)+"\n")
                            .append("marks : "+ cursor.getString(2)+"\n");
                }
                message("Data",buffer.toString());
            }
        });
    }
    public void update()
    {
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean updated = mydb.update(etid.getText().toString(),etname.getText().toString(),
                        etmarks.getText().toString());
                if(updated)
                {
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error in Updating", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void delete()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer delete = mydb.delete(etid.getText().toString());
                if(delete > 0)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void message(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title)
                .setMessage(message)
                .show();
    }
}