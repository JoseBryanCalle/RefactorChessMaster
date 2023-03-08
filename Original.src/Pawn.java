import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Objects;
// -------------------------------------------------------------------------

public class Pawn extends ChessGamePiece {
    private boolean notMoved;
    // ----------------------------------------------------------

    public Pawn(ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color, true );
        notMoved = true;
        possibleMoves = calculatePossibleMoves( board );
    }

    @Override
    public boolean move(ChessGameBoard board, int row, int col ){
        if ( super.move( board, row, col ) ){
            notMoved = false;
            possibleMoves = calculatePossibleMoves( board );
            if ( ( getColorOfPiece() == BLACK && row == 7 )
                || ( getColorOfPiece() == WHITE && row == 0 ) ){ // pawn has reached the end of the board, promote it to queen
                board.getCell( row, col ).setPieceOnSquare( new Queen(
                    board,
                    row,
                    col,
                    getColorOfPiece() ) );
            }
            return true;
        }
        return false;
    }

    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> moves = new ArrayList<>();
        if ( isPieceOnScreen() ){
            int currRow =
                getColorOfPiece() == WHITE
                    ? ( pieceRow - 1 )
                    : ( pieceRow + 1 );
            int count = 1;
            int maxIter = notMoved ? 2 : 1;
            // check for normal moves
            while ( count <= maxIter ){ // only loop while we have open slots and have not passed our
              // limit
                if ( isOnScreen( currRow, pieceColumn )
                    && board.getCell( currRow,
                        pieceColumn ).getPieceOnSquare() == null ){
                    moves.add( currRow + "," + pieceColumn );
                }
                else
                {
                    break;
                }
                currRow =
                    ( getColorOfPiece() == WHITE)
                        ? ( currRow - 1 )
                        : ( currRow + 1 );
                count++;
            }
            // check for enemy capture points
            if ( getColorOfPiece() == WHITE){
                if ( isEnemy( board, pieceRow - 1, pieceColumn - 1 ) ){
                    moves.add( ( pieceRow - 1 ) + "," + ( pieceColumn - 1 ) );
                }
                if ( isEnemy( board, pieceRow - 1, pieceColumn + 1 ) ){
                    moves.add( ( pieceRow - 1 ) + "," + ( pieceColumn + 1 ) );
                }
            }
            else
            {
                if ( isEnemy( board, pieceRow + 1, pieceColumn - 1 ) ){
                    moves.add( ( pieceRow + 1 ) + "," + ( pieceColumn - 1 ) );
                }
                if ( isEnemy( board, pieceRow + 1, pieceColumn + 1 ) ){
                    moves.add( ( pieceRow + 1 ) + "," + ( pieceColumn + 1 ) );
                }
            }
        }
        return moves;
    }

    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == WHITE){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/WhitePawn.gif"))
            );            
        }
        else if ( getColorOfPiece() == BLACK){
            return new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("chessImages/BlackPawn.gif"))
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
