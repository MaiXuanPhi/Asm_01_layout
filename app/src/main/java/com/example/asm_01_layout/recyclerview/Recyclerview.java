package com.example.asm_01_layout.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_01_layout.R;
import com.example.asm_01_layout.SQLite.GiaoDichDAO;
import com.example.asm_01_layout.fragment_con.KhoanChiFragment;
import com.example.asm_01_layout.fragment_con.KhoanThuFragment;
import com.example.asm_01_layout.fragment_con.LoaiChiFragment;
import com.example.asm_01_layout.fragment_con.LoaiThuFragment;
import com.example.asm_01_layout.model.GiaoDich;

import java.util.ArrayList;

public class Recyclerview extends RecyclerView.Adapter<Recyclerview.MyViewHodel>{

    ArrayList<GiaoDich> list;
    Context context;
    GiaoDichDAO giaoDichDAO;
    int maloai;
    Fragment mFragment;

    public Recyclerview(Context context, ArrayList<GiaoDich> list, int maloai, Fragment mFragment){
        this.context = context;
        this.list = list;
        this.maloai = maloai;
        this.mFragment = mFragment;
    }

    @NonNull
    @Override
    public MyViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(context);
        View view = inf.inflate(R.layout.recyclerview, parent, false);
        return new MyViewHodel(view);
    }

    @Override
    public void onBindViewHolder(Recyclerview.MyViewHodel holder, int position) {
        holder.tv_tieude.setText(list.get(position).getTieuDe());
        holder.tv_tien.setText(list.get(position).getTien()+"");
        holder.tv_ngay.setText(list.get(position).getNgay());

        holder.img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (maloai){
                    case 0:
                        KhoanThuFragment khoanThuFragment = (KhoanThuFragment) mFragment;
                        khoanThuFragment.xemGD(position);
                        break;
                    case 1:
                        KhoanChiFragment khoanChiFragment = (KhoanChiFragment) mFragment;
                        khoanChiFragment.xemGD(position);
                        break;
                }
            }
        });

        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("maloai",maloai+"");
                switch (maloai){
                    case 0:
                        KhoanThuFragment khoanThuFragment = (KhoanThuFragment) mFragment;
                        khoanThuFragment.suaGD(position);
                        break;
                    case 1:
                        KhoanChiFragment khoanChiFragment = (KhoanChiFragment) mFragment;
                        khoanChiFragment.suaGD(position);
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
                        GiaoDich giaoDich = list.get(position);
                        giaoDichDAO = new GiaoDichDAO(context);
                        giaoDichDAO.xoaGD(giaoDich);
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHodel extends RecyclerView.ViewHolder{

        TextView tv_tieude, tv_tien, tv_ngay;
        ImageView img_view, img_delete, img_edit;

        public MyViewHodel(View itemView) {
            super(itemView);
            tv_tieude = itemView.findViewById(R.id.tv_tieude);
            tv_tien = itemView.findViewById(R.id.tv_tien);
            tv_ngay = itemView.findViewById(R.id.tv_ngay);
            img_view = itemView.findViewById(R.id.img_view);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }
}
