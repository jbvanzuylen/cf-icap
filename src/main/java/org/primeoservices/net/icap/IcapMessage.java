package org.primeoservices.net.icap;

import org.primeoservices.net.Header;
import org.primeoservices.net.ProtocolVersion;

public interface IcapMessage
{
  public ProtocolVersion getProtocolVersion();

  public Header[] getHeaders();

  public String getHeaderString(); 

  public void addHeader(String name, String value);

  public void addHeader(Header header);
}
