/**
 * Contributor(s): 
 * Rob Ronayne; 5653937
 * 
 * Sources:
 * 
 * Starting Out with Java, 6th edition, T. Gaddis
 * http://MyProgrammingLab.com: Starting Out with Java, 6th edition
 * 
 * Setting Background Images in JFrame
 * https://stackoverflow.com/questions/1064977/setting-background-images-in-jframe
 * 
 * Version: 12/10/2019
 */

import java.awt.*; //Allows access to the AWT toolkit, including Font, Color, Image and
                   //Layout classes
import javax.swing.*; //Allows access to the swing classes.
import java.awt.event.*; //Allows access to the ActionListener class.
import java.util.Random; //Allows access to the Random class.

/**
 *  |-----------------------------------------------------------|
 *  |                         TreasureGame                      |
 *  |-----------------------------------------------------------|
 *  |      Responsibilities         |   Collaborating Classes   |
 *  |-------------------------------+---------------------------|
 *  |   Keep track of treasure      |   EmptyButtonListener     |
 *  |   found and the attempts      |                           |
 *  |       made by the user        |  TreasureButtonListener   |
 *  |                               |                           |
 *  |  Display the user interface   |    TrollButtonListener    |
 *  |                               |                           |
 *  |    Will inform user of the    |       EmptyButton         |
 *  |        game's results         |                           |
 *  |                               |       TrollButton         |
 *  |                               |                           |
 *  |                               |     TreasureButton        |
 *  |                               |                           |
 *  |                               |      ExitListener         |
 *  |                               |                           |
 *  |                               |      AboutListener        |
 *  |-------------------------------+---------------------------|
 */

public class TreasureGame extends JFrame
{
    private final int TREASURE_TOTAL = 20, //The total amount of treasure on the board.
                      TROLL_TOTAL = 20, //The total amount of trolls on the board.
                      ATTEMPTS_ALLOWED = 50; //The maximum number of tries the user is allowed.
   
    private JMenuBar menuBar; //A menu bar to hold a file menu object.
    private JMenu fileMenu; //A file menu object to contain a menu item.
    private JMenuItem aboutItem, //A menu item to hold developer information.
                      exitItem; //A menu item to exit the program.
    
    private int treasureFound, //To hold the amount of treasure found.
                treasureLeft, //To hold the amount of remaining treasure.
                pointTotal, //To hold the number of points earned.
                triesLeft; //To hold the number of remaining tries.
                
    private String moveResult; //To hold the result of the last move.
    
    private JPanel titlePanel, //A panel object to contain the title of the game.
                   infoPanel, //A panel object to contain the amount of treasure found, treasure left,
                              //and number of remaining tries.
                   buttonPanel, //A panel object with a grid layout holding the button objects.
                   legendPanel, //A panel object that will display the meaning of the various icons.
                   lastMovePanel; //A panel object to display the last move made by the user.           
    
    private final String TREASURE_FOUND_HEADER = "Treasure Found     ", //A header for the treasure found label.
                         TREASURE_LEFT_HEADER = "Treasure Left     ", //A header for the treasure found label.
                         POINT_TOTAL_HEADER = "Point Total     ", //A header for the points earned.
                         TRIES_LEFT_HEADER = "Tries Left     "; //A header for the tries left label.
                         
    private JLabel titleLabel, //Will display title of the game.
                   treasureFoundLabel, //Will display a caption before the amount of treasure found. 
                   treasureLeftLabel, //Will display a caption before the amount of treasure remaining.
                   pointTotalLabel, //Will display a caption before the points earned.
                   triesLeftLabel, //Will display a caption before the number of tries the user has left.
                   lastMoveLabel, //Will display a caption before the last move made by the user.
                   legendLabel, //Will display the title of the legend area.
                   nothingIconLabel, //Will display the nothing icon.
                   nothingDescriptionLabel, //Will display a caption describing the nothing icon.
                   treasureIconLabel, //Will display the treasure icon.
                   treasureDescriptionLabel, //Will display a caption describing the treasure icon.
                   trollIconLabel, //Will display the troll icon.
                   trollDescriptionLabel; //Will display a caption describing the troll icon.
    
