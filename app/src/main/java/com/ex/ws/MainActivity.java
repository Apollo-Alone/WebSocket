package com.ex.ws;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.ex.library.bean.ErrorResponse;
import com.ex.library.bean.Response;
import com.ex.library.view.WebSocketActivity;

public class MainActivity extends WebSocketActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        findViewById(R.id.bt_send).setOnClickListener(view -> {
            sendMessage();
        });

    }

    private void sendMessage() {
        JSONObject command = new JSONObject();
        command.put("tradePair", "ETH_BTC");
        command.put("topic", "take_list_buy");
        command.put("action", "refresh");
        sendText(command.toJSONString());
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMessageResponse(Response message) {
        LogUtils.dTag(TAG, "onMessageResponse" + message.toString());
    }

    @Override
    public void onSendMessageError(ErrorResponse error) {
        LogUtils.dTag(TAG, "onMessageResponse" + error.toString());
    }
}