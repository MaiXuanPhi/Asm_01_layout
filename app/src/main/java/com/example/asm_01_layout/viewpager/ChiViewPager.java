package com.example.asm_01_layout.viewpager;

import android.icu.text.CaseMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.asm_01_layout.fragment_con.KhoanChiFragment;
import com.example.asm_01_layout.fragment_con.LoaiChiFragment;

public class ChiViewPager extends FragmentStatePagerAdapter {

    int behavior;

    public ChiViewPager(FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.behavior = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new LoaiChiFragment();
            case 1: return new KhoanChiFragment();
            default: return new LoaiChiFragment();
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
            case 0: title = "Loại Chi"; break;
            case 1: title = "Khoản Chi"; break;
        }
        return title;
    }

}
