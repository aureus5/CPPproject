package KKcode2;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.util.*;

/**
 * Class that implement the GUI interface for the program. Implementation of
 * starting and stopping server and client threads.
 * 
 */
public class KKMultiServer implements Runnable
{
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private JFrame frame = null;
    private JTextArea textArea; // display messages from
                                // server/client/application
    private KKMultiServerThread connection = null; // one server thread for each
                                                   // client
    private Thread serverThread = null; // each client is assigned a distinct
                                        // serverThread
    private Thread thisThread = null; // thread to start this GUI platform
    private int clientNumber = 0; // each client is assigned a number
    private boolean listening = true;
    private boolean serverOn = false;
    private List<KnockKnockClient> clientPool; // stores client objects;
    private List<Thread> serverThreadPool; // Stores serverThread. Interrupt all
                                           // threads in
    private List<Thread> clientThreadPool; // the list to stop all server
                                           // threads

    /**
     * The constructor starts the GUI interface for the app.
     */
    public KKMultiServer()
    {
        frame = new JFrame("Knock knock application");
        JButton button1 = new JButton("Start Server");
        StartServerHandler startServerHandler = new StartServerHandler();
        button1.addActionListener(startServerHandler);

        JButton button2 = new JButton("Start Client");
        StartClientHandler startClientHandler = new StartClientHandler();
        button2.addActionListener(startClientHandler);

        JButton button3 = new JButton("Stop Server");
        StopServerHandler stopServerHandler = new StopServerHandler();
        button3.addActionListener(stopServerHandler);

        JButton button4 = new JButton("Stop clients");
        StopClientHandler stopClientHandler = new StopClientHandler();
        button4.addActionListener(stopClientHandler);

        JPanel panel = new JPanel();
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        frame.addWindowListener(new WindowAdapter() // if window is closed,
                                                    // safely exit the program.
        {
            public void windowClosed(WindowEvent windowevent)
            {
                try
                {
                    listening = false;
                    serverSocket.close();
                    System.exit(0);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientPool = new ArrayList<KnockKnockClient>();
        serverThreadPool = new ArrayList<Thread>();
        clientThreadPool = new ArrayList<Thread>();
    }

    /**
     * Method to start the GUI window(not server thread yet).
     * 
     * @return void
     */
    public void startApplication()
    {
        frame.setVisible(true);
    }

    /**
     * Start the server and waits for client connection. Each client will be
     * assigned a different thread from the server.
     * 
     * @return void
     */
    @Override
    public void run()
    {
        displayServerMessage("Start connecting to client. \nWaiting...");
        try
        {
            serverSocket = new ServerSocket(4444);

        } catch (IOException e1)
        {
            System.err.println("Could not listen on port: 4444.");
            System.exit(-1);
        }

        serverOn = true;
        // connect knockknock client, handles exception.
        try
        {
            while (listening)
            {
                socket = serverSocket.accept();
                clientNumber++;
                connection = new KKMultiServerThread(socket, textArea, clientNumber);
                serverThread = new Thread(connection);
                serverThreadPool.add(serverThread);
                serverThread.start();
            }
        } catch (IOException e2)
        {
            e2.printStackTrace();
        }
    }

    /**
     * Method to display message on the server text area.
     * 
     * @param message
     *            message to be displayed on the server
     */
    public void displayServerMessage(final String message) // add final modifier
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                textArea.append("\n" + message);
            }
        });
    }

    /**
     * Class that deals with server button action event.
     *
     */
    private class StartServerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (!serverOn)
            {
                thisThread = new Thread(KKMultiServer.this);
                thisThread.start();  //open server socket, listens to client
            }
        }
    }

    /**
     * Pressing client button starts new client thread.
     *
     */
    private class StartClientHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (serverOn)
            {
                KnockKnockClient client = new KnockKnockClient(clientNumber);
                clientPool.add(client);
                Thread clientThread = new Thread(client);
                clientThreadPool.add(clientThread);
                clientThread.start();
            }
        }
    }

    /**
     * Private class to stop server upon button clicking. Works by interrupting
     * all server threads. All server threads will be stored in a list upon
     * creation.
     * 
     * @author GuoweiWu
     *
     */
    private class StopServerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            displayServerMessage("Server is stopped. Existing clients(#<" + clientNumber + ") closed");
            serverOn = false;
            for (KnockKnockClient client : clientPool)
            {
                client.close();
            }
            try
            {
                serverSocket.close();
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }

    private class StopClientHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            displayServerMessage("All current clients are shutting down...");
            for (KnockKnockClient client : clientPool)
            {
                client.close();
            }
        }
    }
}
