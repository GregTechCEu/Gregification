package gregification.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GFLog {

    public static Logger baseLogger;
    public static Logger exNihiloLogger;
    public static Logger ocLogger;
    public static Logger forestryLogger;

    public static void init(Logger modLogger) {
        baseLogger = modLogger;
        exNihiloLogger = LogManager.getLogger(String.format("%s: %s", baseLogger.getName(), "Ex Nihilo"));
        ocLogger = LogManager.getLogger(String.format("%s: %s", baseLogger.getName(), "Open Computers"));
        forestryLogger = LogManager.getLogger(String.format("%s: %s", baseLogger.getName(), "Forestry"));
    }
}
