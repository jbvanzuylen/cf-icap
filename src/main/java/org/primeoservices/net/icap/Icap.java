package org.primeoservices.net.icap;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.primeoservices.net.ProtocolVersion;

public class Icap
{
  public static final ProtocolVersion ICAP_1_0 = new ProtocolVersion("ICAP", 1, 0);

  public static final String URI_SCHEME = "icap";

  public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8; 

  public static final int DEFAULT_PORT = 1344;
}
