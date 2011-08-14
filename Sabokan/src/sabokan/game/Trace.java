package sabokan.game;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

/**
 * Custom Trace for the whole game application
 * TODO, better handling
 * @author anaka
 */
public class Trace {

    private static final Logger log = Logger.getLogger("Sakoban");

    static {
        for (Handler hand : log.getHandlers()) {
            log.removeHandler(hand);
        }
        SokobanHandler handler = new SokobanHandler();
        log.setLevel(Level.ALL);
        log.addHandler(handler);
        log.setUseParentHandlers(false);
    }

    private static class SokobanHandler extends StreamHandler {

        public SokobanHandler() {
            setOutputStream(System.out);
            setLevel(Level.ALL);
            setFormatter(new Formatter() {

                @Override
                public String format(LogRecord record) {
                    if (record.getLevel() == Level.SEVERE) {
                        return "ERROR: " + record.getMessage() + "\n";
                    } else {
                        return record.getLoggerName() + ": " + record.getMessage() + "\n";
                    }
                }

                @Override
                public synchronized String formatMessage(LogRecord record) {
                    return format(record);
                }

                @Override
                public String getHead(Handler h) {
                    return "";
                }

                @Override
                public String getTail(Handler h) {
                    return "";
                }
            });
        }

        @Override
        public synchronized void close() throws SecurityException {
            super.close();
            flush();
        }

        @Override
        public synchronized void flush() {
            super.flush();
        }

        @Override
        public synchronized void publish(LogRecord record) {
            super.publish(record);
            flush();
        }
    }

    private Trace() {
    }

    /**
     * Traces a message as info
     * @param msg
     */
    public static void info(String msg) {
        log.log(Level.INFO, msg);
    }

    /**
     * Traces a message as error
     * @param msg
     */
    public static void error(String msg) {
        log.log(Level.SEVERE, msg);
    }

    /**
     * Delegates the stacktrace to error(String msg)
     * @param ex
     */
    public static void error(Throwable ex) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        ex.printStackTrace(printWriter);
        error(result.toString());
    }
}
