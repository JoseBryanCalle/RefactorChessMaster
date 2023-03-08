import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ImageIcon;
// -------------------------------------------------------------------------

public class Bishop extends ChessGamePiece{

    public Bishop( ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color );
    }

    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> northEastMoves = calculateNorthEastMoves( board, 8 );
        ArrayList<String> northWestMoves = calculateNorthWestMoves( board, 8 );
        ArrayList<String> southEastMoves = calculateSouthEastMoves( board, 8 );
        ArrayList<String> southWestMoves = calculateSouthWestMoves( board, 8 );
        ArrayList<String> allMoves = new ArrayList<String>();
        allMoves.addAll( northEastMoves );
        allMoves.addAll( northWestMoves );
        allMoves.addAll( southEastMoves );
        allMoves.addAll( southWestMoves );
        return allMoves;
    }

    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == ChessGamePiece.WHITE ){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/WhiteBishop.gif"))
            );
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK ){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/BlackBishop.gif"))
            );
        }
        else{
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/BlackBishop.gif"))
            );
        }
    }
}
