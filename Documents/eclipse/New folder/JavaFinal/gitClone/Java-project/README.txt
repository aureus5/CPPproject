************************************
Java IV final project, KKcode.
Author: Guowei Wu
Date: 9.1.2015
Instructor: Dell Kronewitter
************************************
NOTE: main method is now in KKtest class.

Changes to improve the original codes:

1. Formatting has been corrected for the codes. Especially left curly braces should start at a new line.

2. Only one main method is kept in a new class KKtest. Both the two bulky main methods are divided into small organized units.

3. KKMultiServerThread is modified to implement Runnable instead of extending Thread. 

4. Create private instance fields in most of the classes to encapsulate data.

5. Use helper functions to display server or client message, make codes simple and straightforward.

6. Move hard coded clues and answers into text files. Load the files into arrayLists.

7. Use polymorphism to populate arrayLists with either clues or answers.

