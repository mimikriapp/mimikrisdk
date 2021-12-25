package com.mimikri.ads.sdk.format;

import static com.mimikri.ads.sdk.util.Constant.ADMOB;
import static com.mimikri.ads.sdk.util.Constant.AD_STATUS_ON;
import static com.mimikri.ads.sdk.util.Constant.APPLOVIN;
import static com.mimikri.ads.sdk.util.Constant.NONE;
import static com.mimikri.ads.sdk.util.Constant.UNITY;

import android.app.Activity;
import android.util.Log;

import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.mimikri.ads.sdk.helper.AudienceNetworkInitializeHelper;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;

import java.util.Map;

public class AdNetwork {

    public static class Initialize {

        private static final String TAG = "AdNetwork";
        Activity activity;
        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobAppId = "";
        private String unityGameId = "";
        private String appLovinSdkKey = "";
        private boolean debug = true;

        public Initialize(Activity activity) {
            this.activity = activity;
        }

        public AdNetwork.Initialize build() {
            initAds();
            initBackupAds();
            return this;
        }

        public AdNetwork.Initialize setAdStatus(String adStatus) {
            this.adStatus = adStatus;
            return this;
        }

        public AdNetwork.Initialize setAdNetwork(String adNetwork) {
            this.adNetwork = adNetwork;
            return this;
        }

        public AdNetwork.Initialize setBackupAdNetwork(String backupAdNetwork) {
            this.backupAdNetwork = backupAdNetwork;
            return this;
        }

        public AdNetwork.Initialize setAdMobAppId(String adMobAppId) {
            this.adMobAppId = adMobAppId;
            return this;
        }



        public AdNetwork.Initialize setUnityGameId(String unityGameId) {
            this.unityGameId = unityGameId;
            return this;
        }

        public AdNetwork.Initialize setAppLovinSdkKey(String appLovinSdkKey) {
            this.appLovinSdkKey = appLovinSdkKey;
            return this;
        }


        public AdNetwork.Initialize setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public void initAds() {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (adNetwork) {
                    case ADMOB:
                        MobileAds.initialize(activity, initializationStatus -> {
                            Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                            for (String adapterClass : statusMap.keySet()) {
                                AdapterStatus adapterStatus = statusMap.get(adapterClass);
                                assert adapterStatus != null;
                                Log.d(TAG, String.format("Adapter name: %s, Description: %s, Latency: %d", adapterClass, adapterStatus.getDescription(), adapterStatus.getLatency()));
                            }
                        });
                        AudienceNetworkInitializeHelper.initialize(activity);
                        break;
                    case UNITY:
                        UnityAds.initialize(activity.getApplicationContext(), unityGameId, debug, new IUnityAdsInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                Log.d(TAG, "Unity Ads Initialization Complete with ID : " + unityGameId);
                            }

                            @Override
                            public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
                                Log.d(TAG, "Unity Ads Initialization Failed: [" + error + "] " + message);
                            }
                        });
                        break;
                    case APPLOVIN:
                        AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                        AppLovinSdk.getInstance(activity).initializeSdk(config -> {
                        });
                        AudienceNetworkInitializeHelper.initialize(activity);
                        final String sdkKey = AppLovinSdk.getInstance(activity).getSdkKey();
                        if (!sdkKey.equals(appLovinSdkKey)) {
                            Log.e(TAG, "ERROR : Please update your applovin sdk key in the manifest file.");
                        }
                        break;
                }
                Log.d(TAG, "[" + adNetwork + "] is selected as Primary Ads");
            }
        }

        public void initBackupAds() {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (backupAdNetwork) {
                    case ADMOB:
                        MobileAds.initialize(activity, initializationStatus -> {
                            Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                            for (String adapterClass : statusMap.keySet()) {
                                AdapterStatus adapterStatus = statusMap.get(adapterClass);
                                assert adapterStatus != null;
                                Log.d(TAG, String.format("Adapter name: %s, Description: %s, Latency: %d", adapterClass, adapterStatus.getDescription(), adapterStatus.getLatency()));
                            }
                        });
                        AudienceNetworkInitializeHelper.initialize(activity);
                        break;
                    case UNITY:
                        UnityAds.initialize(activity.getApplicationContext(), unityGameId, debug, new IUnityAdsInitializationListener() {
                            @Override
                            public void onInitializationComplete() {
                                Log.d(TAG, "Unity Ads Initialization Complete with ID : " + unityGameId);
                            }

                            @Override
                            public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
                                Log.d(TAG, "Unity Ads Initialization Failed: [" + error + "] " + message);
                            }
                        });
                        break;
                    case APPLOVIN:
                        AppLovinSdk.getInstance(activity).setMediationProvider(AppLovinMediationProvider.MAX);
                        AppLovinSdk.getInstance(activity).initializeSdk(config -> {
                        });
                        AudienceNetworkInitializeHelper.initialize(activity);
                        final String sdkKey = AppLovinSdk.getInstance(activity).getSdkKey();
                        if (!sdkKey.equals(appLovinSdkKey)) {
                            Log.e(TAG, "ERROR : Please update your applovin sdk key in the manifest file.");
                        }
                        break;

                    case NONE:
                        //do nothing
                        break;
                }
                Log.d(TAG, "[" + backupAdNetwork + "] is selected as Backup Ads");
            }
        }



    }

}
