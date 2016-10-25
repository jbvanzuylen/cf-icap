package org.primeoservices.net.icap.messages;

import java.io.IOException;

import org.primeoservices.net.RequestLine;
import org.primeoservices.net.icap.IcapMessage;
import org.primeoservices.net.icap.IcapOutputStream;

public interface IcapRequest extends IcapMessage
{
  public RequestLine getRequestLine();

  public void write(final IcapOutputStream out) throws IOException;
}