    private JTextField lastMoveTextField; //Used to display the result of the last move made by user. 
    
    private Font headerFont, //Will hold the font for the text on the title label component.
                 bodyFont, //Will hold the font for text that appears on JLabel and JTextField objects.          
                 legendFont; //Will hold the font for text that appears on JLabel objects in the legend.
                 
    private final Color tan = new Color(255,222,173); //A tan background color for the last move label.
    
    private EmptyButton[] buttonArray; //An array of EmptyButtons that will hold both EmptyButton and 
                                       //TreasureButton objects. Will be used to populate button panel.
    
    private final int GRID_SIZE = 100; //The grid component size of the 10 x 10 button matrix is 100.
    
    /**
     * The constructor for the treasure game class. Initializes the treasure found to zero, and
     * the remaining treasure to the treasure total. Initializes the number of remaining tries to
     * the maximum number of attempts allowed, and set the previous move result to be blank.
     */
    
    public TreasureGame()
    {
        treasureFound = 0; //Set the treasure found equal to zero.
        pointTotal = 0; //Set the point total to zero.
        treasureLeft = TREASURE_TOTAL; //Set the remaining treasure equal to the total amount hidden.
        triesLeft = ATTEMPTS_ALLOWED; //Set the number of tries left equal to the number allowed.
        moveResult = ""; //Set the last move result to be a blank string.
    }
    
    /**
     * Calls the reduceNumberOfTries method, and sets the last move result to depict that no
     * treasure was found.
     */
    
    public void foundNothing()
    {
        triesLeft--; //Decrement number of remaining tries.
        
        moveResult = "Nothing"; //Set the last move result to nothing found.
    }
    
    /**
     * Increments the number of treasure found and decrements the amount of treasure remaining.
     * Will also decrement the number of remaining tries, and set the last move result to depict that
     * treasure was found. Adds the points earned from the treasure to the total.
     */
    
    public void foundTreasure(int points)
    {
        treasureFound++; //Increment treasure found.
        treasureLeft--; //Decrement treasure remaining.
        pointTotal += points; //Add the points earned from the treasure found.
        triesLeft--; //Decrement number of remaining tries.
        
        moveResult = "Treasure - " + Integer.toString(points) + " pts."; //Set the last move result to treasure found.
    }
    
    /**
     * Decrement the number of remaining tries and resets the treasure found to zero.
     * Sets the last move result to depict that a troll was found. Resets points earned.
     */
    
    public void foundTroll()
    {
        triesLeft--; //Decrement number of remaining tries.
        treasureFound = 0; //Reset the amount of treasure found.
        pointTotal = 0; //Reset the points earned.
        
        moveResult = "Troll"; //Set the last move result to troll found.
    }
    
    /**
     * Determine whether the game has ended, by either winning or running out of tries.
     * @return Whether or not the game has finished.
     */
    
    private boolean gameOver()
    {
        //Return true if the number of remaining tries is zero, or the treasure found is equal
        //to the total amount of treasure.
        return (triesLeft == 0 || treasureFound == TREASURE_TOTAL);
    }
    
    /**
     * Construct the background image panel, where the background image will be displayed.
     */
    
    private void buildBackgroundImagePanel()
    {
        //Create a new Image object from the getImage method using the background image.
        Image frameBackgroundImage = Toolkit.getDefaultToolkit().getImage("./images/background.jpg");
        setContentPane(new JPanel(new BorderLayout()) //Set the content pane to display a JPanel object.
                                                      //Adds a border layout to the content pane.
        {
            /**
             * A method to override the paintComponent method in the JPanel class.
             * @param A graphics object to display the background image.
             * @override
             */

            public void paintComponent(Graphics graphic)
            {
                graphic.drawImage(frameBackgroundImage, 0, 0, this); //Add the image to the graphics object.
            }
        });     
    }
    
