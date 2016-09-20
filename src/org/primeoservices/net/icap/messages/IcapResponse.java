package org.primeoservices.net.icap.messages;

import java.io.BufferedReader;
import java.io.IOException;

import org.primeoservices.net.Header;
import org.primeoservices.net.ProtocolVersion;
import org.primeoservices.net.StatusLine;

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

  public static IcapResponse read(final BufferedReader reader) throws IOException
  {
    // Read status line
    String line = reader.readLine();
    final StatusLine statusLine = StatusLine.parse(line);
    final IcapResponse response = new IcapResponse(statusLine);
    // Read headers
    while ((line = reader.readLine()) != null)
    {
      if (line.length() == 0) break;
      response.addHeader(Header.parse(line));
    }
    // Skip the rest
    reader.skip(Long.MAX_VALUE);
    // Return response
    return response;
  }
}
