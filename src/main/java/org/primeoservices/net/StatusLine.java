package org.primeoservices.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatusLine
{
  private static final Pattern PATTERN = Pattern.compile("([A-Z0-9/.]+) ([0-9]{3}) (.*)");

  /**
   * The protocol version
   */
  private ProtocolVersion protocolVersion;

  /**
   * The status code
   */
  private int code;

  /**
   * The status text
   */
  private String text;

  public StatusLine(final ProtocolVersion version, final int code, final String text)
  {
    this.protocolVersion = version;
    this.code = code;
    this.text = text;
  }

  public ProtocolVersion getProtocolVersion()
  {
    return this.protocolVersion;
  }

  public int getCode()
  {
    return this.code;
  }

  public String getText()
  {
    return this.text;
  }

  /**
   * Returns the length of this status line
   * 
   * @return the length of the status line
   */
  public int getLength()
  {
    return this.protocolVersion.getLength()
              + Integer.toString(this.code).length()
              + this.text.length()
              + 2;
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder();
    sb.append(this.protocolVersion);
    sb.append(" ");
    sb.append(Integer.toString(this.code));
    sb.append(" ");
    sb.append(this.text);
    return sb.toString();
  }

  public static StatusLine parse(final String s)
  {
    final Matcher matcher = PATTERN.matcher(s);
    if (!matcher.matches())
    {
      throw new IllegalArgumentException("Invalid status line: " + s);
    }
    final ProtocolVersion protocolVersion = ProtocolVersion.parse(matcher.group(1));
    final int code = Integer.valueOf(matcher.group(2));
    final String text = matcher.group(3);
    return new StatusLine(protocolVersion, code, text);
  }
}
