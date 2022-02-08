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
 *  |                     TrollButtonListener                   |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |     Will call the method      |        TrollButton        |
 *  |   for resetting gold found    |                           |
 *  |                               |       TreasureGame        |
 *  |      Will display a troll     |                           |
 *  |    icon on the button that    |                           |
 *  |         was clicked           |                           |
 *  |                               |                           |
 *  |      Disable the button       |                           |
 *  |       that was clicked        |                           |
 *  |-------------------------------+---------------------------|
 */

public class TrollButtonListener implements ActionListener
{
    private TreasureGame currentGame; //To hold the address of the TreasureGame object.
    private TrollButton clickedButton; //To hold the address of the TrollButton object that was clicked.
    
    /**
     * The constructor for the TreasureButtonListener class.
     * @param game The treasure game being played.
     * @param button The treasure button that was clicked.
     */   
    
    public TrollButtonListener(TreasureGame game, TrollButton button)
    {
        currentGame = game; //Set the current game equal to the parameter.
        clickedButton = button; //Set the address of the clicked button equal to the parameter.
    }
    
    /**
     * The method that will be called when a TreasureButton object is clicked.
     * @param click The button click that called the method.
     * @override
     */
    
    public void actionPerformed(ActionEvent click)
    {
        currentGame.foundTroll(); //Call the TreasureGame object's foundTroll method.
        
        ImageIcon trollIcon = new ImageIcon("./images/troll.png"); //Create a new ImageIcon object holding the
                                                          //troll picture. 
        
        clickedButton.setDisabledIcon(trollIcon); //Set the treasure icon to equal the troll icon. This
                                                  //will make the icon colored even when it is disabled.
        clickedButton.setEnabled(false); //Disable the button that was clicked.
        
        currentGame.updateResults(); //Call the updateResults method of the TreasureGame object.
    }
}
