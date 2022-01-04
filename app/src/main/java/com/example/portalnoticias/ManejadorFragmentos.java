package com.example.portalnoticias;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ManejadorFragmentos extends FragmentPagerAdapter {

    int contador_pestanas;

    public ManejadorFragmentos(FragmentManager fm, int behavior){
        super(fm,behavior);
        contador_pestanas = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new Todas();
            case 1:
                return new Nacional();
            case 2:
                return new Economia();
            case 3:
                return new Deportes();
            case 4:
                return new Tecnologia();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return contador_pestanas;
    }

}
