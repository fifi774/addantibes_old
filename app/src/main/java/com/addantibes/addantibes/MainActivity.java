package com.addantibes.addantibes;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
     implements NavigationView.OnNavigationItemSelectedListener {
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Alarme deuxieme culte
        Calendar c = Calendar.getInstance();
        Date temp = c.getTime();
        Calendar calendar = Calendar.getInstance();

        //calendar.set(Calendar.DAY_OF_WEEK, 1); // Que le dimanche
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM,Calendar.AM);
        Date pm = calendar.getTime();

        // Alarme réunion dimance apres midi
        Calendar c2 = Calendar.getInstance();
        Date temp2 = c2.getTime();
        Calendar calendar2 = Calendar.getInstance();

        //calendar2.set(Calendar.DAY_OF_WEEK, 1); // Que le dimanche
        calendar2.set(Calendar.HOUR_OF_DAY, 15);
        calendar2.set(Calendar.MINUTE, 00);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.AM_PM,Calendar.PM);
        Date pm2 = calendar2.getTime();

        // Alarme réunion mardi apres midi
        Calendar c3 = Calendar.getInstance();
        Date temp3 = c3.getTime();
        Calendar calendar3 = Calendar.getInstance();

        //calendar2.set(Calendar.DAY_OF_WEEK, 3); // Que le mardi
        calendar3.set(Calendar.HOUR_OF_DAY, 18);
        calendar3.set(Calendar.MINUTE, 45);
        calendar3.set(Calendar.SECOND, 0);
        calendar3.set(Calendar.AM_PM,Calendar.PM);
        Date pm3 = calendar3.getTime();


        if(pm.after(temp))
        {
            Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }

        if(pm2.after(temp))
        {
            Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }

        if(pm3.after(temp))
        {
            Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }

        // On affiche la listeview_accueil
        final ListView listview = (ListView) findViewById(R.id.list_accueil);
        String[] values = new String[] { "Nos réunions", "Venir à l'église", "Annonces de l'église", "Evènements", "Messages vidéos",
                                            "Messages audio", "Messages écrits"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                if (position == 0) // Nos réunions
                {
                    HideAll ();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, new class_reunion())
                            .commit();
                }
                else if (position == 1) // Venir à l'église
                {
                    HideAll ();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, new class_venir())
                            .commit();
                }
                else if (position == 2) // Annnonces
                {
                    HideAll ();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, new class_annonces())
                            .commit();
                }
                else if (position == 3) // Evènements
                {
                    HideAll ();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, new class_evenements())
                            .commit();
                }
                else if (position == 4) // Messages video
                {
                    HideAll ();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, new class_video())
                            .commit();
                }
                else if (position == 5) // Messages audio
                {
                    HideAll ();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, new class_audio())
                            .commit();
                }
                else if (position == 6) // Messages écrits
                {
                    HideAll ();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, new class_ecrit())
                            .commit();
                }

            }
        }); // Fin listview

    } //end on


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        HideAll ();
        if (id == R.id.nav_accueil) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_accueil())
                    .commit();
            VisibleAll ();

        }
        else if (id == R.id.nav_reunions) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_reunion())
                    .commit();

        } else if (id == R.id.nav_venir) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_venir())
                    .commit();

        } else if (id == R.id.nav_annonces) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_annonces())
                    .commit();

        } else if (id == R.id.nav_evenements) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_evenements())
                    .commit();

        } else if (id == R.id.nav_video) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_video())
                    .commit();


        } else if (id == R.id.nav_audio) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_audio())
                    .commit();


        } else if (id == R.id.nav_ecrit) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new class_ecrit())
                    .commit();


        } else if (id == R.id.nav_appeler) {
            // Nous appeller
            Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0781177721"));
            startActivity(appel);

        } else if (id == R.id.nav_mail) {
            // Envoyer un mail
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","fifi774@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message depuis l'application mobile");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Votre message");
            startActivity(Intent.createChooser(emailIntent, "Envoyer un mail avec"));

        }else if (id == R.id.nav_site) {
            // Visitez le site
            String url = "http://www.addantibes.com";
            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
            startActivity(intent);

        }else if (id == R.id.nav_youtube) {
        // Visitez le site
        String url = "https://www.youtube.com/user/addantibes";
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
        startActivity(intent);

        }else if (id == R.id.nav_facebook) {
        // Visitez le site
        String url = "https://www.facebook.com/Eglise-Evang%C3%A9lique-Add-Antibes-423206644513009/?ref=ts&fref=ts";
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
        startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    public void HideAll () {
        ListView listview = (ListView) findViewById(R.id.list_accueil);
        listview.setVisibility(View.INVISIBLE);
    }

    public void VisibleAll () {
        ListView listview = (ListView) findViewById(R.id.list_accueil);
        listview.setVisibility(View.VISIBLE);
    }

}
