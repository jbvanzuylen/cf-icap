package org.primeoservices.net.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.primeoservices.net.EntityBody;

public class FileEntityBody implements EntityBody
{
  private File file;

  public FileEntityBody(final File file)
  {
    this.file = file;
  }

  @Override
  public long getLength()
  {
    return this.file.length();
  }

  @Override
  public void write(final OutputStream out) throws IOException
  {
    final InputStream in = new FileInputStream(this.file);
    try
    {
      IOUtils.copy(in, out);
      out.flush();
    }
    finally
    {
      IOUtils.closeQuietly(in);
    }
  }

  @Override
  public void write(Writer writer) throws IOException
  {
    final InputStream in = new FileInputStream(this.file);
    try
    {
      IOUtils.copy(in, writer);
      writer.flush();
    }
    finally
    {
      IOUtils.closeQuietly(in);
    }
  }

  @Override
  public InputStream getContent() throws IOException
  {
    return new FileInputStream(this.file);
  }
}
