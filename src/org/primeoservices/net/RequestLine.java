package org.primeoservices.net;

import java.net.URI;

public class RequestLine
{
  private String method;

  private URI uri;

  private ProtocolVersion protocolVersion;

  public RequestLine(final String method, final URI uri, final ProtocolVersion protocolVersion)
  {
    this.method = method;
    this.uri = uri;
    this.protocolVersion = protocolVersion;
  }

  public String getMethod()
  {
    return this.method;
  }

  public URI getURI()
  {
    return this.uri;
  }

  public ProtocolVersion getProtocolVersion()
  {
    return this.protocolVersion;
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder();
    sb.append(this.method);
    sb.append(" ");
    sb.append(this.uri);
    sb.append(" ");
    sb.append(this.protocolVersion);
    return sb.toString();
  }
}
