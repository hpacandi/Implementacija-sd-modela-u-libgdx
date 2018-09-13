package com.mygdx.test;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		TestGameFragment fragment = new TestGameFragment();
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.replace(android.R.id.content, fragment);
		trans.commit();
	}


	@Override
	public void exit() {}


}
