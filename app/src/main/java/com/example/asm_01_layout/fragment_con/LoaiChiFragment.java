package com.example.asm_01_layout.fragment_con;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_01_layout.R;
import com.example.asm_01_layout.SQLite.PhanLoaiDAO;
import com.example.asm_01_layout.fragment.ChiFragment;
import com.example.asm_01_layout.fragment.ThuFragment;
import com.example.asm_01_layout.model.PhanLoai;
import com.example.asm_01_layout.recyclerview.Recyclerview;
import com.example.asm_01_layout.SQLite.GiaoDichDAO;
import com.example.asm_01_layout.model.GiaoDich;
import com.example.asm_01_layout.recyclerview.RecyclerviewLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiChiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerView recyclerView;
    public FloatingActionButton floatingActionButton;
    public ArrayList<PhanLoai> list;
    public PhanLoaiDAO phanLoaiDAO;
    public int maloai = 0;
    public String trangthai = "chi";


    public LoaiChiFragment() {
        // Required empty public constructor
    }

    public static LoaiChiFragment newInstance(String param1, String param2) {
        LoaiChiFragment fragment = new LoaiChiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.loaichi_recyclerview);
        floatingActionButton = view.findViewById(R.id.loaichi_floatingActionButton);

        capnhat();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLog(0, 0, "Thêm loại chi");
            }
        });
    }

    public void xemGD(int position){
        diaLog(1, position, "Xem loại chi");
    }

    public void suaGD(int position){
        diaLog(2, position, "Sửa loại chi");
    }

    private void capnhat() {
        phanLoaiDAO = new PhanLoaiDAO(getContext());
        list = phanLoaiDAO.xemPL(trangthai);
        RecyclerviewLoai adapter = new RecyclerviewLoai(getContext(), list, maloai, LoaiChiFragment.this); // sua
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void diaLog(int loaidialog, int position, String tenchucnang){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_sua_xoa_loai);

        TextView tv = dialog.findViewById(R.id.tv_sua_xoa_loai);
        EditText ed_tieude = dialog.findViewById(R.id.ed_sua_xoa_loai);
        Button bt_huy = dialog.findViewById(R.id.bt_huy_sua_xoa_loai);
        Button bt_ok = dialog.findViewById(R.id.bt_ok_sua_xoa_loai);

        tv.setText(tenchucnang);

        if (loaidialog == 2){
            ed_tieude.setText(list.get(position).getTenLoai());
        }

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieude = ed_tieude.getText().toString();
                if (loaidialog == 0){
                    PhanLoai phanLoai = new PhanLoai(tieude,trangthai); //sua
                    phanLoaiDAO.themPL(phanLoai);
                    capnhat();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else if (loaidialog == 2){
                    int maloai = list.get(position).getMaLoai();
                    PhanLoai phanLoai = new PhanLoai(maloai, tieude); // sua
                    phanLoaiDAO = new PhanLoaiDAO(getContext());
                    phanLoaiDAO.suaPL(phanLoai);
                    capnhat();
                    Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        bt_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void chuyen (int maloai){
        FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
        ChiFragment chiFragment = (ChiFragment) fragmentManager.findFragmentById(R.id.fragmnet_context);
        KhoanChiFragment khoanChiFragment = (KhoanChiFragment) fragmentManager.findFragmentById(R.id.chi_viewpager);
        chiFragment.viewPager.setCurrentItem(1);
        khoanChiFragment.capnhat(maloai);
    }

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loai_chi, container, false);
    }
}