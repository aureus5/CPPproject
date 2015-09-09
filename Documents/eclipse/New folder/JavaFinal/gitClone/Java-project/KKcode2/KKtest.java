package KKcode2;

import java.io.IOException;

/**
 * <h1>A knockknock server-client application</h1>
 * <p>
 * This program starts a GUI window with buttons to start & stop a knockknock
 * server and one or more clients. The server and clients are able to
 * communicate through the GUI window.
 * <p>
 * Each client is started on a different thread. Upon starting, clients will get
 * a "knock knock!" message from the server, and will receive knockknock jokes
 * by saying "Who's there?".
 * <p>
 * Different clients gets different initial knockknock jokes. The jokes run a
 * circular lists so will go back to the first one in file after reading the
 * last one. There are 20 jokes in total.
 * 
 * @author Guowei Wu
 * @version 2.0
 * @since 2015.9.1
 */
public class KKtest
{
    /**
     * Start the knockknock GUI window
     * 
     * @param args
     *            no arguments needed
     * @throws IOException
     *             throws exception if connection fails
     */
    public static void main(String[] args) throws IOException
    {
        System.out.println("Server starting...");
        new KKMultiServer().startApplication();
    }
}
