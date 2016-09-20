/*
 * Copyright 2016 Jean-Bernard van Zuylen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primeoservices.net.icap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;

import org.apache.commons.net.SocketClient;
import org.primeoservices.net.icap.messages.IcapRequest;
import org.primeoservices.net.icap.messages.IcapResponse;

public class IcapClient
{
  private IcapSocketClient client;

  public IcapClient()
  {
    this.client = new IcapSocketClient();
  }

  /**
   * Executes the specified request by this client
   * 
   * @param request the request to be executed
   * 
   * @throws Exception
   */
  public IcapResponse execute(final IcapRequest request) throws Exception
  {
    return this.client.execute(request);
  }

  /**
   * Disconnects this client
   *
   * @throws IOException
   */
  public void disconnect() throws IOException
  {
    this.client.disconnect();
  }

  private class IcapSocketClient extends SocketClient
  {
    private BufferedReader reader;

    private BufferedWriter writer;

    @Override
    protected void _connectAction_() throws IOException
    {
      super._connectAction_();
      this.reader = new BufferedReader(new InputStreamReader(_input_));
      this.writer = new BufferedWriter(new OutputStreamWriter(_output_));
    }

    public IcapResponse execute(final IcapRequest request) throws Exception
    {
      // Connect to service
      final URI uri = request.getRequestLine().getURI();
      this.connect(uri.getHost(), uri.getPort());
      // Write request
      request.write(this.writer);
      this.writer.flush();
      // Read response (currently only status line and headers, skip the rest)
      return IcapResponse.read(this.reader);
    }

    @Override
    public void disconnect() throws IOException
    {
      super.disconnect();
      this.reader = null;
      this.writer = null;
    }
  }
}
