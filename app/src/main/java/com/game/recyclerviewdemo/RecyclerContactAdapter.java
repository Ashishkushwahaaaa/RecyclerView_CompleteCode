package com.game.recyclerviewdemo;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>{
    Context context;
    ArrayList<ContactModel> arrContacts;

    private int lastPosition = -1;

    public RecyclerContactAdapter(Context context,ArrayList<ContactModel> arrContacts){
        this.context = context;
        this.arrContacts=arrContacts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        holder.imgContact.setImageResource(arrContacts.get(position).img);
        holder.txtName.setText(arrContacts.get(position).name);
        holder.txtNumber.setText(arrContacts.get(position).number);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.contact_add);

                TextView txtDialogHeading = dialog.findViewById(R.id.txtDialogHeading);
                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                txtDialogHeading.setText("Update Contact");
                btnAction.setText("Update");

                edtName.setText(arrContacts.get(position).name);
                edtNumber.setText(arrContacts.get(position).number);


                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="",number="";
                        if (!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }else {
                            Toast.makeText(context, "Please Enter the Name!", Toast.LENGTH_SHORT).show();
                        }
                        if (!edtNumber.getText().toString().equals("")){
                            number=edtNumber.getText().toString();
                        }else{
                            Toast.makeText(context, "Please Enter the Number!", Toast.LENGTH_SHORT).show();
                        }

                        arrContacts.set(position,new ContactModel(arrContacts.get(position).img,name,number));
                        notifyItemChanged(position);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrContacts.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;

            }
        });

        setAnimation(holder.itemView,position);


    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtNumber;
        ImageView imgContact;
        LinearLayout llRow;
        public ViewHolder(View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            imgContact = itemView.findViewById(R.id.imgContact);
            llRow = itemView.findViewById(R.id.llrow);
        }
    }
    public void setAnimation(View viewToAnimate,int position){

        if(position>lastPosition) {
            //This condition is used to make the effect of animation only for the first time
            Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            //If we Want to add our custom animation then only we need to create the anim folder adding how the animation should be and then the path should only be R.anim.xyzfolder
            viewToAnimate.startAnimation(slideIn);
            lastPosition=position;
        }
    }
}


