package org.trustfuse.expenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomAdapter extends BaseAdapter {

    String[] spinnerTitles;
    int[] spinnerImages;
    Context mContext;
    LayoutInflater inflater;
    public CustomAdapter(@NonNull Context context, String[] titles, int[] images) {
        this.spinnerTitles = titles;
        this.spinnerImages = images;
        this.mContext = context;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return spinnerImages.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.single_spinner_item, null);
        ImageView icon = view.findViewById(R.id.image_view_spinner);
        TextView names = (TextView) view.findViewById(R.id.text_view_spinner);
        icon.setImageResource(spinnerImages[i]);
        names.setText(spinnerTitles[i]);
        return view;
    }
}
