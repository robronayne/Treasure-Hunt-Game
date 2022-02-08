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
 *  |                          EmptyButton                      |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |Creates the default appearance |     TreasureButton        |
 *  | of empty and treasure button  |                           |
 *  |            objects            |   EmptyButtonListener     |                         
 *  |                               |                           |
 *  | Allows polymorphic instances  |      TreasureGame         |
 *  |    of treasure buttons to     |                           |
 *  |     be added to an empty      |                           |
 *  |        button array           |                           |
 *  |-------------------------------+---------------------------|
 */

public class EmptyButton extends JButton
{
    private ImageIcon questionIcon; //An ImageIcon object to display the default button appearance.
    
    /**
     * A constructor for the EmptyButton class that sets the dimensions and the image of all empty buttons
     * that will be created.
     */
    
    public EmptyButton()
    {
        setPreferredSize(new Dimension(60,60)); //Make the button object's preferred dimensions 60 x 60.
        
        questionIcon = new ImageIcon("./images/question.png"); //Create a new ImageIcon object holding the question picture.
        
        setIcon(questionIcon); //Set the image displayed on the button to be the question image.
    }
    
    /**
     * Will indicate if the button is an empty button.
     */
    
    public boolean isEmpty()
    {
       return true; //Return that it is empty.
    }
    
}
