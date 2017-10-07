package wll.com.imclient.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import wll.com.imclient.rx.BaseSubscriber;
import wll.com.imclient.rx.RxHelper;

public class SocketWrapper {
    public static Socket setUpSocket() throws Exception {
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(Configs.SERVER_IP), Configs.SERVER_PORT);
        socket.connect(socketAddress, Configs.TIMEOUT);
        return socket;
    }

    public static String doRequest(final Socket socket, final String msg) throws Exception {
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF(msg);
        return input.readUTF();
    }

    public static void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
