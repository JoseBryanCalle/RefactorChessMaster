import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
// -------------------------------------------------------------------------

public abstract class ChessGamePiece{
    private final boolean             skipMoveGeneration;
    private final int                 pieceColor;
    private final ImageIcon           pieceImage;
    protected ArrayList<String> possibleMoves;
    protected int               pieceRow;
    protected int               pieceColumn;
    static final int            BLACK      = 0;
    static final int            WHITE      = 1;
    static final int            UNASSIGNED = -1;
    // ----------------------------------------------------------

    public ChessGamePiece(
        ChessGameBoard board,
        int row,
        int col,
        int pieceColor ){
        skipMoveGeneration = false;
        this.pieceColor = pieceColor;
        pieceImage = createImageByPieceType();
        pieceRow = row;
        pieceColumn = col;
        if ( board.getCell( row, col ) != null ){
            board.getCell( row, col ).setPieceOnSquare( this );
        }
        possibleMoves = calculatePossibleMoves( board );
    }
    // ----------------------------------------------------------

    public ChessGamePiece(
        ChessGameBoard board,
        int row,
        int col,
        int pieceColor,
        boolean skipMoveGeneration ){
        this.skipMoveGeneration = skipMoveGeneration;
        this.pieceColor = pieceColor;
        pieceImage = this.createImageByPieceType();
        pieceRow = row;
        pieceColumn = col;
        if ( board.getCell( row, col ) != null ){
            board.getCell(row, col).setPieceOnSquare( this );
        }
        if ( !this.skipMoveGeneration ){
            possibleMoves = calculatePossibleMoves( board );
        }
    }

    // ----------------------------------------------------------

    protected abstract ArrayList<String> calculatePossibleMoves(ChessGameBoard board );

