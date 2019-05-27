public class RoundConstant {
    private static int[] constants = {0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36};


    public static Palavra getRoundConstant(int roundKeyNumber) {
        return new Palavra((byte) constants[roundKeyNumber-1], (byte) 0, (byte) 0, (byte) 0);
    }
}
