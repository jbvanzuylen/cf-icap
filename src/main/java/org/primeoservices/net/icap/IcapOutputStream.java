package org.primeoservices.net.icap;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IcapOutputStream extends BufferedOutputStream
{
  public IcapOutputStream(final OutputStream out)
  {
    super(out);
  }

  public void write(final String s) throws IOException
  {
    this.write(s.getBytes(Icap.DEFAULT_CHARSET));
  }
}
