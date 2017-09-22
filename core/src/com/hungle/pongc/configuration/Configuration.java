package com.hungle.pongc.configuration;

public final class Configuration {

    //GAME NAME
    public static String gameName = "Pong C";

    //ADMOB IDS
    public static final String AD_UNIT_ID_BANNER = "ca-app-pub-6147578034437241/9683740610";
    public static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-6147578034437241/2160473811";
    public static final float AD_FREQUENCY = 0.5f; //Number between 0 and 1

    //LEADERBOARDS
    public static final String LEADERBOARD_HIGHSCORE = "CgkI9IWt9LwFEAIQAQ";
    public static final String LEADERBOARD_GAMESPLAYED = "CgkIiOzr5vYaEAIQAQ";


    //ACHIEVEMENTS
    public static final String SCORE_10 = "CgkI9IWt9LwFEAIQAQ";
    public static final String SCORE_25 = "CgkI9IWt9LwFEAIQAg";
    public static final String SCORE_50 = "CgkIiOzr5vYaEAIQBA";
    public static final String SCORE_75= "CgkIiOzr5vYaEAIQBQ";
    public static final String SCORE_100 = "CgkIiOzr5vYaEAIQBg";

    public static final String GAMESPLAYED_10 = "CgkIiOzr5vYaEAIQCw";
    public static final String GAMESPLAYED_25 = "CgkIiOzr5vYaEAIQDA";
    public static final String GAMESPLAYED_50 = "CgkIiOzr5vYaEAIQDQ";
    public static final String GAMESPLAYED_75= "CgkIiOzr5vYaEAIQDg";
    public static final String GAMESPLAYED_100 = "CgkIiOzr5vYaEAIQDw";


    //BALL HAS TAIL
    public static boolean tail = true;

    //VELOCITY ADJUSTMENT
    public static float VELOCITY_OVER_0 = 1.75f;
    public static float VELOCITY_OVER_20 = 1.85f;
    public static float VELOCITY_OVER_50 = 1.95f;
    public static float VELOCITY_OVER_75 = 2.0f;
    public static float VELOCITY_OVER_100 = 2.1f;
}
