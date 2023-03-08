import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Objects;
// import java.awt.Color;
// -------------------------------------------------------------------------

public class Queen extends ChessGamePiece {
    // ----------------------------------------------------------

    public Queen(ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color );
    }

    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> northEastMoves = calculateNorthEastMoves( board, 8 );
        ArrayList<String> northWestMoves = calculateNorthWestMoves( board, 8 );
        ArrayList<String> southEastMoves = calculateSouthEastMoves( board, 8 );
        ArrayList<String> southWestMoves = calculateSouthWestMoves( board, 8 );
        ArrayList<String> northMoves = calculateNorthMoves( board, 8 );
        ArrayList<String> southMoves = calculateSouthMoves( board, 8 );
        ArrayList<String> eastMoves = calculateEastMoves( board, 8 );
        ArrayList<String> westMoves = calculateWestMoves( board, 8 );
        return King.getStrings(northEastMoves, northWestMoves, southEastMoves, southWestMoves, northMoves, southMoves, eastMoves, westMoves);
    }

    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == WHITE){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/WhiteQueen.gif"))
            );            
        }
        else if ( getColorOfPiece() == BLACK){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/BlackQueen.gif"))
            );            
        }
        else
        {
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/default-Unassigned.gif"))
            ); 
        }
    }
}