    /**
     * Construct the title panel where the title of the game will appear.
     */
    
    private void buildTitlePanel()
    {
        titleLabel = new JLabel("Treasure Hunt"); //Set the title label to "Treasure Hunt".
        
        titleLabel.setFont(headerFont); //Give the title label the header font.
        
        titlePanel = new JPanel(); //Create a new JPanel object.
        titlePanel.add(titleLabel); //Add the title label to the title panel object.
    }
    
    /**
     * Construct the button panel with a grid layout. The grid will be arranged in a 10 x 10 
     * matrix where each cell will contain either an empty, troll, or treasure button.
     */
    
    private void buildButtonPanel()
    {
        int treasureLocation, //The button array location of a TreasureButton object.
            trollLocation; //The button array location of a TrollButton object.
            
        Random randomNumbers = new Random(); //Create a new instance of the Random class.
        
        buttonArray = new EmptyButton[GRID_SIZE]; //Create a new array of EmptyButton objects that
                                                  //is sized to hold 100 components.
                                                                          
        buttonPanel = new JPanel(); //Create a new JPanel object.
        buttonPanel .setLayout(new GridLayout(10,10)); //Set the layout to a 10 x 10 grid layout.
        
        for (int index = 0; index < TREASURE_TOTAL; index++)
        //A loop to fill the button array with 20 TreasureButton objects.
        {
            do //Requires no precondition to be met.
            {
                treasureLocation = randomNumbers.nextInt(GRID_SIZE); //Set the location of a treasure button in
                                                                     //the array to a random number between 0 and 99.
            }while(buttonArray[treasureLocation] != null); //Will repeat if the buttonArray already contains an object
                                                           //at this location.
            
            buttonArray[treasureLocation] = new TreasureButton(); //Create a new TreasureButton in the array at this
                                                                  //location.
            //Create treasure button listener for the new treasure button in the array.                                                      
            buttonArray[treasureLocation].addActionListener(new TreasureButtonListener(this, 
                                                           (TreasureButton) buttonArray[treasureLocation])); 
        }
        
        for (int index = 0; index < TROLL_TOTAL; index++)
        //A loop to fill the button array with 20 TrollButton objects.
        {
            do //Requires no precondition to be met.
            {
                trollLocation = randomNumbers.nextInt(GRID_SIZE); //Set the location of a treasure button in
                                                                  //the array to a random number between 0 and 99.
            }while(buttonArray[trollLocation] != null); //Will repeat if the buttonArray already contains an object
                                                        //at this location.
            
            buttonArray[trollLocation] = new TrollButton(); //Create a new TrollButton in the array at this
                                                            //location.
                                                            
            //Create treasure button listener for the new treasure button in the array.                                                      
            buttonArray[trollLocation].addActionListener(new TrollButtonListener(this, 
                                                        (TrollButton) buttonArray[trollLocation]));
        }
        
        for (int index = 0; index < GRID_SIZE; index++)
        //A loop to trace through each cell in the grid layout.
        {
            if (buttonArray[index] == null) //If there is no button in the array at the index.
            {
                buttonArray[index] = new EmptyButton(); //Create a new EmptyButton object.
                
                //Create a empty button listener for the new empty button in the array.
                buttonArray[index].addActionListener(new EmptyButtonListener(this, buttonArray[index]));
            }
            
            buttonPanel.add(buttonArray[index]); //Add each individual button in order, to the button panel.
        }
        
    }
    
    /**
     * Construct the info panel which will display the treasure found, the treasure left,
     * and the number of tries remaining.
     */
    
