package com.addantibes.addantibes;

import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by fifi on 19/07/16.
 */
public class class_annonces extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // myView = inflater.inflate(R.layout.layout_evenements, container, false);
        // Initializing instance variables
        ArrayList headlines = new ArrayList();
        ArrayList links = new ArrayList();

        try {
            URL url = new URL("http://addantibes.com/archives/category/annonces_eglise/feed");

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
                    } else if (xpp.getName().equalsIgnoreCase("content:encoded")) {
                        if (insideItem)
                            if (headlines.size() == 0 ) { headlines.add( Html.fromHtml(xpp.nextText())); }  //extract the headline
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (insideItem)
                            if (links.size() == 0 ) {links.add(xpp.nextText()); }  //extract the link of article
                    }
                }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                    insideItem=false;
                }
                eventType = xpp.next(); //move to next element
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        myView = inflater.inflate(R.layout.layout_annonces, container, false);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, headlines);
        ListView lv = (ListView)myView.findViewById(R.id.list);
        lv.setAdapter(adapter);

        return myView;
    }

    private InputStream getInputStream(URL url) throws IOException {
        return url.openConnection().getInputStream();
    }
}
