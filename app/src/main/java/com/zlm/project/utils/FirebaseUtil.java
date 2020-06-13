package com.zlm.project.utils;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * @author Milla
 * @create 2019-05-02
 */
public final class FirebaseUtil {

    private static final FirebaseUtil firebaseUtil = new FirebaseUtil();
    private static FirebaseAnalytics mFirebaseAnalytics;

    // -------------------------------------------
    private FirebaseUtil() {

    }

    // -------------------------------------------
    public static FirebaseUtil getInstance() {
        return firebaseUtil;
    }

    // -------------------------------------------
    public void init(Context context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    // -------------------------------------------

    /**
     * Send event to firebase server.
     */
    private static void sendEvent(String eventName, Bundle bundle) {
        mFirebaseAnalytics.logEvent(eventName, bundle);
    }

    // -------------------------------------------

    /**
     * Login
     *
     * @param method login type.
     */
    public static void sendLoginType(String method) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, method);
        sendEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }

    // -------------------------------------------

    /**
     * Sign Up
     *
     * @param method login type.
     */
    public static void sendSignUpType(String method) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, method);
        sendEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }

    // -------------------------------------------

    /**
     * Channel
     *
     * @param channelId channel id.
     */
    public static void sendChannelId(String channelId) {
        Bundle bundle = new Bundle();
        bundle.putString("Name", channelId);
        sendEvent("Channel", bundle);
    }

    // -------------------------------------------

    /**
     * Tab bar
     *
     * @param itemName name
     */
    public static void sendNaviBar(String itemName) {
        Bundle bundle = new Bundle();
        bundle.putString("Name", itemName);
        sendEvent("NaviBar", bundle);
    }

    // -------------------------------------------
}
