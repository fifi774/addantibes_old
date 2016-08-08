package com.addantibes.addantibes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by fifi on 21/07/16.
 */
public class evenement_adapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    private final String[] image;
    public View rowView;

    public evenement_adapter(Context context, String[] values, String [] image) {
        super(context, R.layout.base_adapter, values);
        this.context = context;
        this.values = values;
        this.image = image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = inflater.inflate(R.layout.base_adapter, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.Titre);
        if (image [position] != "")
        {
            new DownloadImageTask((ImageView) rowView.findViewById(R.id.icon))
                    .execute(image [position]);
        }
        else
        {
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            imageView.setImageResource(R.drawable.pasimage);
        }

        textView.setText(values[position]);

        return rowView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
