package com.example.uas.Memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uas.R;

import java.util.ArrayList;

public class MahasiswaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Komentar> komenList = new ArrayList<>();

    public void setMahasiswaList(ArrayList<Komentar> mahasiswaList) {
        this.komenList = mahasiswaList;
    }

    public MahasiswaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return komenList.size();
    }

    @Override
    public Object getItem(int i) {
        return komenList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_data, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        Komentar data = (Komentar) getItem(i);
        viewHolder.bind(data);
        return itemView;
    }

    private class ViewHolder {
        private TextView txtKomen, txtName;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_nama);
            txtKomen = view.findViewById(R.id.txt_nim);
        }

        void bind(Komentar data) {
            txtName.setText(data.getNama());
            txtKomen.setText(data.getKomen());
        }
    }
}
