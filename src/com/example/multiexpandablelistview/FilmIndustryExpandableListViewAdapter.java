package com.example.multiexpandablelistview;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class FilmIndustryExpandableListViewAdapter extends BaseExpandableListAdapter{
	
	private Context mContext;
	private ArrayList<String> mFilmIndustries = new ArrayList<String>();
	private HashMap<String,ArrayList<String>> mFilmGenreMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> mMoviesMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> mDevStudiosMap = new HashMap<String,ArrayList<String>>();
	
	public FilmIndustryExpandableListViewAdapter( Context context, 
												  ArrayList<String> filmIndustries, 
												  HashMap<String,ArrayList<String>> filmGenreMap, 
												  HashMap<String, ArrayList<String>> devStudiosMap, 
												  HashMap<String, ArrayList<String>> moviesMap){
		mContext = context;
		mFilmIndustries = filmIndustries;
		mFilmGenreMap = filmGenreMap;
		mDevStudiosMap = devStudiosMap;
		mMoviesMap = moviesMap;
	}

	@Override
	public int getGroupCount() {
		return mFilmIndustries.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mFilmGenreMap.get(mFilmIndustries.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mFilmIndustries.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mFilmGenreMap.get(mFilmIndustries.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView( int groupPosition, 
							  boolean isExpanded,
							  View convertView, 
							  ViewGroup parent) {
		
		TextView view = (TextView) convertView;
		String groupText = (String) getGroup(groupPosition);
		
		if(view==null){
			
			view = new TextView(mContext);
			view.setText(groupText);
			view.setPadding(20, 20, 20, 20);
			
		}
		
		return view;
	}

	@Override
	public View getChildView( int groupPosition, 
							  int childPosition,
							  boolean isLastChild, 
							  View convertView, 
							  ViewGroup parent) {
		
		CustomExpandableListView view = (CustomExpandableListView) convertView;
		String genre = (String) getChild(groupPosition, childPosition);
		
		if(view==null){
			
			view = new CustomExpandableListView(mContext);
			GenreExpandableListViewAdapter gelva = new GenreExpandableListViewAdapter(mContext,genre,mDevStudiosMap,mMoviesMap);
			view.setAdapter(gelva);
			
		}
		
		return view;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
