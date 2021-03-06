package com.ex.library.dispatcher;

import com.ex.library.bean.ErrorResponse;
import com.ex.library.bean.Response;
import com.ex.library.listener.ResponseDelivery;

/**
 * Author:  Jerry
 * Date:    2018/10/9
 * Version   v1.0
 * Desc:  用于处理及分发接收到的消息的接口，
 * 如果需要自定义事件的分发，实现这个类并设置到{@link WebSocketSetting} 中即可。
 */
@SuppressWarnings("all")
public interface IResponseDispatcher {
    /**
     * 连接成功
     */
    void onConnected(ResponseDelivery delivery);

    /**
     * 连接失败
     *
     * @param cause 失败原因
     */
    void onConnectError(Throwable cause, ResponseDelivery delivery);

    /**
     * 连接断开
     */
    void onDisconnected(ResponseDelivery delivery);

    /**
     * 接收到消息
     *
     * @param message  接收到的消息
     * @param delivery 消息发射器
     */
    void onMessageResponse(Response message, ResponseDelivery delivery);

    /**
     * 消息发送失败或接受到错误消息等等
     */
    void onSendMessageError(ErrorResponse error, ResponseDelivery delivery);
}