    private void buildInfoPanel()
    {
        final int PANEL_WIDTH = 400, //The default width the panel will be sized to.
                  PANEL_HEIGHT = 100, //The default height the panel will be sized to.
                  HORIZ_SPACING = 50, //The horizontal spacing between panel components.
                  VERT_SPACING = 90; //The vertical spacing between panel components.
                  
        treasureFoundLabel = new JLabel(TREASURE_FOUND_HEADER + 
                                        Integer.toString(treasureFound)); //Create a new JLabel object with caption of "Treasure Found" and
                                                                          //the amount of treasure found.
        treasureLeftLabel = new JLabel(TREASURE_LEFT_HEADER + 
                                       Integer.toString(treasureLeft)); //Create a new JLabel object with caption of "Treasure Left" and
                                                                        //the amount of treasure remaining.
        pointTotalLabel = new JLabel(POINT_TOTAL_HEADER + 
                                     Integer.toString(pointTotal)); //Create a new JLabel object with caption of "Point Total" and
                                                                    //the number of points earned.                                                          
        triesLeftLabel = new JLabel(TRIES_LEFT_HEADER + 
                                    Integer.toString(triesLeft)); //Create a new JLabel object with caption of "Tries Left" and the amount
                                                                  //of tries remaining.
                                                                    
        //Set the font in the respective labels to the body font.
        treasureFoundLabel.setFont(bodyFont); 
        treasureLeftLabel.setFont(bodyFont);
        pointTotalLabel.setFont(bodyFont);
        triesLeftLabel.setFont(bodyFont);
        
        //Create a new Image object from the getImage method using the scroll image.
        Image infoPanelBackgroundImage = Toolkit.getDefaultToolkit().getImage("./images/scroll.png");
        infoPanel = new JPanel(){//Create a new JPanel object and override the paintComponent method to display the
                                 //image as the background.
            /**
             * A method to override the paintComponent method in the JPanel class.
             * @param A graphics object to display the background image.
             * @override
             */

            public void paintComponent(Graphics graphic)
            {
                graphic.drawImage(infoPanelBackgroundImage, 0, 0, infoPanel.getSize().width,
                                                                  infoPanel.getSize().height, infoPanel); 
                                                                  //Add the image to the graphics object.
            }
        };
        
        //Set the layout to a flow layout, centered, with the aforementioned spacing between components.
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, HORIZ_SPACING, VERT_SPACING));                                               
        infoPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT)); //Set panel size to the preferred dimensions.
        
        //In order, add the respective labels to the info panel.                                               
        infoPanel.add(treasureFoundLabel);
        infoPanel.add(treasureLeftLabel);
        infoPanel.add(pointTotalLabel);
        infoPanel.add(triesLeftLabel);
    }
    
    /**
     * Construct the lastMovePanel that will display the last move the user made.
     */
    
    private void buildLastMovePanel()
    {
        lastMoveLabel = new JLabel(" Last Move "); //Create a new JLabel object with the caption " Last Move ".
        
        lastMoveLabel.setOpaque(true); //Make the last move label opaque.
        lastMoveLabel.setBackground(tan); //Set the background color of the last move label to tan.
        
        lastMoveTextField = new JTextField(15); //Create a new JTextField that is 15 characters wide.
        lastMoveTextField.setEditable(false); //Make the last move text field uneditable.
        
        //Set the font of the last move label and text field to the body font.
        lastMoveLabel.setFont(bodyFont);
        lastMoveTextField.setFont(bodyFont);
        
        lastMovePanel = new JPanel(); //Create a new JPanel object.
        
        //Add the corresponding components to the last move panel in order.
        lastMovePanel.add(lastMoveLabel);
        lastMovePanel.add(lastMoveTextField);
    }
    
    /**
     * Construct the legendPanel which will house descriptions of the image icons on the game board.
     */
    
    private void buildLegendPanel()
    {      
        final int PANEL_WIDTH = 400, //The default width the panel will be sized to.
                  PANEL_HEIGHT = 100, //The default height the panel will be sized to.
                  HORIZ_SPACING = 200, //The horizontal spacing between panel components.
                  VERT_SPACING = 20; //The vertical spacing between panel components.
        
        //Create two blank spacer labels that serve only for the purpose of making the other labels fit
        //on the scroll image.
        JLabel spacerLabel1 = new JLabel("     ");
        JLabel spacerLabel2 = new JLabel("     ");
        
        legendLabel = new JLabel("Legend"); //Create a new JLabel object with the caption "Legend".
        legendLabel.setFont(bodyFont); //Set the font of the label to the body font.
        
        nothingIconLabel = new JLabel(new ImageIcon("./images/empty.png")); //Create a label with the empty icon displayed.
        treasureIconLabel = new JLabel(new ImageIcon("./images/treasure.png")); //Create a label with the treasure icon displayed.
        trollIconLabel = new JLabel(new ImageIcon("./images/troll.png")); //Create a label with the troll icon displayed.
        
        nothingDescriptionLabel = new JLabel("A symbol depicting that nothing was found"); //A label to describe the empty icon.
        nothingDescriptionLabel.setFont(legendFont); //Set the font of the label to the legend font.
        
        treasureDescriptionLabel = new JLabel("Finding treasure yields 1 to 3 points"); //A label to describe the treasure icon.
        treasureDescriptionLabel.setFont(legendFont); //Set the font of the label to the legend font.
        
        trollDescriptionLabel = new JLabel("A troll steals all of the treasure found"); //A label to describe the troll icon.
        trollDescriptionLabel.setFont(legendFont); //Set the font of the label to the legend font.
        
         //Create a new Image object from the getImage method using the scroll image.
        Image infoPanelBackgroundImage = Toolkit.getDefaultToolkit().getImage("./images/scroll.png");
        legendPanel = new JPanel(){//Create a new JPanel object and override the paintComponent method to display the
                                 //image as the background.
            /**
             * A method to override the paintComponent method in the JPanel class.
             * @param A graphics object to display the background image.
             * @override
             */

            public void paintComponent(Graphics graphic)
            {
                graphic.drawImage(infoPanelBackgroundImage, 0, 0, infoPanel.getSize().width,
                                                                  infoPanel.getSize().height, infoPanel); 
                                                                  //Add the image to the graphics object.
            }
        };
        
        //Set the layout to a flow layout, centered, with the aforementioned spacing between components.
        legendPanel.setLayout(new FlowLayout(FlowLayout.CENTER, HORIZ_SPACING, VERT_SPACING));                                               
        legendPanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT)); //Set panel size to the preferred dimensions.
        
        //Add the labels to the panel in order, putting the spacers at the top so the rest fit on the image.
        legendPanel.add(spacerLabel1);
        legendPanel.add(spacerLabel2);
        legendPanel.add(legendLabel);
        
        legendPanel.add(nothingIconLabel);
        legendPanel.add(nothingDescriptionLabel);
        legendPanel.add(treasureIconLabel);
        legendPanel.add(treasureDescriptionLabel);
        legendPanel.add(trollIconLabel);
        legendPanel.add(trollDescriptionLabel);
        
    }
    
    /**
     * To be called each time a button is clicked. Will update corresponding JLabel and JTextField components
     * to reflect current treasure found, treasure remaining, and tries left. In addition, will determine if 
     * the game has ended and if so, will disable all buttons and display an appropriate message.
     */
    
    public void updateResults()
    {
        treasureFoundLabel.setText(TREASURE_FOUND_HEADER + Integer.toString(treasureFound)); //Update label to reflect treasure found.
        treasureLeftLabel.setText(TREASURE_LEFT_HEADER + Integer.toString(treasureLeft)); //Update label to reflect treasure left.
        triesLeftLabel.setText(TRIES_LEFT_HEADER + Integer.toString(triesLeft)); //Update label to reflect tries left.
        pointTotalLabel.setText(POINT_TOTAL_HEADER + Integer.toString(pointTotal)); //Update label to reflect points earned.
        lastMoveTextField.setText(moveResult); //Update text field to reflect the last move's result.
        
        if (gameOver()) //If the game has ended.
        {
            if (treasureFound == TREASURE_TOTAL) //If the treasure found is equal to the total treasure.
            {
                lastMoveTextField.setText("Game Over - You Win"); //Display victory message.
            }
            else
            {   
                lastMoveTextField.setText("Game Over - You Lose"); //Display loss message.
            }
            
            for (int index = 0; index < GRID_SIZE; index++)
            //A for-loop to trace through all of the buttons in the array.
            {
                buttonArray[index].setEnabled(false); //Disable each button in the array.
            }
        } 
    }
   
   /**
    * A method to construct the menu bar for the JFrame. Will allow the user to access information about the
    * developer and exit the program.
    */
   
   private void buildMenuBar()
   {
       menuBar = new JMenuBar(); //Create a new JMenuBar object.
       fileMenu = new JMenu("File"); //Create a new JMenu object with the caption "File".
       aboutItem = new JMenuItem("About"); //Create a new JMenuItem object with the caption "About".
       exitItem = new JMenuItem("Exit"); //Create a new JMenuItem object with the caption "Exit".
       
       exitItem.addActionListener(new ExitListener()); //Add an ActionListener to the menu item.
       aboutItem.addActionListener(new AboutListener()); //Add an ActionListener to the menu item.
       
       fileMenu.add(aboutItem); //Add the menu item to the menu.
       fileMenu.add(exitItem); //Add the menu item to the menu.
       menuBar.add(fileMenu); //Add the menu to the menu bar.
       
       setJMenuBar(menuBar); //Set the menu bar for the frame.
   }
   
    /**
     * When called this method will create and display the game's graphical user interface.
     */
    
    public void play()
    {
        setTitle("The Treasure Hunt Game"); //Set the title of the JFrame.
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set the close button operation to exit on close.
        
        headerFont = new Font("Papyrus", Font.BOLD, 50); //Create the header font with bold, size 50 lettering, 
                                                         //and papyrus font.
        
        bodyFont = new Font("Papyrus", Font.PLAIN, 25); //Create the body font with plain, size 25 lettering, 
                                                        //and papyrus font.
        
        legendFont = new Font("Papyrus", Font.PLAIN, 15); //Create the body font with plain, size 15 lettering, 
                                                        //and papyrus font.                                                
        
        buildBackgroundImagePanel(); //Call the buildBackgroundImagePanel method.
        buildTitlePanel(); //Call the buildTitlePanel method.
        buildInfoPanel(); //Call the buildInfoPanel method.
        buildButtonPanel(); //Call the buildButtonPanel method.
        buildLastMovePanel(); //Call the buildLastMovePanel method.
        buildLegendPanel(); //Call the buildLegendPanel method.
        buildMenuBar(); //Call the buildMenuBar method.
        
        infoPanel.setOpaque(false); //Make the info panel transparent so the background image can be seen.
        titlePanel.setOpaque(false); //Make the title panel transparent so the background image can be seen.
        lastMovePanel.setOpaque(false); //Make the last move transparent so the background image can be seen.
        buttonPanel.setOpaque(false); //Make the button panel transparent so the background image can be seen.
        legendPanel.setOpaque(false); //Make the legend panel transparent so the background image can be seen.
        
        add(titlePanel, BorderLayout.NORTH); //Add the title panel to the north section of the JFrame.
        add(buttonPanel, BorderLayout.CENTER); //Add the button panel to the center section of the JFrame.
        add(infoPanel, BorderLayout.WEST); //Add the info panel to the left section of the JFrame.
        add(lastMovePanel, BorderLayout.SOUTH); //Add the last move panel to the south section of the JFrame.
        add(legendPanel, BorderLayout.EAST); //Add the legend panel to the east section of the JFrame.
        
        pack(); //Distribute the componentes evenly in the JFrame.
        
        setVisible(true); //Make the JFrame visible.
        setResizable(false); //Make it so that the JFrame cannot be resized.

    }
    
    public static void main(String[] args) //This method will execute when the program runs.
    {
        new TreasureGame().play(); //Create a new TreasureGame object with no reference variable,
                                   //and call the play method.       
    }
                      
}
