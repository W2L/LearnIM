package wll.com.imclient.net;

import java.net.Socket;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import wll.com.imclient.rx.BaseSubscriber;
import wll.com.imclient.rx.RxHelper;

public class SocketUtils {
    public static void setUpSocket(BaseSubscriber subscriber) {
        Observable.create(new ObservableOnSubscribe<Socket>() {
            @Override
            public void subscribe(ObservableEmitter<Socket> e) throws Exception {
                e.onNext(SocketWrapper.setUpSocket());
            }
        }).compose(RxHelper.io_main()).subscribeWith(subscriber);
    }

    public static void doRequest(final Socket socket, final String msg, BaseSubscriber subscriber) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(SocketWrapper.doRequest(socket, msg));
            }
        }).compose(RxHelper.io_main()).subscribeWith(subscriber);
    }

    public static void closeSocket(Socket socket) {
        SocketWrapper.closeSocket(socket);
    }
}