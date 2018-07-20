package com.octavian.vEnrich;

import com.octavian.vEnrich.correspondence.OverviewPresenter;
import com.octavian.vEnrich.correspondence.RetrofitBridge;
import org.junit.Test;

public class HODResponseTest {

    private static final String AS_ADVERTISED_ABSOLUTE = "D:\\Repo\\vEnrich\\src\\main\\resources\\images\\samples\\random_text\\raw1.0\\ocr_document.png";
    private static final String AS_ADVERTISED_RELATIVE = "images/samples/random_text/raw1.0/ocr_document.png";

    public HODResponseTest() {
    }

    @Test
    public void responseTest() {
        HODResponseTest.Receiver receiver = new HODResponseTest.Receiver();
        receiver.extractText("D:\\Repo\\vEnrich\\src\\main\\resources\\images\\samples\\random_text\\raw1.0\\ocr_document.png");
        System.out.println("EOF");
    }

    class Receiver implements RetrofitBridge {
        private OverviewPresenter presenter = new OverviewPresenter(this);

        public Receiver() {
        }

        public void receiveText(String text) {
            System.out.println(text);
        }

        public void message(String text) {
            System.out.println(text);
        }

        public void error(Throwable throwable) {
            throwable.printStackTrace();
        }

        public void extractText(String fileName) {
            this.presenter.extractText(fileName);
        }
    }

}
