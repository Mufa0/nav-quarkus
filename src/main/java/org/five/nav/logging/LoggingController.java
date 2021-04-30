package org.five.nav.logging;
import javax.ws.rs.*;
import java.util.logging.*;
@Path("/logging")
public class LoggingController {

    private static Level getLogLevel(Logger logger) {
           for (Logger current = logger; current != null;) {
                Level level = current.getLevel();
                if (level != null)
                    return level;
                current = current.getParent();
            }
            return Level.INFO;
        }

        @GET
        @Path("/{logger}")
        @Produces("text/plain")
        public String logger(@PathParam("logger") String loggerName, @QueryParam("level") String level) {
            // get the logger instance
            Logger logger = Logger.getLogger(loggerName);

            // change the log-level if requested
            if (level != null && level.length() > 0)
                logger.setLevel(Level.parse(level));

            // return the current log-level
            return getLogLevel(logger).toString();
        }
}
