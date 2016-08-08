package com.addantibes.addantibes;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fifi on 19/07/16.
 */
public class class_audio extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        List<String> Titre = new ArrayList<String>();
        List<String> Lien = new ArrayList<String>();
        List<String> Thumbnail = new ArrayList<String>();

        try {
            URL url = new URL("http://addantibes.com/archives/category/audio/feed");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            // We will get the XML from an input stream
            xpp.setInput( getInputStream(url), "UTF_8");

            boolean insideItem = false;

            // Returns the type of current event: START_TAG, END_TAG, etc..
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem)
                           Titre.add(xpp.nextText());  //extract the headline
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (insideItem)
                            Lien.add(xpp.nextText());  //extract the link of article
                    } else if (xpp.getName().equalsIgnoreCase("post-thumbnail")) {
                        Thumbnail.add( String.valueOf( xpp.nextText()));   //extract url
                    }

                }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                    insideItem=false;
                }
                // On ajoute à la liste
                eventType = xpp.next(); //move to next element
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] Array_Titre = new String[Titre.size()];
        Array_Titre = Titre.toArray(Array_Titre);

        String[] Array_Lien = new String[Lien.size()];
        Array_Lien = Lien.toArray(Array_Lien);

        String[] Array_Thumbnail = new String[Thumbnail.size()];
        Array_Thumbnail = Thumbnail.toArray(Array_Thumbnail);

        myView = inflater.inflate(R.layout.layout_ecrit, container, false);
        audio_adapter adapter = new audio_adapter(getActivity(), Array_Titre, Array_Thumbnail);
        ListView lv = (ListView)myView.findViewById(R.id.list);
        lv.setAdapter(adapter);

        final String[] finalArray_Lien = Array_Lien;
        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = finalArray_Lien[i];
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                startActivity(intent);
            }
        });

        return myView;
    }

    private InputStream getInputStream(URL url) throws IOException {
        return url.openConnection().getInputStream();
    }
}
