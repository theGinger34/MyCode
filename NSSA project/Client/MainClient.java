

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;


public class MainClient
{
  public static void main(String[] paramArrayOfString)
  {
    String addr = "";
    Scanner localScanner = new Scanner(System.in);
    System.out.print("Enter the name or IP address of the Server: ");
    String ipAddr = localScanner.next().toUpperCase();
    System.out.print("Enter TCP or UDP: ");
    String socket = localScanner.next().toLowerCase();

    System.out.print("Enter Port: ");
    int i = localScanner.nextInt();

    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date localDate = new Date();
    String date = localSimpleDateFormat.format(localDate);
    try
    {
      InetAddress localInetAddress = InetAddress.getByName(ipAddr);
      addr = localInetAddress.getHostAddress();
    }
    catch (UnknownHostException localUnknownHostException) {
      System.out.println("Error resolving hostname " + localUnknownHostException);
      System.exit(-1);
    }

    System.out.println("Connecting to " + ipAddr + " with IP address " + addr + " using " + socket + " on Port " + i + " at " + date);
    Object localObject;
    if (socket.equals("TCP")) {
      localObject = new TCPClient(ipAddr, i);
    } else if (socket.equals("UDP")) {
      localObject = new UDPClient(ipAddr, i);
    } else {
      System.out.println("Unknown socket");
      System.exit(0);
    }

    localScanner.close();
  }
}