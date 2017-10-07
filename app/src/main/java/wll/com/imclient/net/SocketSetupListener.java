package wll.com.imclient.net;

import java.net.Socket;

public interface SocketSetupListener {

    void onSetUpSuccess(Socket socket);

    void onSetUpFailed();
}
