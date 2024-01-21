package server;

import config.AppConfig;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Server {

    public static Javalin configureJavalin() {
        return Javalin.create(config ->
                config.staticFiles.add("/templates", Location.CLASSPATH))
                .start(AppConfig.SERVER_PORT);
    }

}
