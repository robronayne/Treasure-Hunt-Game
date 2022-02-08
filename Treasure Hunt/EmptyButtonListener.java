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
 *  |                         EmptyButtonListener               |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |     Will call the method      |       EmptyButton         |
 *  |   for nothing being found     |                           |
 *  |                               |       TreasureGame        |
 *  |     Will display an empty     |                           |
 *  |    icon on the button that    |                           |
 *  |         was clicked           |                           |
 *  |                               |                           |
 *  |      Disable the button       |                           |
 *  |       that was clicked        |                           |
 *  |-------------------------------+---------------------------|
 */

public class EmptyButtonListener implements ActionListener
{
    private TreasureGame currentGame; //To hold the address of the TreasureGame object.
    private EmptyButton clickedButton; //To hold the address of the EmptyButton object that was clicked.
    
    /**
     * The constructor for the EmptyButtonListener class.
     * @param game The treasure game being played.
     * @param button The empty button that was clicked.
     */   
    
    public EmptyButtonListener(TreasureGame game, EmptyButton button)
    {
        currentGame = game; //Set the current game equal to the parameter.
        clickedButton = button; //Set the address of the clicked button equal to the parameter.
    }
    
    /**
     * The method that will be called when a EmptyButton object is clicked.
     * @param click The button click that called the method.
     * @override
     */
    
    public void actionPerformed(ActionEvent click)
    {
        currentGame.foundNothing(); //Call the TreasureGame object's foundNothing method.
        
        ImageIcon emptyIcon = new ImageIcon("./images/empty.png"); //Create a new ImageIcon object holding the
                                                          //empty picture.
        
        clickedButton.setDisabledIcon(emptyIcon); //Set the disabled icon to equal the empty icon. This
                                                  //will make the icon colored even when it is disabled.
        clickedButton.setEnabled(false); //Disable the button that was clicked.
        
        currentGame.updateResults(); //Call the updateResults method of the TreasureGame object.
    }
}
