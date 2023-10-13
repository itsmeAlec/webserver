package webserver667.configuration;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Arrays;

public class ServerCliFlag {
  private Set<String> flags;
  private String description;
  private boolean required;
  private boolean unique;

  public ServerCliFlag(Set<String> flags, String description, boolean required, boolean unique) {
    this.flags = flags;
    this.description = description;
    this.required = required;
    this.unique = unique;
  }

  public ServerCliFlag(Set<String> flags, String description) {
    this(flags, description, false, true);
  }

    // Define a new flag for specifying the configuration file path.
    protected static final ServerCliFlag configFileFlag = new ServerCliFlag(
        new LinkedHashSet<String>(Arrays.asList("-c", "--config")),
        "Path to the configuration file");

  public String getDescription() {
    return String.format(
        "%-15s %50s",
        String.join(" ", this.flags).trim(),
        String.format(
            "Required: %3s Unique: %3s\n%s%s",
            this.required ? "Yes" : "No",
            this.unique ? "Yes" : "No",
            String.format("%16s", " "),
            this.description));
  }

  public Set<String> getFlags() {
    return new LinkedHashSet<>(this.flags);
  }

  public boolean isRequired() {
    return this.required;
  }

  public boolean isUnique() {
    return this.unique;
  }
}
