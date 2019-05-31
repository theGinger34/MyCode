import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

public class TCPServer
{
  private ServerSocket server = null;

  public TCPServer(int port)
  {
    DataInputStream input = null;
    DataOutputStream output = null;
    Socket localSocket = null;
    try
    {
      this.server = new ServerSocket(port);
    } catch (IOException localIOException1) {
      System.out.println(localIOException1);
      return;
    }
    while (true)
    {
      try {
        localSocket = this.server.accept();

        input = new DataInputStream(new BufferedInputStream(localSocket.getInputStream()));
        output = new DataOutputStream(localSocket.getOutputStream());
      } catch (IOException localIOException2) {
        System.out.println(localIOException2);
        break;
      }

      try
      {
        Handler local = new Handler(localSocket, input, output);

        local.start();
      } catch (Exception localException) {
        System.out.println(localException);
      }
    }
  }

  private class Handler extends Thread
  {
    private Socket socket = null;

    private DataInputStream in = null;

    private DataOutputStream out = null;

    public Handler(Socket sock, DataInputStream input, DataOutputStream output)
    {
      this.socket = sock;
      this.in = input;
      this.out = output;
    }

    public void run()
    {
      long l = Thread.currentThread().getId();

      String str= this.socket.getRemoteSocketAddress().toString();

      SimpleDateFormat formatd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date localDate = new Date();
      String date = formatd.format(localDate);

      System.out.println(date + " New connection from: " + str);
      try
      {
        while (true)
        {
          String clientInput = this.in.readUTF();
          localDate = new Date();
          date = formatd.format(localDate);

          String finalm = new String(date + " " + clientInput);
          System.out.println("Sending to client: " + str + " " + finalm);

          this.out.writeUTF(finalm);

          if (clientInput.equals("end")) {
            break;
          }
        }
      }
      catch (EOFException localEOFException)
      {
        System.out.println("Disconnect");
      }
      catch (IOException localIOException1)
      {
        System.out.println(localIOException1);
      }

      System.out.println("Server thread " + l + " is done");
      try
      {
        this.in.close();
        this.out.close();
        this.socket.close();
      }
      catch (IOException localIOException2) {
        System.out.println(localIOException2);
      }
    }
  }
}