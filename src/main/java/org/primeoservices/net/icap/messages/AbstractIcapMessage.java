package org.primeoservices.net.icap.messages;

import org.primeoservices.net.Header;
import org.primeoservices.net.HeaderList;
import org.primeoservices.net.icap.IcapMessage;

public abstract class AbstractIcapMessage implements IcapMessage
{
  /**
   * The headers
   */
  private HeaderList headers;

  public AbstractIcapMessage()
  {
    super();
    this.headers = new HeaderList();
  }

  /**
   * Returns the headers for this message
   * 
   * @return the headers for the message
   */
  @Override
  public Header[] getHeaders()
  {
    return this.headers.getHeaders();
  }

  /**
   * 
   */
  @Override
  public String getHeaderString()
  {
    return this.headers.toString();
  }

  /**
   * Adds the header with specified name and value to this message
   * 
   * @param name the name of the header to be added
   * @param value the value of the header to be added
   */
  @Override
  public void addHeader(final String name, final String value)
  {
    this.addHeader(new Header(name, value));
  }

  /**
   * Adds the specified header to this message
   * 
   * @param header the header to be added
   */
  @Override
  public void addHeader(final Header header)
  {
    this.headers.add(header);
  }
}
