package Culminating2022;

import java.awt.*;
import java.io.IOException;

public class chessMain {
    public static void main(String[] args) throws IOException {
        //Makes a new makeBoard object called chessBoard
        makeBoard chessBoard = new makeBoard();
        //Calls the generate method
        chessBoard.generate();
    }
}