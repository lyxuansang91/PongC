package com.hungle.pongc;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class IOSLauncher extends IOSApplication.Delegate implements ActionResolver{
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new CirclePongGame(this), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
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