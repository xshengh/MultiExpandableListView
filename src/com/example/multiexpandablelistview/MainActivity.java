package com.example.multiexpandablelistview;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;


public class MainActivity extends Activity {
	
	private ArrayList<String> mFilmIndustries = new ArrayList<String>();
	private HashMap<String,ArrayList<String>> mFilmGenreMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> mDevStudiosMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> mMoviesMap = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> mAmericanStudios = new ArrayList<String>();
	
	private ExpandableListView elv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //film industries
        mFilmIndustries.add("Filipino");
        mFilmIndustries.add("Hollywood");
        
        //genre
        ArrayList<String> genre = new ArrayList<String>();
        genre.add("Action");
        genre.add("Drama");
        genre.add("Sci-fi");
        genre.add("Comedy");
        mFilmGenreMap.put("Filipino", genre);
        mFilmGenreMap.put("Hollywood", genre);
        
        //studios
        mAmericanStudios.add("Warner Brothers");
        mAmericanStudios.add("Fox");
        mAmericanStudios.add("New Line Cinema");
        mAmericanStudios.add("Paramount Pictures");
        mAmericanStudios.add("Universal Studios");
        mAmericanStudios.add("Metro-Goldwyn");
        mDevStudiosMap.put("Action", mAmericanStudios);
        mDevStudiosMap.put("Drama", mAmericanStudios);
        mDevStudiosMap.put("Sci-fi", mAmericanStudios);
        mDevStudiosMap.put("Comedy", mAmericanStudios);
        
        ArrayList<String> movies = new ArrayList<String>();
        movies.add("Movie-1");
        movies.add("Movie-2");
        movies.add("Movie-3");
        movies.add("Movie-4");
        movies.add("Movie-5");
        
        mMoviesMap.put("Warner Brothers",movies);
        mMoviesMap.put("Fox",movies);
        mMoviesMap.put("New Line Cinema",movies);
        mMoviesMap.put("Paramount Pictures",movies);
        mMoviesMap.put("Universal Studios",movies);
        mMoviesMap.put("Metro-Goldwyn",movies);
        
        elv = (ExpandableListView)findViewById(R.id.expandable_listview);
        FilmIndustryExpandableListViewAdapter adapter = new FilmIndustryExpandableListViewAdapter( this,
        																						   mFilmIndustries, 
        																						   mFilmGenreMap,
        																						   mDevStudiosMap,
        																						   mMoviesMap);
        elv.setAdapter(adapter);
        
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
