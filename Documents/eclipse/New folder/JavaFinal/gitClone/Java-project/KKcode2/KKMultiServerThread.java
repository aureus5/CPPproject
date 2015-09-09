package KKcode2;

import java.net.*;
import java.io.*;
import javax.swing.*;

/**
 * Class that enables server to communicate with client.
 * 
 * @author Guowei Wu
 *
 */
public class KKMultiServerThread implements Runnable
{
    private Socket socket = null;
    private JTextArea textArea = null;
    private PrintWriter out;
    private BufferedReader in;
    int clientNumber;
    boolean listening = true;

    public KKMultiServerThread(Socket socket, JTextArea textArea, int clientNumber)
    {
        this.socket = socket;
        this.textArea = textArea;
        this.clientNumber = clientNumber;
    }

    /**
     * Server thread that retrieves messages from client, processes the
     * messages, and sends clues or jokes to client.
     * 
     * @exception IOException
     *                exception if connection failed.
     */
    @Override
    public void run()
    {
        displayServerMessage("Start client #" + clientNumber);
        try
        {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine, outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null)
            {
                displayServerMessage("From client #" + clientNumber + ": " + inputLine);
                outputLine = kkp.processInput(inputLine); // kkprotocol determines responses
                out.println(outputLine); // write data in outputstream
                if (outputLine.equals("Bye.")) //should be "Bye." instead of "Bye"
                {
                    displayServerMessage("Client #" + clientNumber + "quits.");
                    out.println("Bye.");  //signal client GUI to hide and client thread to stop
                    break;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to display message on server GUI.
     * 
     * @param message
     *            message to append to server text area.
     */
    public void displayServerMessage(final String message) // add final modifier
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                textArea.append("\n" + message);
            }
        });
    }

    /**
     * Method to close server thread
     */
    public void close()
    {
        try
        {
            out.close();
            in.close();
            socket.close();
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
