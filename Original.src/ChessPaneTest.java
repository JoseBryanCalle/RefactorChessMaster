import org.junit.Assert;
import org.junit.Test;

public class ChessPaneTest{
    @Test
    public void creacionImagen(){
        int jugador = 1;
        ChessPanel cp = new ChessPanel();
        Assert.assertEquals(null, cp.getGraveyard(3));

        Assert.assertEquals(null, cp.getGraveyard(9));

    }

}
