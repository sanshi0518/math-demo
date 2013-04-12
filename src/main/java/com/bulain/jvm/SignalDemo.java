package com.bulain.jvm;

import java.io.IOException;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class SignalDemo {
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] signals = new String[]{"INT", "TERM", "SEGV", "ILL", "ABRT"};

        for (String signal : signals) {
            try {
                DebugSignalHandler.install(signal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("This a signal demo.");

        while (true) {
            Thread.currentThread().sleep(1000 * 60);
        }

    }
}

class DebugSignalHandler implements SignalHandler {

    private SignalHandler oldHandler;

    public static DebugSignalHandler install(String signalName) {
        Signal diagSignal = new Signal(signalName);
        DebugSignalHandler diagHandler = new DebugSignalHandler();
        diagHandler.oldHandler = Signal.handle(diagSignal, diagHandler);
        return diagHandler;
    }

    public void handle(Signal sig) {
        System.out.println("Accept signal - " + sig);
        if (SignalHandler.SIG_DFL != oldHandler && SignalHandler.SIG_IGN != oldHandler) {
            oldHandler.handle(sig);
            System.out.println(oldHandler.getClass());
        }
    }
}