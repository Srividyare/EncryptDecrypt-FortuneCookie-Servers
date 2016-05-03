import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client
{
  public static void main(String [] args)
  {
    String serverName = args[0];
    Socket client = null;
    // int port = Integer.parseInt(args[0]);
    try
    {


      Scanner s = new Scanner(System.in);
      while(true) {
        System.out.println("Please enter: \n 1 to get Fortune Cookies"
            + "\n 2 to encrypt/decrypt a string" + "\n 3 to close.");
        String input = s.nextLine();
        
        System.out.println("Connecting to " + serverName
              + " on port " + 9996);
          client = new Socket(serverName, 9996);
          System.out.println("Just connected to "
              + client.getRemoteSocketAddress());
          OutputStream outToServer = client.getOutputStream();
          DataOutputStream out =
              new DataOutputStream(outToServer);
          InputStream inFromServer = client.getInputStream();
          DataInputStream in =
              new DataInputStream(inFromServer);
             String serv_addr = in.readUTF();
          
        
        // Processes Fortune Cookie logic
        if (input.equalsIgnoreCase("1")) {
              
          System.out.println("Connecting to " + serv_addr
              + " on port " + 9998);
          client = new Socket(serv_addr, 9998);
          System.out.println("Just connected to "
              + client.getRemoteSocketAddress());
          OutputStream outToServer = client.getOutputStream();
          DataOutputStream out =
              new DataOutputStream(outToServer);
          InputStream inFromServer = client.getInputStream();
          DataInputStream in =
              new DataInputStream(inFromServer);
          System.out.println("Please enter the number of cookie you want");
          String inputToServer = s.nextLine();

          if (inputToServer.equalsIgnoreCase("close")) {
            client.close();
            System.exit(0);
          } else {
         
          out.writeUTF(inputToServer);

          System.out.println(":" + in.readUTF());
          client.close();
          }
        }
        
        // Processes Encrypt Decrypt logic
        if (input.equalsIgnoreCase("2")) {

          System.out.println("Connecting to " + serv_addr
              + " on port " + 9997);
          client = new Socket(serv_addr, 9997);
          System.out.println("Just connected to "
              + client.getRemoteSocketAddress());
          System.out.println("Enter your string that needs to be encrypted/ decrypted. \n"
              + "For encryption prefix your string with e \n"
              + "For decryption prefix your string with d");
          OutputStream outToServer = client.getOutputStream();
          DataOutputStream out =
              new DataOutputStream(outToServer);
          InputStream inFromServer = client.getInputStream();
          DataInputStream in =
              new DataInputStream(inFromServer);
          while (true) {
            String inputToServer = s.nextLine();
            if (inputToServer.equalsIgnoreCase("close")) {
              client.close();
              break;
            } else {
              out.writeUTF(inputToServer);
              System.out.println(":" + in.readUTF());
            }
          } 
        }
        if (input.equalsIgnoreCase("3")) {
          if (client != null) {
            client.close();
            System.exit(0);
          }
          System.exit(0);
        }
      } 

      //client.close();
    }catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
