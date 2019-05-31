

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

public class UDPServer
{
  private DatagramSocket socket;

  public UDPServer(int port)
  {
    byte[] byteArray = new byte[256];
    Thread[] threads = new Thread[10];
    try
    {
      this.socket = new DatagramSocket(port);
      for (int i = 0; i < 10; i++) {
        Handler local = new Handler(this.socket);
        threads[i] = local;
        local.start();
      }
      for (int i = 0; i < 10; i++) {
        threads[i].join();
      }

      this.socket.close();
    }
    catch (IOException localIOException) {
      System.out.println(localIOException);
    }
    catch (InterruptedException localInterruptedException) {
      System.out.println(localInterruptedException);
    }
  }

  private class Handler extends Thread
  {
    private DatagramSocket socket;

    public Handler(DatagramSocket sock)
    {
     
      this.socket = sock;
    }

    public void run()
    {
      byte[] byteArray1 = new byte[256];

      DatagramPacket packet = new DatagramPacket(byteArray1, byteArray1.length);

      SimpleDateFormat formatd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      try
      {
        while (true)
        {
          this.socket.receive(packet);
          Date localDate = new Date();
          String date = formatd.format(localDate);

          String str = date + " " + new String(packet
            .getData(), 0, packet.getLength());

          System.out.println(str + " client " + packet.getAddress());
          byte[] byteArray2 = str.getBytes();

          DatagramPacket packet2 = new DatagramPacket(byteArray2, byteArray2.length, packet
            .getAddress(), packet.getPort());
          this.socket.send(packet2);
        }
      }
      catch (EOFException localEOFException)
      {
        System.out.println("Disconnect");
      }
      catch (IOException localIOException)
      {
        System.out.println(localIOException);
      }

      System.out.println("Closing server thread");
    }
  }
}