package com.example.asm_01_layout.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asm_01_layout.R;
import com.example.asm_01_layout.SQLite.GiaoDichDAO;
import com.example.asm_01_layout.SQLite.PhanLoaiDAO;
import com.example.asm_01_layout.model.GiaoDich;
import com.example.asm_01_layout.model.PhanLoai;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhanLoaiDAO phanLoaiDAO;
    public GiaoDichDAO giaoDichDAO;
    public ArrayList<PhanLoai> list_phanloai = new ArrayList<>();
    public ArrayList<GiaoDich> list_giaodich = new ArrayList<>();
    public TextView tv_loaithu_sl, tv_khoanthu_sl, tv_khoanthu_tien, tv_loaichi_sl, tv_khoanchi_sl, tv_khoanchi_tien, tv_tong;

    public ThongKeFragment() {
        // Required empty public constructor
    }

    public static ThongKeFragment newInstance(String param1, String param2) {
        ThongKeFragment fragment = new ThongKeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_loaithu_sl = view.findViewById(R.id.tv_tong_loaithu);
        tv_khoanthu_sl = view.findViewById(R.id.tv_tong_khoanthu);
        tv_khoanthu_tien = view.findViewById(R.id.tv_tong_khoanthu_tien);
        tv_loaichi_sl = view.findViewById(R.id.tong_loaichi);
        tv_khoanchi_sl = view.findViewById(R.id.tv_khoanchi);
        tv_khoanchi_tien = view.findViewById(R.id.tv_khoanchi_tien);
        tv_tong = view.findViewById(R.id.tv_tong);

        phanLoaiDAO = new PhanLoaiDAO(getContext());
        list_phanloai = phanLoaiDAO.xemPL("thu");
        tv_loaithu_sl.setText(list_phanloai.size()+"");
        list_phanloai = phanLoaiDAO.xemPL("chi");
        tv_loaichi_sl.setText(list_phanloai.size()+"");

        giaoDichDAO = new GiaoDichDAO(getContext());
        list_giaodich = giaoDichDAO.xemGD2("thu");
        tv_khoanthu_sl.setText(list_giaodich.size()+"");
        int tongtienthu = 0;
        for (GiaoDich giaoDich : list_giaodich) {
            tongtienthu += giaoDich.getTien();
        }
        tv_khoanthu_tien.setText(tongtienthu+" VND");

        list_giaodich = giaoDichDAO.xemGD2("chi");
        tv_khoanchi_sl.setText(list_giaodich.size()+"");
        int tongtienchi = 0;
        for (GiaoDich giaoDich : list_giaodich) {
            tongtienchi += giaoDich.getTien();
        }
        tv_khoanchi_tien.setText(tongtienchi+" VND");

        tv_tong.setText((tongtienthu - tongtienchi)+" VND");


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
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }
}