package com.mimikri.ads.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.mimikri.ads.sdk.format.AdNetwork;
import com.mimikri.ads.sdk.format.BannerAd;
import com.mimikri.ads.sdk.format.InterstitialAd;
import com.mimikri.ads.sdk.format.NativeAd;

public class MainActivity extends AppCompatActivity {

    public static final String AD_STATUS = "1";
    public static final String AD_NETWORK = "applovin";
    public static final String BACKUP_AD_NETWORK = "unity";

    public static final String ADMOB_BANNER_ID = "ca-app-pub-3940256099942544/6300978111";
    public static final String ADMOB_INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712";
    public static final String ADMOB_NATIVE_ID = "ca-app-pub-3940256099942544/2247696110";


    public static final String UNITY_GAME_ID = "4089993";
    public static final String UNITY_BANNER_ID = "banner";
    public static final String UNITY_INTERSTITIAL_ID = "video";

    public static final String APPLOVIN_BANNER_ID = "da17eff31ae69f15";
    public static final String APPLOVIN_INTERSTITIAL_ID = "98f6a586ed642919";
    public static final String APPLOVIN_NATIVE_ID = "f3f42da984bf7464";

    Toolbar toolbar;
    AdNetwork.Initialize adNetwork;
    BannerAd.Builder bannerAd;
    InterstitialAd.Builder interstitialAd;
    NativeAd.Builder nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adNetwork = new AdNetwork.Initialize(this)
                .setAdStatus(AD_STATUS)
                .setAdNetwork(AD_NETWORK)
                .setBackupAdNetwork(BACKUP_AD_NETWORK)
                .setAdMobAppId(null)
                .setUnityGameId(UNITY_GAME_ID)
                .setAppLovinSdkKey(getResources().getString(R.string.applovin_sdk_key))
                .setDebug(false)
                .build();

        bannerAd = new BannerAd.Builder(this)
                .setAdStatus(AD_STATUS)
                .setAdNetwork(AD_NETWORK)
                .setBackupAdNetwork(BACKUP_AD_NETWORK)
                .setAdMobBannerId(ADMOB_BANNER_ID)
                .setUnityBannerId(UNITY_BANNER_ID)
                .setAppLovinBannerId(APPLOVIN_BANNER_ID)
                .setDarkTheme(false)
                .build();

        interstitialAd = new InterstitialAd.Builder(this)
                .setAdStatus(AD_STATUS)
                .setAdNetwork(AD_NETWORK)
                .setBackupAdNetwork(BACKUP_AD_NETWORK)
                .setAdMobInterstitialId(ADMOB_INTERSTITIAL_ID)
                .setUnityInterstitialId(UNITY_INTERSTITIAL_ID)
                .setAppLovinInterstitialId(APPLOVIN_INTERSTITIAL_ID)
                .setInterval(3)
                .build();

        findViewById(R.id.btn_interstitial).setOnClickListener(v -> interstitialAd.show());

        nativeAd = new NativeAd.Builder(this)
                .setAdStatus(AD_STATUS)
                .setAdNetwork(AD_NETWORK)
                .setBackupAdNetwork(BACKUP_AD_NETWORK)
                .setAdMobNativeId(ADMOB_NATIVE_ID)
                //.setAppLovinNativeId(APPLOVIN_NATIVE_ID)
                .setDarkTheme(false)
                .build();

    }

}