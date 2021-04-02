package com.ex.ws.app;

import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.ex.library.config.WebSocketSetting;
import com.ex.library.service.WebSocketService;
import com.ex.ws.dispatcher.AppResponseDispatcher;
import com.ex.ws.utils.Constant;

/**
 * author Administrator
 * date   2021/4/2 0002
 * version v1.0
 * desc
 */
public class WsApplication extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initWs();
    }

    private void initWs() {
        //配置webSocket必须在服务启动前配置
        WebSocketSetting.setConnectUrl(Constant.WEBSOCKET_URL);
        WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
        WebSocketSetting.setReconnectWithNetworkChanged(true);
        //启动服务
        startService(new Intent(this, WebSocketService.class));
    }
}
