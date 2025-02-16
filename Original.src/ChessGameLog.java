import javax.swing.*;
import java.util.Date;

public class ChessGameLog extends JScrollPane implements Observador, Pieza{

    private static JTextArea textArea = null;

    /******Singleton*******/
    private static ChessGameLog singletonChessGameLog;

    public static ChessGameLog getInstance(){
        if(singletonChessGameLog ==null){
            singletonChessGameLog = new ChessGameLog();
        }
        return singletonChessGameLog;
    }
    /***********************/
    /*******Observador*********/

    @Override
    public void escribirTextArea(String texto) {
        textArea.setText(texto);
    }
    /*************************/


    public ChessGameLog(){
        super(
                new JTextArea( "", 5, 30 ),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        textArea = ( (JTextArea)this.getViewport().getView() );
    }

    public void addToLog( String s ){
        if ( textArea.getText().length() > 0 ){
            textArea.setText( textArea.getText() + "\n" + new Date() + " - "
                    + s );
        }
        else
        {
            textArea.setText( new Date() + " - " + s );
        }
    }

    public void clearLog(){
        textArea.setText("");
    }

    public String getLastLog(){
        int indexOfLastNewLine = textArea.getText().lastIndexOf( "\n" );
        if ( indexOfLastNewLine < 0 ){
            return textArea.getText();
        }
        return textArea.getText().substring( indexOfLastNewLine + 1 );
    }

    @Override
    public Pieza avanzar() {
        textArea.setText("La pieza ha avanzado");
        return null;
    }

    @Override
    public Pieza retroceder() {
        textArea.setText("La pieza ha sido retrocedida");
        return null;
    }

    @Override
    public String nombre() {
        String nombrePieza = "King";
        textArea.setText(nombrePieza);
        return nombrePieza;
    }
}
