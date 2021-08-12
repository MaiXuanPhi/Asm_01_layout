package com.example.asm_01_layout.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.asm_01_layout.fragment_con.KhoanThuFragment;
import com.example.asm_01_layout.fragment_con.LoaiThuFragment;

public class ThuViewPager extends FragmentStatePagerAdapter {

    int behavior;

    public ThuViewPager(FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.behavior = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new LoaiThuFragment();
            case 1: return new KhoanThuFragment();
            default: return new LoaiThuFragment();
        }
    }

    @Override
    public int getCount() {
        return behavior;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0: title = "Loại thu"; break;
            case 1: title = "Khoản thu"; break;
        }
        return title;
    }
}
