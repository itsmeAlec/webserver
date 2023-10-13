package webserver667.configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import webserver667.exceptions.ConfigurationHelpRequestedException;
import webserver667.exceptions.InvalidServerConfigurationException;

public class ServerConfiguration {
    // Define flags for command-line options.
    protected static final ServerCliFlag helpFlag = new ServerCliFlag(
      new LinkedHashSet<String>(Arrays.asList("-h", "--help")),
      "Print usage information");
    protected static final ServerCliFlag portFlag = new ServerCliFlag(
      new LinkedHashSet<String>(Arrays.asList("-p", "--port")),
      "The integer port number the server should listen on",
      true,
      true);
    protected static final ServerCliFlag documentRootFlag =  new ServerCliFlag(
      new LinkedHashSet<String>(Arrays.asList("-r", "--root")),
      "The local filesystem path from which the server should resolve URIs",
      true,
      true);
    // Add a new flag for specifying the configuration file path.
    protected static final ServerCliFlag configFileFlag = ServerCliFlag.configFileFlag;

    private Set<ServerCliFlag> specification;
    private int port;
    private Path documentRootPath;
    private Path configFile; // Store the path to the configuration file.

    /**
     * Create a ServerConfiguration instance by parsing command-line arguments.
     *
     * @param args              The command-line arguments.
     * @param cliFlagSpecification The set of available CLI flags.
     * @throws InvalidServerConfigurationException If there are issues with configuration.
     * @throws ConfigurationHelpRequestedException If help information is requested.
     */
    public ServerConfiguration(String[] args, Set<ServerCliFlag> cliFlagSpecification)
        throws InvalidServerConfigurationException, ConfigurationHelpRequestedException {
        this.specification = cliFlagSpecification;

        // Create a ConfigurationValidator to validate and parse command-line arguments.
        ConfigurationValidator validator = new ConfigurationValidator(args, cliFlagSpecification);
        validator.validate();

        // Get validated values from the validator.
        Map<String, String> validatedValues = validator.validatedValues();

        // Set port and document root based on validated values.
        this.setPort(validatedValues.get("-p"), validatedValues.get("--port"));
        this.setDocumentRootPath(validatedValues.get("-r"), validatedValues.get("--root"));
        // Set the configuration file path based on the validated values.
        this.setConfigFilePath(validatedValues.get("-c"), validatedValues.get("--config"));
    }

    private void setPort(String shortVersion, String longVersion) throws InvalidServerConfigurationException {
      
    }

    public int getPort() {
        return this.port;
    }

    private void setDocumentRootPath(String shortVersion, String longVersion) throws InvalidServerConfigurationException {
       
    }

    public Path getDocumentRoot() {
        return this.documentRootPath;
    }

    private void setConfigFilePath(String shortVersion, String longVersion) throws InvalidServerConfigurationException {
        if (!this.specification.contains(configFileFlag)) {
            return;
        }

        String specifiedVersion = shortVersion == null ? longVersion : shortVersion;
        this.configFile = Paths.get(specifiedVersion);

        if (!Files.exists(this.configFile)) {
            throw new InvalidServerConfigurationException(
                this.specification,
                new LinkedHashSet<>(
                    Arrays.asList(String.format("The configuration file %s does not exist", this.configFile.toString()))));
        }
    }

    // public static ServerConfiguration getDefault(String[] args)
    //   throws InvalidServerConfigurationException, ConfigurationHelpRequestedException {
    // // Define the set of flags that server configuration accepts
    // Set<ServerCliFlag> cliFlagSpecification = new LinkedHashSet<>(
    //     Arrays.asList(helpFlag, portFlag, documentRootFlag, configFileFlag));

    /**
     * Get the path to the configuration file specified via CLI.
     *
     * @return The path to the configuration file.
     */
    public Path getConfigFilePath() {
        return this.configFile;
    }
}