package org.primeoservices.net.icap.messages;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.commons.net.SocketClient;
import org.primeoservices.net.EntityBody;
import org.primeoservices.net.Header;
import org.primeoservices.net.HeaderList;
import org.primeoservices.net.StatusLine;
import org.primeoservices.net.icap.IcapHeaders;

public class IcapRespMod extends AbstractIcapRequest
{
  public final static String METHOD_NAME = "RESPMOD";

  public StatusLine respStatusLine;

  public HeaderList respHeaders;

  public EntityBody respBody;

  public IcapRespMod(final String host, final int port, final String service) throws Exception
  {
    super(host, port, service);
    this.addHeader(IcapHeaders.ALLOW, Integer.toString(204));
    this.respHeaders = new HeaderList();
  }

  /**
   * Returns the method for this request
   * 
   * @return the method for the request
   */
  @Override
  public String getMethod()
  {
    return METHOD_NAME;
  }

  public void setResponseStatusLine(final StatusLine line)
  {
    this.respStatusLine = line;
  }

  public void addResponseHeader(final String name, final String value)
  {
    this.addResponseHeader(new Header(name, value));
  }

  public void addResponseHeader(final Header header)
  {
    this.respHeaders.add(header);
  }

  public void setResponseBody(final EntityBody body)
  {
    this.respBody = body;
  }

  @Override
  public void write(final BufferedWriter writer) throws IOException
  {
    // Add encapsulated header
    this.addHeader(buildEncapHeader());
    // Write request
    super.write(writer);
    // Write HTTP response status line
    writer.write(this.respStatusLine.toString());
    writer.write(SocketClient.NETASCII_EOL);
    // Write HTTP response headers
    writer.write(this.respHeaders.toString());
    writer.write(SocketClient.NETASCII_EOL);
    // Write HTTP response body
    writer.write(Long.toHexString(this.respBody.getLength()));
    writer.write(SocketClient.NETASCII_EOL);
    this.respBody.write(writer);
    writer.write(SocketClient.NETASCII_EOL);
    writer.write("0; ieof");
    writer.write(SocketClient.NETASCII_EOL);
    writer.write(SocketClient.NETASCII_EOL);
  }

  private Header buildEncapHeader()
  {
    final int hdrLength = this.respStatusLine.getLength()
                            + this.respHeaders.getLength()
                            + 6;
    return new Header(IcapHeaders.ENCAPSULATED, "res-hdr=0, res-body=" + hdrLength);
  }
}
