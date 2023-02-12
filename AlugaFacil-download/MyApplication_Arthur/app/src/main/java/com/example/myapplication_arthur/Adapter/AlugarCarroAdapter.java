package com.example.myapplication_arthur.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.myapplication_arthur.Model.Carro;

import java.util.List;

public class AlugarCarroAdapter extends BaseAdapter {

    private Context context;
    private List<Carro> carros;


    public AlugarCarroAdapter(Context context, List<Carro> carros) {
        super();
        this.context = context;
        this.carros = carros;
    }

    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int position) {
        return carros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

       //aula4, alunoadapter


        return null;
    }
}
