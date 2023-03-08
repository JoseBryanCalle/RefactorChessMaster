import org.junit.Assert;
import org.junit.Test;

public class ChessGameEngineTest {
    @Test
    public void movimientosLegales(){
        boolean validador =true;
        ChessGameEngine cge = new ChessGameEngine();
        Assert.assertEquals(true,
                cge.playerHasLegalMoves(21));
    }
}
