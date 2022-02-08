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
 *  |                     TreasureButtonListener                |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |     Will call the method      |      TreasureButton       |
 *  |   for treasure being found    |                           |
 *  |                               |       TreasureGame        |
 *  |    Will display a treasure    |                           |
 *  |    icon on the button that    |                           |
 *  |         was clicked           |                           |
 *  |                               |                           |
 *  |      Disable the button       |                           |
 *  |       that was clicked        |                           |
 *  |-------------------------------+---------------------------|
 */

public class TreasureButtonListener implements ActionListener
{
    private TreasureGame currentGame; //To hold the address of the TreasureGame object.
    private TreasureButton clickedButton; //To hold the address of the TreasureButton object that was clicked.
    
    /**
     * The constructor for the TreasureButtonListener class.
     * @param game The treasure game being played.
     * @param button The treasure button that was clicked.
     */   
    
    public TreasureButtonListener(TreasureGame game, TreasureButton button)
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
        currentGame.foundTreasure(clickedButton.getPointValue()); //Call the TreasureGame object's foundTreasure method.
        
        ImageIcon treasureIcon = new ImageIcon("./images/treasure.png"); //Create a new ImageIcon object holding the
                                                                //treasure picture. 
        
        clickedButton.setDisabledIcon(treasureIcon); //Set the treasure icon to equal the empty icon. This
                                                     //will make the icon colored even when it is disabled.
        clickedButton.setEnabled(false); //Disable the button that was clicked.
        
        currentGame.updateResults(); //Call the updateResults method of the TreasureGame object.
    }
}
