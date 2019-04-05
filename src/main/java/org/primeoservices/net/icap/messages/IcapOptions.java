package org.primeoservices.net.icap.messages;

public class IcapOptions extends AbstractIcapRequest
{
  public final static String METHOD_NAME = "OPTIONS";

  public IcapOptions(final String host, final int port, final String service) throws Exception
  {
    super(host, port, service);
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
}
