package org.primeoservices.net.icap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IcapInputStream extends BufferedReader
{
  public IcapInputStream(final InputStream in)
  {
    super(new InputStreamReader(in, Icap.DEFAULT_CHARSET));
  }
}
