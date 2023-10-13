package webserver667;

import java.nio.file.Path;

import driver.IStartableServer;
import webserver667.configuration.MimeTypes;
import webserver667.configuration.ServerConfiguration;

public class WebServer implements IStartableServer {

  @Override
  public void close() throws Exception {
    // TODO Auto-generated method stub; close any resources opened by server (i.e.
    // ServerSocket)
  }

  @Override
  public void start(ServerConfiguration configuration, MimeTypes mimeTypes) {
    Path configFile = configuration.getConfigFilePath();
    // Load configuration settings from the configFile and use them to start the server.
    // TODO Auto-generated method stub; implement your server startup information
  }

  @Override
  public void stop() {
    // TODO Auto-generated method stub
  }
}
