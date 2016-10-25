package org.primeoservices.net.icap.messages;

import java.io.IOException;
import java.net.URI;

import org.apache.commons.net.SocketClient;
import org.primeoservices.net.ProtocolVersion;
import org.primeoservices.net.RequestLine;
import org.primeoservices.net.icap.Icap;
import org.primeoservices.net.icap.IcapHeaders;
import org.primeoservices.net.icap.IcapOutputStream;

public abstract class AbstractIcapRequest extends AbstractIcapMessage implements IcapRequest
{
  private RequestLine requestLine;

  public AbstractIcapRequest(final String host, final String service) throws Exception
  {
    this(new URI(Icap.URI_SCHEME + "://" + host + "/" + service));
  }

  public AbstractIcapRequest(final String host, final int port, final String service) throws Exception
  {
    this(new URI(Icap.URI_SCHEME + "://" + host + ":" + port + "/" + service));
  }

  private AbstractIcapRequest(final URI uri)
  {
    this.requestLine = new RequestLine(this.getMethod(), uri, Icap.ICAP_1_0);
    this.addHeader(IcapHeaders.HOST, uri.getHost());
    this.addHeader(IcapHeaders.USER_AGENT, "ICAP/1.0");
  }

  /**
   * Returns the method for this request
   * 
   * @return the method for the request
   */
  protected abstract String getMethod();

  /**
   * Returns the protocol version of this request
   * 
   * @return the protocol version of the request
   */
  @Override
  public ProtocolVersion getProtocolVersion()
  {
    return this.requestLine.getProtocolVersion();
  }

  /**
   * Returns the request line of this request
   * 
   * @return the request line of the request
   */
  @Override
  public RequestLine getRequestLine()
  {
    return this.requestLine;
  }

  /**
   * Writes this request to the specified write
   * 
   * @param out the output stream to which the request is to be written
   * 
   * @throws IOException in case of an error when writing this request
   */
  public void write(final IcapOutputStream out) throws IOException
  {
    // Write request line
    out.write(this.requestLine.toString());
    out.write(SocketClient.NETASCII_EOL);
    // Write headers
    out.write(this.getHeaderString());
    out.write(SocketClient.NETASCII_EOL);
  }
}
