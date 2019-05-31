import java.io.*;
import java.net.*;
import java.util.*;

public class MainServer
{
  public static void main(String[] paramArrayOfString)
  {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter TCP or UDP: ");
    String str = scan.next().toUpperCase();

    System.out.print("Enter Port: ");
    int i = scan.nextInt();
    scan.close();
    try
    {
      System.out.println("IP Address: " + InetAddress.getLocalHost().getHostAddress());
      System.out.println("IP Hostname: " + InetAddress.getLocalHost().getHostName());
    }
    catch (UnknownHostException localUnknownHostException) {
      System.out.println("Error: " + localUnknownHostException);
    }
    
    if (str.equals("TCP")) {
      System.out.println("Running TCP on Port " + i);
      TCPServer tcps = new TCPServer(i);
    } else if (str.equals("UDP")) {
      System.out.println("Running UDP on Port " + i);
      UDPServer udps = new UDPServer(i);
    } else {
      System.out.println("Unknown socket");
      System.exit(0);
    }
  }
}