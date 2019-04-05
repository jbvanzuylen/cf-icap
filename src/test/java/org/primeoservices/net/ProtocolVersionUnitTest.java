package org.primeoservices.net;

import org.junit.Assert;
import org.junit.Test;

public class ProtocolVersionUnitTest
{
  @Test
  public void testParseValid()
  {
    final ProtocolVersion version = ProtocolVersion.parse("HTTP/1.0");
    Assert.assertEquals("HTTP", version.getProtocol());
    Assert.assertEquals(1, version.getMajor());
    Assert.assertEquals(0, version.getMinor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalid()
  {
    ProtocolVersion.parse("Invalid");
  }
  
  @Test
  public void testToString()
  {
    final ProtocolVersion version = new ProtocolVersion("HTTP", 1, 0);
    Assert.assertEquals("HTTP/1.0", version.toString());
  }
}
