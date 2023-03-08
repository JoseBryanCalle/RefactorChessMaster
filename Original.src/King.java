import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Objects;
// -------------------------------------------------------------------------

public class King extends ChessGamePiece {
    // ----------------------------------------------------------

    public King(ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color, false );
    }

    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> northEastMoves = calculateNorthEastMoves( board, 1 );
        ArrayList<String> northWestMoves = calculateNorthWestMoves( board, 1 );
        ArrayList<String> southEastMoves = calculateSouthEastMoves( board, 1 );
        ArrayList<String> southWestMoves = calculateSouthWestMoves( board, 1 );
        ArrayList<String> northMoves = calculateNorthMoves( board, 1 );
        ArrayList<String> southMoves = calculateSouthMoves( board, 1 );
        ArrayList<String> eastMoves = calculateEastMoves( board, 1 );
        ArrayList<String> westMoves = calculateWestMoves( board, 1 );
        return getStrings(northEastMoves, northWestMoves, southEastMoves, southWestMoves, northMoves, southMoves, eastMoves, westMoves);
    }

    static ArrayList<String> getStrings(ArrayList<String> northEastMoves, ArrayList<String> northWestMoves, ArrayList<String> southEastMoves, ArrayList<String> southWestMoves, ArrayList<String> northMoves, ArrayList<String> southMoves, ArrayList<String> eastMoves, ArrayList<String> westMoves) {
        ArrayList<String> allMoves = new ArrayList<String>();
        allMoves.addAll( northEastMoves );
        allMoves.addAll( northWestMoves );
        allMoves.addAll( southWestMoves );
        allMoves.addAll( southEastMoves );
        allMoves.addAll( northMoves );
        allMoves.addAll( southMoves );
        allMoves.addAll( westMoves );
        allMoves.addAll( eastMoves );
        return allMoves;
    }


    public boolean isChecked( ChessGameBoard board ){
        return getCurrentAttackers( board ).size() > 0;
    }

    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == ChessGamePiece.WHITE ){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/WhiteKing.gif"))
            );            
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK ){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/BlackKing.gif"))
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
