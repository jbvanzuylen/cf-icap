package org.primeoservices.net.icap;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.primeoservices.net.EntityBody;
import org.primeoservices.net.ProtocolVersion;
import org.primeoservices.net.StatusLine;
import org.primeoservices.net.entity.FileEntityBody;
import org.primeoservices.net.entity.StringEntityBody;
import org.primeoservices.net.icap.IcapClient;
import org.primeoservices.net.icap.messages.IcapOptions;
import org.primeoservices.net.icap.messages.IcapRespMod;
import org.primeoservices.net.icap.messages.IcapResponse;

public class IcapClientTest
{
  public static void main(String[] args) throws Exception
  {
    //final File file = new File("C:\\Users\\Jean-Bernard\\Desktop\\IT Risk Tracking Guide -Proxyclick - 15248-DB.xlsx");
    //final File file = new File("D:\\Hubic\\InvitKathRaph10Ans.ppsx");
    //final File file = new File("D:\\Hubic\\Thunderbird\\1429882416498-moneylog-huawei.xlsx");
    final File file = new File("D:\\Projects\\Proxyclick\\test\\malicious-content-scan\\eicar.com.txt");
    final EntityBody body = new FileEntityBody(file);
    
    //final String content ="X5O!P%@AP[4\\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*";
    //final EntityBody body = new StringEntityBody(content);

    /*
    final ProtocolVersion version = new ProtocolVersion("HTTP", 1, 1);
    System.out.println(version);
    System.out.println("-----");
    final StatusLine line = new StatusLine(version, 200, "OK");
    System.out.println(line);
    System.out.println("-----");
    final HttpResponseHeader header = new HttpResponseHeader(line);
    header.addHeader("Content-Length", Integer.toString(150));
    System.out.println(header);
    System.out.println("-----");
    final RequestLine requestLine = new RequestLine("OPTIONS", new URI("icap://virus.proxyclick.com/avscan"), version);
    System.out.println(requestLine);
    */

    System.out.println("-----");
    final IcapClient client = new IcapClient();
    /*
    final IcapOptions options = new IcapOptions("164.132.149.98", 1344, "avscan");
    options.addHeader(IcapHeaders.CONNECTION, "Close");
    final IcapResponse response = client.execute(options);
    */
    final IcapRespMod respmod = new IcapRespMod("79.137.105.39", 1344, "avscan");
    respmod.addHeader(IcapHeaders.CONNECTION, "Close");
    respmod.setResponseStatusLine(new StatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
    respmod.addResponseHeader("Content-Length", Long.toString(body.getLength()));
    respmod.setResponseBody(body);
    final IcapResponse response = client.execute(respmod);
    client.disconnect();
    System.out.println(response.getStatusLine());
    System.out.println(response.getHeaderString());
    System.out.println("-----");
  }
}
