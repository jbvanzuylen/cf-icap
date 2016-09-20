package org.primeoservices.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

public interface EntityBody
{
  public long getLength();

  public void write(OutputStream out) throws IOException;

  public void write(Writer writer) throws IOException;

  public InputStream getContent() throws IOException;
}
