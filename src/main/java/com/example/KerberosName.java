package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KerberosName {

    /**
     * A pattern that matches a Kerberos name with at most 3 components.
     */
    private static final Pattern NAME_PARSER = Pattern.compile("([^/@]*)(/([^/@]*))?@([^/@]*)");

    /** The first component of the name */
    private final String serviceName;
    /** The second component of the name. It may be null. */
    private final String hostName;
    /** The realm of the name. */
    private final String realm;

    /**
     * Creates an instance of `KerberosName` with the provided parameters.
     */
    public KerberosName(String serviceName, String hostName, String realm) {
        if (serviceName == null)
            throw new IllegalArgumentException("serviceName must not be null");
        this.serviceName = serviceName;
        this.hostName = hostName;
        this.realm = realm;
    }

    /**
     * Create a name from the full Kerberos principal name.
     */
    public static KerberosName parse(String principalName) {
        Matcher match = NAME_PARSER.matcher(principalName);
        if (!match.matches()) {
            if (principalName.contains("@")) {
                throw new IllegalArgumentException("Malformed Kerberos name: " + principalName);
            } else {
                return new KerberosName(principalName, null, null);
            }
        } else {
            return new KerberosName(match.group(1), match.group(3), match.group(4));
        }
    }

    /**
     * Put the name back together from the parts.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(serviceName);
        if (hostName != null) {
            result.append('/');
            result.append(hostName);
        }
        if (realm != null) {
            result.append('@');
            result.append(realm);
        }
        return result.toString();
    }

    /**
     * Get the first component of the name.
     * @return the first section of the Kerberos principal name
     */
    public String serviceName() {
        return serviceName;
    }

    /**
     * Get the second component of the name.
     * @return the second section of the Kerberos principal name, and may be null
     */
    public String hostName() {
        return hostName;
    }

    /**
     * Get the realm of the name.
     * @return the realm of the name, may be null
     */
    public String realm() {
        return realm;
    }

}
