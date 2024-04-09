package my;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Slf4jExample {

    private static Logger logger = LoggerFactory.getLogger(Slf4jExample.class);


    public Slf4jExample() {
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
    }
}