package org.primeoservices.net.icap.messages;

import java.io.IOException;

import org.apache.commons.net.SocketClient;
import org.primeoservices.net.EntityBody;
import org.primeoservices.net.Header;
import org.primeoservices.net.HeaderList;
import org.primeoservices.net.StatusLine;
import org.primeoservices.net.icap.IcapHeaders;
import org.primeoservices.net.icap.IcapOutputStream;

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
  public void write(final IcapOutputStream out) throws IOException
  {
    // Add encapsulated header
    this.addHeader(buildEncapHeader());
    // Write request
    super.write(out);
    // Write HTTP response status line
    out.write(this.respStatusLine.toString());
    out.write(SocketClient.NETASCII_EOL);
    // Write HTTP response headers
    out.write(this.respHeaders.toString());
    out.write(SocketClient.NETASCII_EOL);
    // Write HTTP response body
    out.write(Long.toHexString(this.respBody.getLength()));
    out.write(SocketClient.NETASCII_EOL);
    this.respBody.write(out);
    out.write(SocketClient.NETASCII_EOL);
    out.write("0; ieof");
    out.write(SocketClient.NETASCII_EOL);
    out.write(SocketClient.NETASCII_EOL);
  }

  private Header buildEncapHeader()
  {
    final int hdrLength = this.respStatusLine.getLength()
                            + this.respHeaders.getLength()
                            + 6;
    return new Header(IcapHeaders.ENCAPSULATED, "res-hdr=0, res-body=" + hdrLength);
  }
}
