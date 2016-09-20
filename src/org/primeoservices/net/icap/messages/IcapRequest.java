package org.primeoservices.net.icap.messages;

import java.io.BufferedWriter;
import java.io.IOException;

import org.primeoservices.net.RequestLine;
import org.primeoservices.net.icap.IcapMessage;

public interface IcapRequest extends IcapMessage
{
  public RequestLine getRequestLine();

  public void write(final BufferedWriter writer) throws IOException;
}
