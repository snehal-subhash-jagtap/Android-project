package com.example.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidproject.FycoHelper.FycoHelper;

public class SecondActivity extends AppCompatActivity {

    Button insert,update,delete,view,clear;
    EditText roll,name,cont,per,addr;
    FycoHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        insert=findViewById(R.id.buttoninsert);
        update=findViewById(R.id.buttonupdate);
        delete=findViewById(R.id.buttondelete);
        view=findViewById(R.id.buttonview);
        clear=findViewById(R.id.buttonclear);


        roll=findViewById(R.id.editTextroll);
        name=findViewById(R.id.editTextname);
        per=findViewById(R.id.editTextpercentage);
        cont=findViewById(R.id.editTextphone);
        addr=findViewById(R.id.editTextaddress);

        db=new FycoHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer rolltxt=Integer.parseInt(roll.getText().toString());
                String nametxt=name.getText().toString();
                String pertxt=per.getText().toString();
                String contxt=cont.getText().toString();
                String addrtxt=addr.getText().toString();

                Boolean checkinsertdata=db.insertuserdata(rolltxt,nametxt,pertxt,contxt,addrtxt);
                if (checkinsertdata==true){
                    Toast.makeText(SecondActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SecondActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer rolltxt=Integer.parseInt(roll.getText().toString());
                String nametxt=name.getText().toString();
                String pertxt=per.getText().toString();
                String contxt=cont.getText().toString();
                String addrtxt=addr.getText().toString();

                Boolean checkupdatedata=db.updateuserdata(rolltxt,nametxt,pertxt,contxt,addrtxt);
                if (checkupdatedata==true){
                    Toast.makeText(SecondActivity.this, "New Entry Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SecondActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nametxt=name.getText().toString();

                Boolean checkdeletedata=db.deleteuserdata(nametxt);
                if (checkdeletedata==true){
                    Toast.makeText(SecondActivity.this, "New Entry Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SecondActivity.this, " Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Cursor res=db.getdata();
                if (res.getCount()==0){
                    Toast.makeText(SecondActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("*********************"+"\n");
                    buffer.append("                     "+"\n");
                    buffer.append("roll=:"+res.getString(0)+"\n");
                    buffer.append("name=:"+res.getString(1)+"\n");
                    buffer.append("percentage=:"+res.getString(2)+"\n");
                    buffer.append("contact=:"+res.getString(3)+"\n");
                    buffer.append("address=:"+res.getString(4)+"\n");
                    buffer.append("                     "+"\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(SecondActivity.this);
                builder.setCancelable(true);
                builder.setTitle("******** STUDENT DATA ********");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll.setText(null);
                name.setText(null);
                per.setText(null);
                cont.setText(null);
                addr.setText(null);

            }
        });
    }

}