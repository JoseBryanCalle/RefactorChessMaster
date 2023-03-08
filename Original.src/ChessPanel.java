import java.awt.*;
import javax.swing.*;
// -------------------------------------------------------------------------

public class ChessPanel extends JPanel{
    private final ChessGameBoard  gameBoard;
    private final ChessGameLog gameLog;
    private final ChessGraveyard  playerOneGraveyard;
    private final ChessGraveyard  playerTwoGraveyard;
    private final ChessGameEngine gameEngine;

    public ChessPanel(){
        this.setLayout( new BorderLayout() );
        ChessMenuBar menuBar = new ChessMenuBar();
        gameBoard = new ChessGameBoard();
        //**Singleton
        gameLog = ChessGameLog.getInstance();
        //*****
        playerOneGraveyard = new ChessGraveyard( "Player 1's graveyard" );
        playerTwoGraveyard = new ChessGraveyard( "Player 2's graveyard" );
        this.add(menuBar, BorderLayout.NORTH );
        this.add( gameBoard, BorderLayout.CENTER );
        this.add( gameLog, BorderLayout.SOUTH );
        this.add( playerOneGraveyard, BorderLayout.WEST );
        this.add( playerTwoGraveyard, BorderLayout.EAST );
        this.setPreferredSize( new Dimension( 800, 600 ) );
        gameEngine = new ChessGameEngine( gameBoard ); // start the game
    }
    // ----------------------------------------------------------

    public ChessGameLog getGameLog(){
        return gameLog;
    }
    // ----------------------------------------------------------

    public ChessGameBoard getGameBoard(){
        return gameBoard;
    }
    // ----------------------------------------------------------

    public ChessGameEngine getGameEngine(){
        return gameEngine;
    }
    // ----------------------------------------------------------

    public ChessGraveyard getGraveyard( int whichPlayer ){
        if ( whichPlayer == 1 ){
            return playerOneGraveyard;
        }
        else if ( whichPlayer == 2 ){
            return playerTwoGraveyard;
        }
        else
        {
            return null;
        }
    }
}
