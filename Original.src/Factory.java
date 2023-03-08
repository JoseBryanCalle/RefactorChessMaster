public class Factory {
    public static Pieza construir(String nombrePieza){
        switch (nombrePieza){
            case "Pawn":
                return new ChessGameLog().avanzar();
            case "King":
                return new ChessGameLog().retroceder();
            default:
                return null;
        }
    }
}
