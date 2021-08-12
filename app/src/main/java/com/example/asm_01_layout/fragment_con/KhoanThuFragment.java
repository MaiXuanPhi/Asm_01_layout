package com.example.asm_01_layout.fragment_con;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_01_layout.R;
import com.example.asm_01_layout.SQLite.PhanLoaiDAO;
import com.example.asm_01_layout.model.PhanLoai;
import com.example.asm_01_layout.recyclerview.Recyclerview;
import com.example.asm_01_layout.SQLite.GiaoDichDAO;
import com.example.asm_01_layout.fragment.ThuFragment;
import com.example.asm_01_layout.model.GiaoDich;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoanThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoanThuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerView recyclerView;
    public FloatingActionButton floatingActionButton;
    public ArrayList<GiaoDich> list;
    public GiaoDichDAO giaoDichDAO;
    public PhanLoaiDAO phanLoaiDAO;
    public int maloaigd;

    Calendar calendar = Calendar.getInstance();
    int ngay = calendar.get(Calendar.DAY_OF_MONTH);
    int thang = calendar.get(Calendar.MONTH);
    int nam = calendar.get(Calendar.YEAR);

    public KhoanThuFragment() {
        // Required empty public constructor
    }

    public static KhoanThuFragment newInstance(String param1, String param2) {
        KhoanThuFragment fragment = new KhoanThuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.khoanthu_recyclerview);
        floatingActionButton = view.findViewById(R.id.khoanthu_floatingActionButton);

        cap_nhat();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLog(0, 0, "Thêm khoản thu");
            }
        });
    }

    public void cap_nhat() {
        Log.e("maloaigd",maloaigd+"");
        giaoDichDAO = new GiaoDichDAO(getContext());
        list = giaoDichDAO.xemGD2("thu");
        Recyclerview adapter = new Recyclerview(getContext(), list, 0, KhoanThuFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void capnhat(int magd) {
        maloaigd = magd;
        Log.e("maloaigd",maloaigd+"");
        giaoDichDAO = new GiaoDichDAO(getContext());
        list = giaoDichDAO.xemGD(maloaigd);
        Recyclerview adapter = new Recyclerview(getContext(), list, 0, KhoanThuFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
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
        return inflater.inflate(R.layout.fragment_khoang_thu, container, false);
    }

    public void suaGD(int position){
            diaLog(2, position, "Sửa khoản thu");
    }

    public void xemGD(int position){
        diaLog(1, position, "Xem khoản thu");
    }

    public void diaLog(int loaidialog, int position, String tenchucnang){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_them_sua_xem);

        Spinner spinner = dialog.findViewById(R.id.spinner);
        TextView tv = dialog.findViewById(R.id.dialog_tv_loai);
        EditText ed_tieude = dialog.findViewById(R.id.dialog_ed_tieude);
        Button bt_ngay = dialog.findViewById(R.id.dialog_bt_ngay);
        EditText ed_tien = dialog.findViewById(R.id.dialog_ed_tien);
        EditText ed_mota = dialog.findViewById(R.id.dialog_ed_mota);
        Button bt_huy = dialog.findViewById(R.id.dialog_bt_huy);
        Button bt_ok = dialog.findViewById(R.id.dialog_bt_ok);

        Log.e("anh xa","run");

        ArrayList<String> list_sp = new ArrayList<>();
        phanLoaiDAO = new PhanLoaiDAO(getContext());
        list_sp = phanLoaiDAO.xemPL2("thu");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list_sp);
        spinner.setAdapter(adapter);

        String tenloai = phanLoaiDAO.xemPL4(maloaigd); // list giao dich
        if (tenloai.isEmpty()){
            spinner.setSelection(0);
        }else {
            Log.e("tenloai",tenloai);
            int spinerPosition = adapter.getPosition(tenloai);
            spinner.setSelection(spinerPosition);
        }


        tv.setText(tenchucnang);
        if (loaidialog == 0){
            bt_ngay.setText(ngay+"/"+(thang+1)+"/"+nam);
        }else if (loaidialog == 2 || loaidialog == 1){
            ed_tieude.setText(list.get(position).getTieuDe());
            bt_ngay.setText(list.get(position).getNgay());
            ed_tien.setText(list.get(position).getTien()+"");
            ed_mota.setText(list.get(position).getMoTa());
        }

        bt_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        bt_ngay.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieude = ed_tieude.getText().toString();
                String ngay = bt_ngay.getText().toString();
                int tien = Integer.parseInt(ed_tien.getText().toString());
                String mota = ed_mota.getText().toString();
                String tenloai = spinner.getSelectedItem().toString();
                int maloai = phanLoaiDAO.xemPL3(tenloai);

                Log.e("maloai",maloai+"");
                if (loaidialog == 0){
                    GiaoDich giaoDich = new GiaoDich(tien, maloai, tieude, ngay, mota);
                    giaoDichDAO = new GiaoDichDAO(getContext());
                    giaoDichDAO.themGD(giaoDich);
                    Log.e("themGd","run");
                    capnhat(maloai);
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else if (loaidialog == 2){
                    int magd = list.get(position).getMaGd();
                    GiaoDich giaoDich = new GiaoDich(magd,tien, maloai, tieude, ngay, mota);
                    giaoDichDAO = new GiaoDichDAO(getContext());
                    giaoDichDAO.suaGD(giaoDich);
                    capnhat(maloai);
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
}