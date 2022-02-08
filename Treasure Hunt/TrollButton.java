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

import java.awt.*; //Allows access to the AWT toolkit, including the ImageIcon class.
import javax.swing.*; //Allows access to the swing classes.

/**
 *  |-----------------------------------------------------------|
 *  |                         TrollButton                       |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |     The main purpose of       |       EmptyButton         |
 *  | distinguishing this class     |                           |
 *  | from the EmptyButton class    |    TrollButtonListener    |
 *  |   is to have a different      |                           |
 *  |   response from a button      |       TreasureGame        |
 *  |      listener object          |                           |
 *  |                               |                           |
 *  |    Will set the icon of the   |                           |
 *  |     button to the troll       |                           |
 *  |      image when clicked       |                           |
 *  |-------------------------------+---------------------------|
 */

public class TrollButton extends EmptyButton
{
    private ImageIcon trollIcon; //An ImageIcon object to display the troll button appearance
                                 //when it is disabled.
    
    /**
     * A constructor for the TrollButton class that calls the super class's constructor.
     */
    
    public TrollButton()
    {
        super(); //Calls the super class's constructor. Will take on the appearance attributes of an
                 //empty button object.
                 
        trollIcon = new ImageIcon("./images/troll.png"); //Create a new ImageIcon object using the
                                                //troll image.
        
    }
    
    /**
     * Will indicate if the button is an empty button.
     * @override
     */
    
    public boolean isEmpty()
    {
       return false; //Return that it is not empty.
    }
    
    /**
     * Will override the setEnabled JButton method. Will first set the icon of the button to a troll
     * image before disabling it.
     * @override
     */
    
    public void setEnabled(boolean response)
    {
        setIcon(trollIcon); //Set the icon to the treasure image.
        super.setEnabled(response); //Call the super class setEnabled method.
    }
}
