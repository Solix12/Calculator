package com.salikh.calculator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;

import java.util.Arrays;

public class ThemeActivity extends BaseActivity {

    private static final String TAG = "REWARDS_INTER_TAG";
    private CardView themeGray, themePing, themeBlue;
    private LinearLayout bacBtn;
    private ConstraintLayout mainBack, container;
    private AdView mAdView;
    private RewardedInterstitialAd mRewardedInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        loadView();
        setListeners();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("TEST_DEVICE_ID1_HERE", "TEST_DEVICE_ID2_HERE")).build()
        );

        loadRewardedInterstitialAd();

        loadAdBanner();

    }

    private void loadAdBanner() {
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-3259174483175600/8363920617");


        mAdView = findViewById(R.id.ad_view1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


    private void setListeners() {

        themeGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelpr().setThemeBack(Color.parseColor("#4f5b66"));
                MemoryHelper.getHelpr().setThemeCard(Color.parseColor("#65737e"));
                MemoryHelper.getHelpr().setThemeText(Color.parseColor("#343d46"));
                setThemeData();
                loadAdShowRewardedInterstitialAd();
            }
        });

        themePing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelpr().setThemeBack(Color.parseColor("#f6abb6"));
                MemoryHelper.getHelpr().setThemeCard(Color.parseColor("#f4b6c2"));
                MemoryHelper.getHelpr().setThemeText(Color.parseColor("#ff77aa"));
                setThemeData();
                loadAdShowRewardedInterstitialAd();
            }
        });

        themeBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelpr().setThemeBack(Color.parseColor("#4a91f2"));
                MemoryHelper.getHelpr().setThemeCard(Color.parseColor("#64a1f4"));
                MemoryHelper.getHelpr().setThemeText(Color.parseColor("#3b7dd8"));
                setThemeData();
                loadAdShowRewardedInterstitialAd();
            }
        });

        bacBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeActivity.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(ThemeActivity.this);
                finish();
            }
        });

    }

    private void loadView() {
        themeGray = findViewById(R.id.gray_theme);
        themePing = findViewById(R.id.ping_theme);
        themeBlue = findViewById(R.id.blue_theme);
        mainBack = findViewById(R.id.main_setting_back);
        container = findViewById(R.id.container);
        bacBtn = findViewById(R.id.back_view);

    }

    @Override
    public void setThemeData() {
        mainBack.setBackgroundColor(MemoryHelper.getHelpr().getThemeBack());
        container.setBackgroundColor(MemoryHelper.getHelpr().getThemeCard());
        getWindow().setStatusBarColor(MemoryHelper.getHelpr().getThemeBack());
        getWindow().setNavigationBarColor(MemoryHelper.getHelpr().getThemeBack());
        bacBtn.setBackgroundColor(MemoryHelper.getHelpr().getThemeBack());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ThemeActivity.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateZoom(this);
        finish();
        super.onBackPressed();
    }

    private void loadRewardedInterstitialAd() {
        RewardedInterstitialAd.load(this, "ca-app-pub-3259174483175600/6692364849", new AdRequest.Builder().build(), new RewardedInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
                super.onAdLoaded(rewardedInterstitialAd);
                Log.d(TAG, "onAdLoaded: ");
                mRewardedInterstitialAd = rewardedInterstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mRewardedInterstitialAd = null;
            }
        });
    }

    private void loadAdShowRewardedInterstitialAd() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Loading Reward...");
        progressDialog.show();


        RewardedInterstitialAd.load(this, "ca-app-pub-3259174483175600/6692364849", new AdRequest.Builder().build(), new RewardedInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
                super.onAdLoaded(rewardedInterstitialAd);
                Log.d(TAG, "onAdLoaded: ");
                mRewardedInterstitialAd = rewardedInterstitialAd;
                progressDialog.dismiss();
                showRewardInterstitialAd();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mRewardedInterstitialAd = null;
                progressDialog.dismiss();
                Toast.makeText(ThemeActivity.this, "Ad not loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showRewardInterstitialAd() {
        if (mRewardedInterstitialAd != null) {
            Log.d(TAG, "showRewardInterstitialAd: Ad was loaded");
            mRewardedInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);

                    Log.d(TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                    mRewardedInterstitialAd = null;
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                    Log.d(TAG, "onAdShowedFullScreenContent: ");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    Log.d(TAG, "onAdDismissedFullScreenContent: ");
                    mRewardedInterstitialAd = null;
                    loadRewardedInterstitialAd();
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();

                    Log.d(TAG, "onAdImpression: ");

                }

                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                    Log.d(TAG, "onAdClicked: ");
                }
            });


            mRewardedInterstitialAd.show(this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Toast.makeText(ThemeActivity.this, "Reward earned...", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onUserEarnedReward: ");
                }
            });
        }
    }

}