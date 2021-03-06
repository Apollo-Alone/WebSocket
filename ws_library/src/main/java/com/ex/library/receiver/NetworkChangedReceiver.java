package com.ex.library.receiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import com.ex.library.service.WebSocketService;


/**
 * Author:  Jerry
 * Date:    2018/10/9
 * Version   v1.0
 * Desc:   监听网络变化广播，网络变化时自动重连
 */
@SuppressWarnings("all")
public class NetworkChangedReceiver extends BroadcastReceiver {
    private static final String LOGTAG = "NetworkChangedReceiver";

    private WebSocketService mSocketService;

    public NetworkChangedReceiver() {
    }

    public NetworkChangedReceiver(WebSocketService socketService) {
        this.mSocketService = socketService;
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager == null) return;
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.isConnected()) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        Log.i(LOGTAG, "网络连接发生变化，当前WiFi连接可用，正在尝试重连。");
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        Log.i(LOGTAG, "网络连接发生变化，当前移动连接可用，正在尝试重连。");
                    }
                    if (mSocketService != null) {
                        mSocketService.reconnect();
                    }
                } else {
                    Log.i(LOGTAG, "当前没有可用网络");
                }
            }
        }
    }
}
