package driver;

import webserver667.WebServer;
import webserver667.configuration.MimeTypes;
import webserver667.configuration.ServerConfiguration;
import webserver667.exceptions.ConfigurationHelpRequestedException;
import webserver667.exceptions.InvalidServerConfigurationException;

public class ServerStartup {
  public static void main(String[] args) {
    try (WebServer server = new WebServer()) {
      server.start(ServerConfiguration.getDefault(args), MimeTypes.fromDefaultFile());
    } catch (ConfigurationHelpRequestedException configHelpException) {
      System.out.println(configHelpException.getMessage());
      System.exit(0);
    } catch (InvalidServerConfigurationException configException) {
      System.out.println(configException.getMessage());
      System.exit(1);
    } catch (Exception failedToCloseException) {
      System.err.println("Failed to close server resources on shut down");
      System.exit(1);
    }
  }
}
