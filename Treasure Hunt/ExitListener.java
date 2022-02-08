/**
 * Contributor(s): 
 * Rob Ronayne; 5653937
 * 
 * Sources:
 * 
 * Starting Out with Java, 6th edition, T. Gaddis
 * http://MyProgrammingLab.com: Starting Out with Java, 6th edition
 *
 * Version: 12/10/2019 
 */

import java.awt.event.*; //Allows access to the ActionListener class.
import javax.swing.*; //Allows access to the swing classes.

/**
 *  |-----------------------------------------------------------|
 *  |                          ExitListener                     |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |   Exit the program when the   |       TreasureGame        |
 *  |    exit button is clicked     |                           |
 *  |-------------------------------+---------------------------|
 */
   
public class ExitListener implements ActionListener
{
    /**
     * Purpose: A method to be executed when the 'exit' menu item is clicked.
     * Signature: Takes an ActionEvent, no return.
     * Examples:
     * actionPerformed(new ActionEvent)
     */
      
    public void actionPerformed(ActionEvent click)
    {
        System.exit(0); //Exit the program.
    }
}
