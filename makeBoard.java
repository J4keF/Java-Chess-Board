package Culminating2022;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class makeBoard extends JFrame{

    //Creates JFrame (Main GUI) and initializes variables (Square colours, height, width, etc.)
    JFrame frame;
    Color color1 = Color.decode("#B58863");
    Color color2 = Color.decode("#F0D9B5");
    int width = 1200;
    int height = 1200;

    //Colours and Pieces are given number values so they can be accessed properly when setting up the board
    public static int BLACK = 0, WHITE = 1;
    public static int PAWN = 0, ROOK = 1, KNIGHT = 2, BISHOP = 3, QUEEN = 4, KING = 5;

    //Array with order of start setup (How a starting chess row normally looks)
    public static int[] startRow = {ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK};

    //Generate is a method that outputs (to the screen) a completed GUI with a 8x8 grid (chessboard) and pieces
    public void generate() throws IOException {

        //Creates a new JFrame and gives it a title, exit behaviour, width and height, and keeps it the same size
        frame = new JFrame();
        frame.setTitle("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        //Makes a JPanel (component of JFrame that gets added to it) that has an 8x8 grid layout
        JPanel tiles = new JPanel(new GridLayout(8,8));

        //8x8 array of JButtons that will help update and keep track of the 'tiles' board squares
        JButton[][] board = new JButton[8][8];

        //Grid layout is empty when it's created so these loops generate buttons (named tile) to fill it
        //Side note: Buttons are just elements you can add to the GUI that can have images and text
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board.length; col++) {
                JButton tile = new JButton();
                //Removes ugly black borders
                tile.setBorderPainted(false);
                tile.setOpaque(true);
                //Not sure if this does anything but I'd like to think it's the reason they're all the same size (100x100px)
                tile.setPreferredSize(new Dimension(100, 100));

                if ((row % 2 == 1 && col % 2 == 1) || (row % 2 == 0 && col % 2 == 0)) {
                    tile.setBackground(color2);
                } else {
                    tile.setBackground(color1);
                }

                //Adds tile to board in the appropriate spot
                board[row][col] = tile;
                //Adds tile (button) to tiles (JPanel)
                tiles.add(tile);
            }
        }

        //Creates an array of Images with the dimensions of the chess pieces (2 colours, by 6 pieces)
        Image[][] pieceImages = new Image[2][6];

        //Stores the chessIcons file
        File pieces = new File("/Users/jakefogel/Downloads/IdeaProjects/ComputerSciencePractice/src/Culminating2022/chessIconsFinal.png");
        //Converts it to a bufferedImage
        BufferedImage pieceImagesBuff = ImageIO.read(pieces);

        //Loops over the 2x6 array and fills each index with a segment (a single chess piece) from the chessIcons image
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                pieceImages[i][j] = pieceImagesBuff.getSubimage((j * 93), i * 92, 93, 92);
            }
        }

        //4 loops (not to be confused with for-loops. God I'm hilarious) that fill the board array (from earlier)-
        //with the appropriate icons from the images array (this is where those numbers we assigned to the colours and-
        //pieces come in handy)
        for (int i = 0; i < startRow.length; i++) {
            board[0][i].setIcon(new ImageIcon(pieceImages[BLACK][startRow[i]]));

        }
        for (int j = 0; j < startRow.length; j++) {
            board[1][j].setIcon(new ImageIcon(pieceImages[BLACK][PAWN]));
        }

        for (int i = 0; i < startRow.length; i++) {
            board[7][i].setIcon(new ImageIcon(pieceImages[WHITE][startRow[i]]));

        }
        for (int j = 0; j < startRow.length; j++) {
            board[6][j].setIcon(new ImageIcon(pieceImages[WHITE][PAWN]));
        }



        //So I created a new JPanel called overlayoutPanel
        JPanel  overlayoutPanel = new JPanel();

        //Then I create an overlayLayout (just a layout system that tells a panel to put objects over-top each other)
        OverlayLayout overlay = new OverlayLayout(overlayoutPanel);
        //Then I set the overlayoutPanel JPanel to the OverlayLayout I created, so now it will follow the rules of-
        //the overlaying layout
        overlayoutPanel.setLayout(overlay);

        //Now I create a new button that just has a single piece icon (white pawn) and is transparent, borderless, and-
        //empty, to make it look like the piece is just on its own
        JButton testPiece = new JButton();
        testPiece.setIcon(new ImageIcon(pieceImages[WHITE][PAWN]));
        Dimension d2 = new Dimension(100, 100);
        testPiece.setMaximumSize(d2);
        //0,0 alignment sets the piece one square to the right which is interesting
        testPiece.setAlignmentX(8.7f);
        testPiece.setAlignmentY(5.2f);
        testPiece.setOpaque(false);
        testPiece.setContentAreaFilled(false);
        testPiece.setBorderPainted(false);

        //Then I add testPiece to the JPanel with the OverlayLayout, followed by the entire chessboard (tiles)
        overlayoutPanel.add(testPiece);
        overlayoutPanel.add(tiles);


        //Finally, I add overlayoutPanel (with the tiles board and testPiece to frame and make it visible
        frame.add(overlayoutPanel);
        frame.pack();
        frame.setVisible(true);

        //So now we have a button (testPiece) over the board which is great because if we can find a way to move ONLY-
        //testPiece, we can find a way to set all the pieces as their own buttons and move them individually

        //TODO --> FIND A WAY TO DRAG TESTPIECE
        //TODO --> FIND A WAY TO GET TESTPIECE TO SNAP TO CHESSBOARD GRID (MAYBE CHECK WHICH SQUARE CENTER OF TESTPIECE IS IN AND SET TO MIDDLE WHEN MOUSE RELEASES?)

    }
}

