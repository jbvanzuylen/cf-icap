package org.primeoservices.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface EntityBody
{
  public long getLength();

  public void write(OutputStream out) throws IOException;

  public InputStream getContent() throws IOException;
}
