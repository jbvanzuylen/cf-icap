package org.primeoservices.net.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import org.primeoservices.net.EntityBody;

public class StringEntityBody implements EntityBody
{
  final String string;

  public StringEntityBody(final String string)
  {
    this.string = string;
  }

  @Override
  public long getLength()
  {
    return this.string.length();
  }

  @Override
  public void write(final OutputStream out) throws IOException
  {
    out.write(this.string.getBytes());
  }

  @Override
  public void write(Writer writer) throws IOException
  {
    writer.write(this.string);
  }

  @Override
  public InputStream getContent() throws IOException
  {
    return new ByteArrayInputStream(this.string.getBytes());
  }
}
