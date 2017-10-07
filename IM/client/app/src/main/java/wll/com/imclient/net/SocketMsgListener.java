package wll.com.imclient.net;

/**
 * Created by wanglilin on 2017/9/10.
 *
 * @see
 */

public interface SocketMsgListener {
    void onSendSuccess(String msg);

    void onSendFailed();
}
