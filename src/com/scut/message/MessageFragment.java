package com.scut.message;





import itat.zttc.login.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageFragment extends Fragment 
{
	 View view;
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		    view=inflater.inflate(R.layout.message, container, false);
		    
		    
		    
	        return view;
	 } 
	
}
