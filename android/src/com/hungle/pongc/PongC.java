package com.hungle.pongc;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import android.os.Bundle;

public class PongC extends AndroidApplication implements ActionResolver{
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new CirclePongGame(this), config);
	}

	@Override
	public void showOrLoadInterstital() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void signIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void signOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitScore(long score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showScores() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSignedIn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shareGame(String msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlockAchievementGPGS(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAchievement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitGamesPlayed(long score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAd(boolean view) {
		// TODO Auto-generated method stub
		
	}
}
