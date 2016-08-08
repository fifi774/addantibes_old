package com.addantibes.addantibes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fifi on 21/07/16.
 */
public class accueil_adapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    public View rowView;

    public accueil_adapter(Context context, String[] values) {
        super(context, R.layout.adapter_accueil, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        rowView = inflater.inflate(R.layout.base_adapter, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.Titre);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        imageView.setImageResource(R.drawable.pasimage);
        textView.setText(values[position]);
        return rowView;
    }


}