    protected ArrayList<String> calculateSouthMoves(ChessGameBoard board, int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = pieceRow + 1; i < 8 && count < numMoves; i++ ){
                if ( ( board.getCell( i, pieceColumn ).getPieceOnSquare()
                    == null || isEnemy( board, i, pieceColumn ) ) ){
                    moves.add( i + "," + pieceColumn );
                    count++;
                    if ( isEnemy( board, i, pieceColumn ) ){
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------

    protected ArrayList<String> calculateNorthMoves(ChessGameBoard board, int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = pieceRow - 1; i >= 0 && count < numMoves; i-- ){
                if ( ( board.getCell( i, pieceColumn ).getPieceOnSquare()
                    == null || isEnemy( board, i, pieceColumn ) ) ){
                    moves.add( i + "," + pieceColumn );
                    count++;
                    if ( isEnemy( board, i, pieceColumn ) ){
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------

    protected ArrayList<String> calculateEastMoves(ChessGameBoard board, int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = pieceColumn + 1; i < 8 && count < numMoves; i++ ){
                if ( ( board.getCell( pieceRow, i ).getPieceOnSquare()
                    == null || isEnemy( board, pieceRow, i ) ) ){
                    moves.add( pieceRow + "," + i );
                    count++;
                    if ( isEnemy( board, pieceRow, i ) ){
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------

    protected ArrayList<String> calculateWestMoves(ChessGameBoard board, int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = pieceColumn - 1; i >= 0 && count < numMoves; i-- ){
                if ( ( board.getCell(pieceRow, i ).getPieceOnSquare()
                    == null || isEnemy( board, pieceRow, i ) ) ){
                    moves.add( pieceRow + "," + i );
                    count++;
                    if ( isEnemy( board, pieceRow, i ) ){
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------

    protected ArrayList<String> calculateNorthWestMoves(ChessGameBoard board, int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( isOnScreen( pieceRow - i, pieceColumn - i )
                    && ( board.getCell( pieceRow - i,
                        pieceColumn - i ).getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow - i ) + "," + ( pieceColumn - i ) );
                    count++;
                }
                else if ( isEnemy( board, pieceRow - i, pieceColumn - i ) ){
                    moves.add( ( pieceRow - i ) + "," + ( pieceColumn - i ) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------

    protected ArrayList<String> calculateNorthEastMoves(ChessGameBoard board, int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( isOnScreen( pieceRow - i, pieceColumn + i )
                    && ( board.getCell( pieceRow - i,
                        pieceColumn + i).getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow - i ) + "," + ( pieceColumn + i ) );
                    count++;
                }
                else if ( isEnemy( board, pieceRow - i, pieceColumn + i ) ){
                    moves.add( ( pieceRow - i ) + "," + ( pieceColumn + i ) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------

    protected ArrayList<String> calculateSouthWestMoves(ChessGameBoard board, int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( isOnScreen( pieceRow + i, pieceColumn - i )
                    && ( board.getCell( pieceRow + i,
                        pieceColumn - i ).getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn - i ) );
                    count++;
                }
                else if ( isEnemy( board, pieceRow + i, pieceColumn - i ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn - i ) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------

    protected ArrayList<String> calculateSouthEastMoves(ChessGameBoard board, int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( isOnScreen( pieceRow + i, pieceColumn + i )
                    && ( board.getCell( pieceRow + i,
                        pieceColumn + i ).getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn + i ) );
                    count++;
                }
                else if ( isEnemy( board, pieceRow + i, pieceColumn + i ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn + i ) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }

    public abstract ImageIcon createImageByPieceType();

    public ImageIcon getImage(){
        return pieceImage;
    }
    // ----------------------------------------------------------

    public int getColorOfPiece(){
        return pieceColor;
    }
    // ----------------------------------------------------------

    public boolean isOnScreen( int row, int col ){
        if ( row >= 0 && row <= 7 && col >= 0 && col <= 7 ){
            return true;
        }
        else
        {
            return false;
        }
    }
    // ----------------------------------------------------------

    public boolean isPieceOnScreen(){
        return isOnScreen( pieceRow, pieceColumn );
    }

    public boolean move( ChessGameBoard board, int row, int col ){
        if ( canMove( board, row, col ) ){
            String moveLog = this.toString() + " -> ";
            board.clearCell( pieceRow, pieceColumn );
            if ( isEnemy( board, row, col ) ){
                ChessGraveyard graveyard;
                ChessGameEngine gameEngine =
                    ( (ChessPanel)board.getParent() ).getGameEngine();
                if ( gameEngine.getCurrentPlayer() == 1 ){
                    graveyard =
                        ( (ChessPanel)board.getParent() ).getGraveyard( 2 );
                }
                else
                {
                    graveyard =
                        ( (ChessPanel)board.getParent() ).getGraveyard( 1 );
                }
                graveyard.addPiece(
                    board.getCell( row, col ).getPieceOnSquare() );
            }
            setPieceLocation( row, col );
            moveLog += " (" + row + ", " + col + ")";
            ( (ChessPanel)board.getParent() ).getGameLog().addToLog( moveLog );
            board.getCell( row, col ).setPieceOnSquare( this );
            if ( !skipMoveGeneration ){
                updatePossibleMoves( board );
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean canMove( ChessGameBoard board, int row, int col ){
        updatePossibleMoves( board );
        if (possibleMoves.contains(row + "," + col)){
            return testMoveForKingSafety( board, row, col );
        }
        return false;
    }

    private boolean testMoveForKingSafety(
        ChessGameBoard board,
        int row,
        int col ){
        updatePossibleMoves( board );
        ChessGamePiece oldPieceOnOtherSquare =
            board.getCell( row, col ).getPieceOnSquare();
        ChessGameEngine engine =
            ( (ChessPanel)board.getParent() ).getGameEngine();
        int oldRow = pieceRow;
        int oldColumn = pieceColumn;
        board.clearCell( pieceRow, pieceColumn ); // move us off
        this.setPieceLocation( row, col ); // move us to the new location
        board.getCell( row, col ).setPieceOnSquare( this );
        boolean retVal = !engine.isKingInCheck( true ); // is the current
        // king still in check?
        this.setPieceLocation( oldRow, oldColumn ); // move us back
        board.getCell( oldRow, oldColumn ).setPieceOnSquare( this );
        board.clearCell( row, col ); // ^ move the other piece back
        // to where it was
        board.getCell( row, col ).setPieceOnSquare( oldPieceOnOtherSquare );
        return retVal;
    }
    // ----------------------------------------------------------

    protected void updatePossibleMoves( ChessGameBoard board ){
        possibleMoves = calculatePossibleMoves( board );
    }
    // ----------------------------------------------------------

    public void setPieceLocation( int row, int col ){
        pieceRow = row;
        pieceColumn = col;
    }
    // ----------------------------------------------------------

    public int getRow(){
        return pieceRow;
    }
    // ----------------------------------------------------------

    public int getColumn(){
        return pieceColumn;
    }
    // ----------------------------------------------------------

    public void showLegalMoves( ChessGameBoard board ){
        updatePossibleMoves( board );
        if ( isPieceOnScreen() ){
            for ( String locStr : possibleMoves ){
                String[] currCoords = locStr.split( "," );
                int row = Integer.parseInt( currCoords[0] );
                int col = Integer.parseInt( currCoords[1] );
                if ( canMove( board, row, col ) ) // only show legal moves
                {
                    if ( isEnemy( board, row, col ) ){
                        board.getCell( row, col ).setBackground(
                            Color.YELLOW );
                    }
                    else
                    {
                        board.getCell( row, col ).setBackground( Color.PINK );
                    }
                }
            }
        }
    }
    // ----------------------------------------------------------

    public boolean hasLegalMoves( ChessGameBoard board ){
        updatePossibleMoves( board );
        if ( isPieceOnScreen() ){
            for ( String locStr : possibleMoves ){
                String[] currCoords = locStr.split( "," );
                int row = Integer.parseInt( currCoords[0] );
                int col = Integer.parseInt( currCoords[1] );
                if ( canMove( board, row, col ) ) // only show legal moves
                {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    // ----------------------------------------------------------

    public boolean isEnemy( ChessGameBoard board, int row, int col ){
        if ( row > 7 || col > 7 || row < 0 || col < 0 ){
            return false;
        }
        ChessGamePiece enemyPiece =
            board.getCell( row, col ).getPieceOnSquare() == null
                ? null
                : board.getCell( row, col ).getPieceOnSquare();
        if ( enemyPiece == null
            || this.getColorOfPiece() == ChessGamePiece.UNASSIGNED
            || enemyPiece.getColorOfPiece() == ChessGamePiece.UNASSIGNED ){
            return false;
        }
        if ( this.getColorOfPiece() == ChessGamePiece.WHITE ){
            return enemyPiece.getColorOfPiece() == ChessGamePiece.BLACK;
        }
        else
        {
            return enemyPiece.getColorOfPiece() == ChessGamePiece.WHITE;
        }
    }
    // ----------------------------------------------------------

    public ArrayList<ChessGamePiece> getCurrentAttackers( ChessGameBoard board ){
        ArrayList<ChessGamePiece> attackers = new ArrayList<>();
        int enemyColor =
            ( this.getColorOfPiece() == ChessGamePiece.BLACK )
                ? ChessGamePiece.WHITE
                : ChessGamePiece.BLACK;
        this.updatePossibleMoves( board );
        for ( int i = 0; i < board.getCells().length; i++ ){
            for ( int j = 0; j < board.getCells()[0].length; j++ ){
                ChessGamePiece currPiece =
                    board.getCell( i, j ).getPieceOnSquare();
                if ( currPiece != null
                    && currPiece.getColorOfPiece() == enemyColor ){
                    currPiece.updatePossibleMoves( board );
                    if ( currPiece.canMove( board, pieceRow, pieceColumn ) ){
                        attackers.add( currPiece );
                    }
                }
            }
        }
        return attackers;
    }

    @Override
    public String toString(){
        return this.getClass().toString().substring( 6 ) + " @ (" + pieceRow
            + ", " + pieceColumn + ")";
    }
}
