

import java.io.*;
import java.net.*;


public class TCPClient
{
  private Socket socket = null;

  private DataOutputStream out = null;

  private DataInputStream in = null;

  private BufferedReader input = null;

  public TCPClient(String ipAddr, int port)
  {
    int i = 0;
    try
    {
      this.socket = new Socket(ipAddr, port);

      this.out = new DataOutputStream(this.socket.getOutputStream());
      this.in = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));

      this.input = new BufferedReader(new InputStreamReader(System.in));
    }
    catch (ConnectException localConnectException)
    {
      System.out.println(localConnectException);
      return;
    }
    catch (UnknownHostException localUnknownHostException)
    {
      System.out.println(localUnknownHostException);
      return;
    }
    catch (IOException localIOException1) {
      System.out.println(localIOException1);
      return;
    }
    while (true) {
      if (i == 0) {
        try
        {
          String inp = this.input.readLine();

          if (inp.equals("end")) {
            i = 1;
            System.exit(0);
          }

          this.out.writeUTF(inp);

          inp = this.in.readUTF();
          System.out.println(inp);
          if(i==1)
          { this.input.close();
            this.out.close();
            this.in.close();
            this.socket.close();}

        }
        catch (EOFException localEOFException)
        {
          System.out.println("Disconnected");
        }
        catch (IOException localIOException2)
        {
          System.out.println(localIOException2);
        }
      }
    }

      }
}