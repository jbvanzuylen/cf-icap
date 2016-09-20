package org.primeoservices.net;

import org.junit.Assert;
import org.junit.Test;

public class StatusLineUnitTest
{
  @Test
  public void testParseValid()
  {
    final StatusLine line = StatusLine.parse("HTTP/1.0 204 No Content");
    Assert.assertEquals(new ProtocolVersion("HTTP", 1, 0), line.getProtocolVersion());
    Assert.assertEquals(204, line.getCode());
    Assert.assertEquals("No Content", line.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalid()
  {
    StatusLine.parse("Invalid");
  }

  @Test
  public void testToString()
  {
    final StatusLine line = new StatusLine(new ProtocolVersion("HTTP", 1, 0), 404, "Not Found");
    Assert.assertEquals("HTTP/1.0 404 Not Found", line.toString());
  }
}
