import javax.swing.*;
// -------------------------------------------------------------------------

public class ChessMain{
    // ----------------------------------------------------------

    public static void main( String[] args ){

        ChessGameLog cgl = new ChessGameLog();
        ChessGameEngine cge = new ChessGameEngine();
        cge.enlazarObservador(cgl);
        cge.procesoEscribir();

        Pieza pieza = Factory.construir("King");
        assert pieza != null;

        JFrame frame = new JFrame( "YetAnotherChessGame 1.0" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().add( new ChessPanel() );
        frame.pack();
        frame.setVisible( true );
    }
}


