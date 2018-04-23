package org.primeoservices.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Header
{
  private static final Pattern PATTERN = Pattern.compile("([A-Za-z0-9-]+): (.*)", Pattern.DOTALL);

  /**
   * The name of the header
   */
  private String name;

  /**
   * The value of the header
   */
  private String value;

  /**
   * Creates a new <code>Header</code> with specified name and value
   * 
   * @param name the name of the header to be created
   * @param value the value of the header to be created
   */
  public Header(final String name, final String value)
  {
    this.name = name;
    this.value = value;
  }

  /**
   * Returns the name of this header
   * 
   * @return the name of the header
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Returns the value of this header
   * 
   * @return the value of the header
   */
  public String getValue()
  {
    return this.value;
  }

  /**
   * Returns the length of this header
   * 
   * @return the lenght of the header
   */
  public int getLength()
  {
    return this.name.length() + this.value.length() + 2;
  }

  @Override
  public String toString()
  {
    return this.name + ": " + this.value;
  }

  public static Header parse(final String s)
  {
    final Matcher matcher = PATTERN.matcher(s);
    if (!matcher.matches())
    {
      throw new IllegalArgumentException("Invalid header: " + s);
    }
    final String name = matcher.group(1);
    final String value = matcher.group(2);
    return new Header(name, value);
  }
}
