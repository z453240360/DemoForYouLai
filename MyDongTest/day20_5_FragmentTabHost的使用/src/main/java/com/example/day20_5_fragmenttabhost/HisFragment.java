package com.example.day20_5_fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class HisFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		String str = getArguments().getString("data");
		Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();	
		return inflater.inflate(R.layout.f3, container,false);
	}
}
