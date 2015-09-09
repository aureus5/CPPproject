package KKcode2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The protocol process input from client, and decide how to interact with
 * client. It randomly picks a joke for new coming client, and will run a
 * circular list of the available jokes loaded from local files.
 * 
 * @author Guowei Wu
 * @see KKMultiServerThread
 * @see KnockKnockClient
 */
public class KnockKnockProtocol
{
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 20;

    private int state = WAITING;
    private int currentJoke;

    private List<String> clues;
    private List<String> answers;

    private String clueFile = "clues.txt";
    private String answerFile = "answers.txt";

    /**
     * Constructor that generate a random index number for the arrayList of
     * jokes. Each client thus different jokes in the beginning.
     */
    public KnockKnockProtocol()
    {
        Random random = new Random();
        currentJoke = random.nextInt(NUMJOKES);
    }

    /**
     * The protocol to process client input. Decides how to interact with
     * client.
     * 
     * @param theInput
     *            input message from client.
     * @return returns a string that will be be sent to client.
     */
    public String processInput(String theInput)
    {
        String theOutput = null;
        populateJokes();

        if (state == WAITING)
        {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK)
        {
            if (theInput.equalsIgnoreCase("Who's there?"))
            {
                theOutput = clues.get(currentJoke);
                state = SENTCLUE;
            } else
            {
                theOutput = "You're supposed to say \"Who's there?\"! " + "\nTry again. Knock! Knock!";
            }
        } else if (state == SENTCLUE)
        {
            if (theInput.equalsIgnoreCase(clues.get(currentJoke) + " who?"))
            {
                theOutput = answers.get(currentJoke) + " \nWant another? (y/n)";
                state = ANOTHER;
            } else
            {
                theOutput = "You're supposed to say \"" + clues.get(currentJoke) + " who?\""
                        + "! \nTry again. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER)
        {
            if (theInput.equalsIgnoreCase("y"))
            {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                {
                    currentJoke = 0;
                } else
                {
                    currentJoke++;
                }
                state = SENTKNOCKKNOCK;
            } else
            {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }

    /**
     * Method to read clues and answers from files to arrayLists.
     */
    public void populateJokes()
    {
        clues = new ArrayList<String>();
        answers = new ArrayList<String>();
        populateLists(clues, clueFile);
        populateLists(answers, answerFile);
    }

    /**
     * Helper function to read clues and answers from files to lists.
     * 
     * @param list
     *            the list to hold clues or files.
     * @param file
     *            the file to read from for clues/answers.
     */
    public void populateLists(List<String> list, String file)
    {
        try
        {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine())
            {
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found\n" + e.toString());
        }
    }
}
