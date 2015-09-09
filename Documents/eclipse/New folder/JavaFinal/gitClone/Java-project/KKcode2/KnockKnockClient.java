package KKcode2;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 * Client thread that starts a client GUI window, connects to and communicate
 * with server.
 * 
 * @author Guowei Wu
 *
 */
public class KnockKnockClient implements Runnable
{
    private JFrame clientFrame = null;
    private Socket kkSocket = null;
    private int clientNumber;
    private PrintWriter out;
    private BufferedReader in;
    private String laptopName;
    private JTextArea textAreaClient;
    private JTextField textField;

    /**
     * Constructor, initiate the GUI for client.
     * 
     * @param clientNumber
     *            the number of the client.
     */
    public KnockKnockClient(int clientNumber)
    {
        this.clientNumber = clientNumber + 1; //first client will be #1, and so on.
        clientFrame = new JFrame("KnockKnock client #" + this.clientNumber);
        clientFrame.setLocation(450 + clientNumber * 20, 150 + clientNumber * 60); //clients thus not hidden
        laptopName = "127.0.0.1"; // localhost address
        textField = new JTextField();
        textField.addActionListener(new ActionListener()  
        {
            public void actionPerformed(ActionEvent event)
            {
                sendTextToServer(event.getActionCommand());
                textField.setText("");
            }
        });
        clientFrame.add(textField, BorderLayout.NORTH);
        textAreaClient = new JTextArea();
        clientFrame.add(new JScrollPane(textAreaClient), BorderLayout.CENTER);
        clientFrame.setSize(500, 225);
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientFrame.setVisible(true);
    }

    /**
     * The client thread connects to and then interacts with server.
     */
    @Override
    public void run()
    {
        //client threads try to connect to server socket, then retrieve data streams.
        try
        {
            kkSocket = new Socket(laptopName, 4444);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e)
        {
            System.err.println("Don't know about host: " + laptopName + ".");
            System.exit(1);
        } catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to: " + laptopName + ".");
            System.exit(1);
        }

        String serverMsg;

        // displaying messages sent by server
        try
        {
            while ((serverMsg = in.readLine()) != null)
            {
                displayClientMessage(serverMsg);
                if (serverMsg.equals("Bye."))
                {
                    clientFrame.setVisible(false);
                    break;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Client #" + clientNumber + " is stopped");
        }
    }

    /**
     * Method to send string to server
     * @param st1 string to be sent to server
     */
    public void sendTextToServer(String st1)
    {
        out.println(st1);
    }

    /**
     * Helper function to display message on client text area.
     * 
     * @param msg
     *            message to be displayed on client GUI.
     */
    public void displayClientMessage(final String msg)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                textAreaClient.append("\n" + msg);
            }
        });
    }

    /**
     * Method to stop client threads and destroy clients.
     */
    public void close()
    {
        try
        {
            out.close();
            in.close();
            kkSocket.close();
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        clientFrame.setVisible(false);
        clientFrame.dispose();
    }
}
