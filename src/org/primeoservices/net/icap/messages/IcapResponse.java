package org.primeoservices.net.icap.messages;

import java.io.IOException;

import org.primeoservices.net.Header;
import org.primeoservices.net.ProtocolVersion;
import org.primeoservices.net.StatusLine;
import org.primeoservices.net.icap.IcapInputStream;

public class IcapResponse extends AbstractIcapMessage
{
  private StatusLine statusLine;

  public IcapResponse(final StatusLine statusLine)
  {
    super();
    this.statusLine = statusLine;
  }

  /**
   * Returns the protocol version of this response
   * 
   * @return the protocol version of the response
   */
  @Override
  public ProtocolVersion getProtocolVersion()
  {
    return this.statusLine.getProtocolVersion();
  }

  /**
   * Returns the status line of this response
   * 
   * @return the status line of the response
   */
  public StatusLine getStatusLine()
  {
    return this.statusLine;
  }

  /**
   * Reads the response from the specified input stream
   * 
   * @param in the input stream from which the response is to be read
   * 
   * @return the response read from the input stream
   * 
   * @throws IOException
   */
  public static IcapResponse read(final IcapInputStream in) throws IOException
  {
    // Read status line
    String line = in.readLine();
    final StatusLine statusLine = StatusLine.parse(line);
    final IcapResponse response = new IcapResponse(statusLine);
    // Read headers
    StringBuilder header = new StringBuilder();
    while ((line = in.readLine()) != null)
    {
      if (line.length() == 0)
      {
        if (header.length() > 0)
        {
          response.addHeader(Header.parse(header.toString()));
        }
        break;
      }
      if (Character.isWhitespace(line.charAt(0)))
      {
        header.append("\n").append(line.trim());
      }
      else
      {
        if (header.length() > 0)
        {
          response.addHeader(Header.parse(header.toString()));
          header = new StringBuilder();
        }
        header.append(line);
      }
    }
    // Skip the rest
    in.skip(Long.MAX_VALUE);
    // Return response
    return response;
  }
}
