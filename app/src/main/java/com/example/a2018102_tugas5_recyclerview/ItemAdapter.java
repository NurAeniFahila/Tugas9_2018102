package com.example.a2018102_tugas5_recyclerview;

import static com.example.a2018102_tugas5_recyclerview.DBmain.TABLEDEVISI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    int singledata;
    ArrayList<Model> modelArrayList;
    SQLiteDatabase sqLiteDatabase;

    //generate constructor
    public ItemAdapter(Context context, int singledata,
                       ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull
                                                             ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder
                                         holder, @SuppressLint("RecyclerView") int position) {
        final Model model = modelArrayList.get(position);
        byte[] image = model.getProavatar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,
                image.length);
        holder.imageavatar.setImageBitmap(bitmap);
        holder.txtdevisi.setText(model.getUserdevisi());
        holder.txtstar.setText(model.getUserstar());
        holder.txtjoin.setText(model.getUserjoin());
        //flow menu
        holder.flowmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,
                        holder.flowmenu);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_menu:
                                //edit operation
                                Bundle bundle = new Bundle();

                                bundle.putInt("id", model.getId());

                                bundle.putString("devisi", model.getUserdevisi());

                                bundle.putString("star", model.getUserstar());

                                bundle.putString("join", model.getUserjoin());

                                bundle.putByteArray("avatar", model.getProavatar());
                                Intent intent = new
                                        Intent(context, ManageItem.class);

                                intent.putExtra("userdata", bundle);
                                context.startActivity(intent);
                                break;
                            case R.id.delete_menu:
                                //delete operation
                                DBmain dBmain = new
                                        DBmain(context);
                                sqLiteDatabase =
                                        dBmain.getReadableDatabase();
                                long recdelete =
                                        sqLiteDatabase.delete(TABLEDEVISI, "id=" + model.getId(), null);
                                if (recdelete != -1) {
                                    Toast.makeText(context, "DataDeleted", Toast.LENGTH_SHORT).show();
                                    //remove positon after deleted
                                    modelArrayList.remove(position);
                                    //update data
                                    notifyDataSetChanged();
                                }
                                break;
                            default:
                                return false;
                        }
                        return false;
                    }
                });
                //display menu
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageavatar;
        TextView txtdevisi, txtstar, txtjoin;
        ImageButton flowmenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageavatar =
                    (ImageView) itemView.findViewById(R.id.viewavatar);
            txtdevisi = (TextView)
                    itemView.findViewById(R.id.txt_devisi);
            txtstar = (TextView)
                    itemView.findViewById(R.id.txt_star);
            txtjoin = (TextView)
                    itemView.findViewById(R.id.txt_join);
            flowmenu = (ImageButton)
                    itemView.findViewById(R.id.flowmenu);
        }
    }
}

