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
 *  |                         AboutListener                     |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |   Display relevant developer  |       TreasureGame        |
 *  |    information when about     |                           | 
 *  |          is clicked           |                           |
 *  |-------------------------------+---------------------------|
 */
   
public class AboutListener implements ActionListener
{
    /**
     * Purpose: A method to be executed when the 'about' menu item is clicked.
     * Signature: Takes an ActionEvent, no return.
     * Examples:
     * actionPerformed(new ActionEvent)
     */
      
    public void actionPerformed(ActionEvent click)
    {
        //Show the developer information in a message dialog.
        JOptionPane.showMessageDialog(null, "Created by Rob Ronayne. Developed and completed during " +
                                            "the fall 2019 semester of CISC 190.");
    }
}
