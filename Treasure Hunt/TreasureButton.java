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
import java.util.Random; //Allows access to the Random class.

/**
 *  |-----------------------------------------------------------|
 *  |                         TreasureButton                    |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |     The main purpose of       |       EmptyButton         |
 *  | distinguishing this class     |                           |
 *  | from the EmptyButton class    |  TreasureButtonListener   |
 *  |   is to have a different      |                           |
 *  |   response from a button      |       TreasureGame        |
 *  |      listener object          |                           |
 *  |                               |                           |
 *  |    Will set the icon of the   |                           |
 *  |     button to the treasure    |                           |
 *  |      image when clicked       |                           |
 *  |                               |                           |
 *  |     Assigns a point value     |                           |
 *  |    between 1 and 3 to the     |                           |
 *  |        treasure button        |                           |
 *  |-------------------------------+---------------------------|
 */

public class TreasureButton extends EmptyButton
{
    private ImageIcon treasureIcon; //An ImageIcon object to display the treasure button appearance
                                    //when it is disabled.
    private int pointValue; //To hold the number of points the treasure is worth.
    
    /**
     * A constructor for the TreasureButton class that calls the super class's constructor. Assigns
     * a point value for the button.
     */
    
    public TreasureButton()
    {
        super(); //Calls the super class's constructor. Will take on the appearance attributes of an
                 //empty button object.
                 
        treasureIcon = new ImageIcon("./images/treasure.png"); //Create a new ImageIcon object using the
                                                      //treasure image.
        pointValue = new Random().nextInt(3) + 1; //Set the point value equal to a random number between
                                                  //1 and 3.
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
     * Will override the setEnabled JButton method. Will first set the icon of the button to a treasure
     * icon before disabling it.
     * @override
     */
    
    public void setEnabled(boolean response)
    {
        setIcon(treasureIcon); //Set the icon to the treasure image.
        super.setEnabled(response); //Call the super class setEnabled method.
    }
    
    /**
     * Will return the point value of the button.
     */
    
    public int getPointValue()
    {
        return pointValue; //Return the point value of the treasure button.
    }
}
