package com.game.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ArrayList<ContactModel> arrContact = new ArrayList<>();
    RecyclerContactAdapter adapter;
    FloatingActionButton btnOpenDialog;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.contact_add);
                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="",number="";

                        if(!edtName.getText().toString().equals("")) {
                            name = edtName.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter the Name!", Toast.LENGTH_SHORT).show();
                        }
                        if (!edtNumber.getText().toString().equals("")) {
                            number = edtNumber.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter the Number!", Toast.LENGTH_SHORT).show();
                        }
                        arrContact.add(new ContactModel(name,number));
                        adapter.notifyItemInserted(arrContact.size()-1);
                        recyclerView.scrollToPosition(arrContact.size()-1);
                        dialog.dismiss();
                    }
                });

                dialog.show();
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        arrContact.add(new ContactModel(R.drawable.boy,"A","9889123456"));
        arrContact.add(new ContactModel(R.drawable.boy1,"B","9889654321"));
        arrContact.add(new ContactModel(R.drawable.girl,"C","9889135246"));
        arrContact.add(new ContactModel(R.drawable.boy2,"D","9889246123"));
        arrContact.add(new ContactModel(R.drawable.girl1,"E","9889582344"));
        arrContact.add(new ContactModel(R.drawable.man,"F","9889581123"));
        arrContact.add(new ContactModel(R.drawable.boy1,"G","9889584562"));
        arrContact.add(new ContactModel(R.drawable.girl,"H","9889583454"));
        arrContact.add(new ContactModel(R.drawable.boy2,"I","9889582322"));
        arrContact.add(new ContactModel(R.drawable.girl1,"J","9889581323"));
        arrContact.add(new ContactModel(R.drawable.man,"K","9889589872"));
        arrContact.add(new ContactModel(R.drawable.boy1,"L","9889584562"));
        arrContact.add(new ContactModel(R.drawable.girl,"M","9889582343"));
        arrContact.add(new ContactModel(R.drawable.boy2,"N","9889585272"));
        arrContact.add(new ContactModel(R.drawable.girl1,"O","9889587272"));
        arrContact.add(new ContactModel(R.drawable.man,"P","9889580000"));






        adapter = new RecyclerContactAdapter(MainActivity.this,arrContact);
        recyclerView.setAdapter(adapter);
    }
}