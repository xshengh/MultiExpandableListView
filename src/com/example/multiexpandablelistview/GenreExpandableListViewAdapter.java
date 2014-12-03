package com.example.multiexpandablelistview;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class GenreExpandableListViewAdapter extends BaseExpandableListAdapter{
	
	private Context mContext;
	private String mGenre = "";
	private HashMap<String,ArrayList<String>> mStudiosMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String,ArrayList<String>> mMoviesMap = new HashMap<String,ArrayList<String>>();
	
	public GenreExpandableListViewAdapter( Context context, 
										   String genre, 
										   HashMap<String, ArrayList<String>> studiosMap, 
										   HashMap<String, ArrayList<String>> moviesMap){
		
		mContext = context;
		mGenre = genre;
		mStudiosMap = studiosMap;
		mMoviesMap = moviesMap;
	}
	
	@Override
	public int getGroupCount() {
		return 1;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mStudiosMap.get(mGenre).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mGenre;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mStudiosMap.get(mGenre).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView( int groupPosition, 
							  boolean isExpanded,
							  View convertView, 
							  ViewGroup parent ) {
		
		TextView view = (TextView) convertView;
		
		if(view==null){
			
			view = new TextView(mContext);
			view.setText(mGenre);
			view.setPadding(100, 15, 15, 15);
			
		}
		
		return view;
	}

	@Override
	public View getChildView( int groupPosition, 
							  int childPosition,
							  boolean isLastChild, 
							  View convertView, 
							  ViewGroup parent) {
		
		
		String studio = (String) getChild(groupPosition, childPosition);
		ArrayList<String> movies = mMoviesMap.get(studio);
		
		CustomExpandableListView view = (CustomExpandableListView) convertView;
		
		if(view==null){
			
			view = new CustomExpandableListView(mContext);
			MoviesExpandableListViewAdapter adapter = new MoviesExpandableListViewAdapter(mContext,studio,movies);
			view.setAdapter(adapter);
			
		}
		
		return view;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
}
