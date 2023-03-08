import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Objects;
// -------------------------------------------------------------------------

public class Rook extends ChessGamePiece {
    // private ArrayList<String> possibleMoves;
    // ----------------------------------------------------------

    public Rook(ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color );
    }

    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> northMoves = calculateNorthMoves( board, 8 );
        ArrayList<String> southMoves = calculateSouthMoves( board, 8 );
        ArrayList<String> westMoves = calculateWestMoves( board, 8 );
        ArrayList<String> eastMoves = calculateEastMoves( board, 8 );
        ArrayList<String> allMoves = new ArrayList<String>();
        allMoves.addAll( northMoves );
        allMoves.addAll( southMoves );
        allMoves.addAll( westMoves );
        allMoves.addAll( eastMoves );
        return allMoves;
    }

    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == WHITE){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/WhiteRook.gif"))
            );            
        }
        else if ( getColorOfPiece() == BLACK){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/BlackRook.gif"))
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
