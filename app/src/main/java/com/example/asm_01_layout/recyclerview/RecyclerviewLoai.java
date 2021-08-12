package com.example.asm_01_layout.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_01_layout.R;
import com.example.asm_01_layout.SQLite.GiaoDichDAO;
import com.example.asm_01_layout.SQLite.PhanLoaiDAO;
import com.example.asm_01_layout.fragment_con.LoaiChiFragment;
import com.example.asm_01_layout.fragment_con.LoaiThuFragment;
import com.example.asm_01_layout.model.GiaoDich;
import com.example.asm_01_layout.model.PhanLoai;

import java.util.ArrayList;

public class RecyclerviewLoai extends RecyclerView.Adapter<RecyclerviewLoai.MyViewHolder> {

    Context context;
    ArrayList<PhanLoai> list;
    int maloai;
    Fragment fragment;
    PhanLoaiDAO phanLoaiDAO;

    public RecyclerviewLoai(Context context, ArrayList<PhanLoai> list, int maloai, Fragment fragment) {
        this.context = context;
        this.list = list;
        this.maloai = maloai;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(context);
        View view = inf.inflate(R.layout.recyclerview2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewLoai.MyViewHolder holder, int position) {
        holder.tv_tieude.setText(list.get(position).getTenLoai());
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("maloai",""+maloai);
                switch (maloai){
                    case 0:
                        LoaiChiFragment loaiChiFragment = (LoaiChiFragment) fragment;
                        loaiChiFragment.suaGD(position);
                        break;
                    case 1:
                        LoaiThuFragment loaiThuFragment = (LoaiThuFragment) fragment;
                        loaiThuFragment.suaGD(position);
                        break;
                }
            }
        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle("Xóa");
                dialog.setMessage("Bạn có chắc muốn xóa");

                dialog.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhanLoai phanLoai = list.get(position);
                        phanLoaiDAO = new PhanLoaiDAO(context);
                        phanLoaiDAO.xoaPL(phanLoai);
                        list.remove(position);
                        notifyItemRemoved(position);
                    }
                });
                dialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (maloai){
                    case 0:
                        LoaiChiFragment loaiChiFragment = (LoaiChiFragment) fragment;
                        loaiChiFragment.chuyen(list.get(position).getMaLoai());
                        break;
                    case 1:
                        LoaiThuFragment loaiThuFragment = (LoaiThuFragment) fragment;
                        loaiThuFragment.chuyen(list.get(position).getMaLoai());
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tieude;
        ImageView img_edit, img_delete;
        RelativeLayout main;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_tieude = itemView.findViewById(R.id.tv_tieude2);
            img_delete = itemView.findViewById(R.id.img_delete2);
            img_edit = itemView.findViewById(R.id.img_edit2);
            main = itemView.findViewById(R.id.mainlt2);
        }
    }
}
