package org.primeoservices.cficap;

import java.io.File;

import org.primeoservices.net.EntityBody;
import org.primeoservices.net.StatusLine;
import org.primeoservices.net.entity.FileEntityBody;
import org.primeoservices.net.http.Http;
import org.primeoservices.net.http.HttpHeaders;
import org.primeoservices.net.icap.IcapClient;
import org.primeoservices.net.icap.IcapHeaders;
import org.primeoservices.net.icap.messages.IcapRespMod;
import org.primeoservices.net.icap.messages.IcapResponse;

public class IcapUtils
{
  /**
   * This utilities class should never be initialized
   */
  private IcapUtils()
  {
  }

  public static IcapResponse scanFile(final String host, final int port, final String service, final String filePath) throws Exception
  {
    // Check file
    final File file = new File(filePath);
    if (file.exists() && !file.isFile())
    {
      throw new IllegalArgumentException("File " + filePath + " not found");
    }

    // Create request
    final EntityBody body = new FileEntityBody(file);
    final IcapRespMod respmod = new IcapRespMod(host, port, service);
    respmod.addHeader(IcapHeaders.CONNECTION, "Close");
    respmod.setResponseStatusLine(new StatusLine(Http.HTTP_1_1, 200, "OK"));
    respmod.addResponseHeader(HttpHeaders.CONTENT_LENGTH, Long.toString(body.getLength()));
    respmod.setResponseBody(body);

    // Send request
    final IcapClient client = new IcapClient();
    final IcapResponse response = client.execute(respmod);
    client.disconnect();
    return response; 
  }
}
