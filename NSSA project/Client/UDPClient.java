

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPClient
{
  private BufferedReader input = null;
  private DatagramSocket socket;
  private InetAddress address;

  public UDPClient(String paramString, int port)
  {
    int i = 0;
    try
    {
      this.socket = new DatagramSocket();

      this.address = InetAddress.getByName(paramString);

      this.input = new BufferedReader(new InputStreamReader(System.in));
    }
    catch (UnknownHostException localUnknownHostException)
    {
      System.out.println(localUnknownHostException);
    }
    catch (IOException localIOException1)
    {
      System.out.println(localIOException1);
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

          byte[] byteArray = inp.getBytes();
          DatagramPacket packet = new DatagramPacket(byteArray, byteArray.length, this.address, port);

          this.socket.send(packet);

          byteArray = new byte[256];
          packet = new DatagramPacket(byteArray, byteArray.length);

          this.socket.receive(packet);

          String str = new String(packet.getData(), 0, packet.getLength());
          System.out.println(str);
          if(i==1)
          {this.input.close();
            this.socket.close();
            }
        }
        catch (EOFException localEOFException)
        {
          System.out.println("EOF");
        }
        catch (IOException localIOException2)
        {
          System.out.println(localIOException2);
        }
      }
    }

   }
}