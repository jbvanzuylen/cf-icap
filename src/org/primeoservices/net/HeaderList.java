package org.primeoservices.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.SocketClient;

public class HeaderList
{
  private List<Header> headers;

  public HeaderList()
  {
    this.headers = new ArrayList<Header>(16);
  }

  /**
   * Returns the headers in this list
   * 
   * @return the headers in the list
   */
  public Header[] getHeaders()
  {
    return this.headers.toArray(new Header[this.headers.size()]);
  }

  /**
   * Adds the header with specified name and value to this list
   * 
   * @param name the name of the header to be added
   * @param value the value of the header to be added
   */
  public void add(final String name, final String value)
  {
    this.add(new Header(name, value));
  }

  /**
   * Adds the specified header to this list
   * 
   * @param header the header to be added to the list
   */
  public void add(final Header header)
  {
    this.headers.add(header);
  }

  /**
   * Returns the length of this list
   * 
   * @return the length of the list
   */
  public int getLength()
  {
    int length = 0;
    for (Header header : this.headers)
    {
      length += header.getLength();
    }
    return length;
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder();
    for (Header header : headers)
    {
      sb.append(header);
      sb.append(SocketClient.NETASCII_EOL);
    }
    return sb.toString();
  }
}
