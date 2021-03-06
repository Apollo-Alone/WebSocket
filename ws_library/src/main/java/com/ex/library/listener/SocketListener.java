package com.ex.library.listener;


import com.ex.library.bean.ErrorResponse;
import com.ex.library.bean.Response;

/**
 * Author:  Jerry
 * Date:    2018/10/9
 * Version   v1.0
 * Desc:    WebSocket监听器
 */
@SuppressWarnings("all")
public interface SocketListener {
    /**
     * 连接成功
     */
    void onConnected();

    /**
     * 连接失败
     *
     * @param cause 失败原因
     */
    void onConnectError(Throwable cause);

    /**
     * 连接断开
     */
    void onDisconnected();

    /**
     * 接收到消息
     */
    void onMessageResponse(Response message);

    /**
     * 消息发送失败或接受到错误消息等等
     */
    void onSendMessageError(ErrorResponse error);
}
