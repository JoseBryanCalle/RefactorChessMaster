import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Objects;

public class Knight extends ChessGamePiece {

    public Knight(ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color );
    }

    private ArrayList<String> calculateNorthMoves( ChessGameBoard board ){
        return getStrings(board, 2, 4, 1, 2);
    }

    private ArrayList<String> getStrings(ChessGameBoard board, int i2, int i3, int i4, int i5) {
        ArrayList<String> moves = new ArrayList<>();
        for (int i = i2; i >= -i2; i -= i3){
            for (int j = i4; j >= -i4; j -= i5){
                if ( isOnScreen( pieceRow + i, pieceColumn + j )
                    && ( isEnemy( board, pieceRow + i, pieceColumn + j ) ||
                        board.getCell(
                        pieceRow + i,
                        pieceColumn + j )
                        .getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn + j ) );
                }
            }
        }
        return moves;
    }

    private ArrayList<String> calculateSouthMoves( ChessGameBoard board ){
        return getStrings(board, 1, 2, 2, 4);
    }

    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> moves = new ArrayList<String>();
        if ( isPieceOnScreen() ){
            moves.addAll( calculateNorthMoves( board ) );
            moves.addAll( calculateSouthMoves( board ) );
        }
        return moves;
    }

    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == WHITE){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/WhiteKnight.gif"))
            );            
        }
        else if ( getColorOfPiece() == BLACK){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/BlackKnight.gif"))
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
