package org.primeoservices.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProtocolVersion
{
  private static final Pattern PATTERN = Pattern.compile("([A-Z]{3,4})\\/(\\d).(\\d)");

  private String protocol;

  private int major;

  private final int minor;

  public ProtocolVersion(final String protocol, final int major, final int minor)
  {
    this.protocol = protocol;
    this.major = major;
    this.minor = minor;
  }

  public String getProtocol()
  {
    return this.protocol;
  }

  public int getMajor()
  {
    return this.major;
  }

  public int getMinor()
  {
    return this.minor;
  }

  public int getLength()
  {
    return this.protocol.length()
              + Integer.toString(this.major).length()
              + Integer.toString(this.minor).length()
              + 2;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof ProtocolVersion)) return false;
    final ProtocolVersion version = (ProtocolVersion) obj;
    return this.protocol.equals(version.getProtocol())
              && this.major == version.getMajor()
              && this.minor == version.getMinor();
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder();
    sb.append(this.protocol);
    sb.append("/");
    sb.append(Integer.toString(this.major));
    sb.append(".");
    sb.append(Integer.toString(this.minor));
    return sb.toString();
  }

  public static ProtocolVersion parse(final String s)
  {
    final Matcher matcher = PATTERN.matcher(s);
    if (!matcher.matches())
    {
      throw new IllegalArgumentException("Invalid protocol version: " + s);
    }
    final String protocol = matcher.group(1);
    final int major = Integer.valueOf(matcher.group(2));
    final int minor = Integer.valueOf(matcher.group(3));
    return new ProtocolVersion(protocol, major, minor);
  }
}
