package com.hungle.pongc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.hungle.pongc.screens.GameScreen;
import com.hungle.pongc.screens.SplashScreen;

public class PongC extends AndroidApplication implements ActionResolver, BillingProcessor.IBillingHandler {

    private static final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhyLBbucOxIeWTV9jGvRlYFQ3kERv+FqUTWTgOtczCkjKwUZ4ONnghXvXZG+DFFoZoqKbAJMN/uS5c5TSvuil+OliHfaChGoDSiWJf+N0kMmpGRK9o9T3saEzdQ52yJMhZucbHAiJ97GnPxqHK03Ts9vfFrntZQ7Q1VSrdnPvCkwo8j0QdbBbh5pV9Fl9VUknB/yMqdNVSNsPDyTYCiYDzrJdYGlPHofS+N8a0WxKjJjmoIOD9q5C4mh6jT6TjwMzg+zmLI83BvaCw/NxsJwYwSAIU/rD6MK7J84dU92256kw+AOue8rI7qGLhBhNtcNkRyWd4c0pfXp1C5FSY5JAxwIDAQAB";
    private static final String TAG = PongC.class.getSimpleName();
    //	private static final String PRODUCT_ID = "game.king.cball.price";
    private static final String PRODUCT_ID = "android.test.purchased";
    public CirclePongGame game;

    private BillingProcessor bp;
    private boolean readyToPurchase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        game = new CirclePongGame(this);
        initialize(game, config);
        if (!BillingProcessor.isIabServiceAvailable(this)) {
            showToast("In-app billing service is unavailable, please upgrade Android Market/Play to version >= 3.9.16");
        }

        bp = BillingProcessor.newBillingProcessor(this, LICENSE_KEY, this);
        bp.initialize();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void viewAd(boolean view) {
        // TODO Auto-generated method stub

    }

    @Override
    public void purchaseIAP() {
        if (!readyToPurchase) {
            showToast("Billing not initialized!");
            return;
        }

        if (bp != null) {
            Log.d(TAG, "billing processor:" + bp);
            bp.purchase(this, PRODUCT_ID);
        }
    }


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        Log.d(TAG, "on product purchased:" + productId);
        Log.d(TAG, "transaction detail:" + details.purchaseInfo.responseData);
        boolean isConsumed = bp.consumePurchase(PRODUCT_ID);
        if (isConsumed) {
            // handle purchase success
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "set screen game screen");
                    game.setScreen(new SplashScreen(game, PongC.this));
                }
            });
        }
    }

    @Override
    public void onPurchaseHistoryRestored() {
        for (String sku : bp.listOwnedProducts())
            Log.d(TAG, "Owned Managed Product: " + sku);
        for (String sku : bp.listOwnedSubscriptions())
            Log.d(TAG, "Owned Subscription: " + sku);
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Log.d(TAG, "onBilling Error:" + errorCode);
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "set screen game screen");
                game.setScreen(new SplashScreen(game, PongC.this));
            }
        });
    }

    @Override
    public void onBillingInitialized() {
        Log.d(TAG, "onBillingInitialized");
        readyToPurchase = true;
    }
}
