package Lesson6;
import java.io.IOException;
import java.util.logging.*;
import java.util.logging.Logger;
//import org.apache.log4j.Logger;

public class Main {

 /*   public static void main(String[] args) {

        Logger admin = Logger.getLogger("admin");

        admin.info("This is info");
        admin.warn("This is warn");
        admin.error("This is error");
        admin.fatal("This is fatal");

        Logger file = Logger.getLogger("file");

        file.info("This is info");
        file.warn("This is warn");
        file.error("This is error");
        file.fatal("This is fatal");

    }
*/

    protected static final Logger logger = Logger.getLogger("");

    public static void main(String[] args) throws IOException {

        logger.setLevel(Level.ALL);
        logger.getHandlers()[0].setLevel(Level.ALL);

        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + "\t" + record.getMessage() + "\t" + record.getMillis() + "\n";
            }
        });

        logger.getHandlers()[0].setFilter(record -> record.getMessage().startsWith("Checkpoint"));

        Handler handler = new FileHandler("mylog.log", true);
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);

        logger.log(Level.SEVERE, " S");
        logger.log(Level.INFO, "Checkpoint I");
        logger.log(Level.CONFIG, " C");
        logger.log(Level.FINE, "Checkpoint F");



        //OFF
        //SEVERE
        //WARNING
        //INFO
        //CONFIG
        //FINE(3)
        //ALL
        }


    }




