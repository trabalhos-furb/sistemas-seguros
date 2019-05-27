public class TabelaMultiplicacao {
    private static byte[][] multiplicacao = {
            {2, 3, 1, 1},
            {1, 2, 3, 1},
            {1, 1, 2, 3},
            {3, 1, 1, 2}
    };

    public static Palavra multiply(Palavra palavra) {
        byte byteAtual1 = palavra.getByte(0);
        byte byteAtual2 = palavra.getByte(1);
        byte byteAtual3 = palavra.getByte(2);
        byte byteAtual4 = palavra.getByte(3);

        byte byteNovoByte1 = (byte) (multiplyBytes(byteAtual1, multiplicacao[0][0]) ^ multiplyBytes(byteAtual2, multiplicacao[0][1]) ^ multiplyBytes(byteAtual3, multiplicacao[0][2]) ^ multiplyBytes(byteAtual4, multiplicacao[0][3]));
        byte byteNovoByte2 = (byte) (multiplyBytes(byteAtual1, multiplicacao[1][0]) ^ multiplyBytes(byteAtual2, multiplicacao[1][1]) ^ multiplyBytes(byteAtual3, multiplicacao[1][2]) ^ multiplyBytes(byteAtual4, multiplicacao[1][3]));
        byte byteNovoByte3 = (byte) (multiplyBytes(byteAtual1, multiplicacao[2][0]) ^ multiplyBytes(byteAtual2, multiplicacao[2][1]) ^ multiplyBytes(byteAtual3, multiplicacao[2][2]) ^ multiplyBytes(byteAtual4, multiplicacao[2][3]));
        byte byteNovoByte4 = (byte) (multiplyBytes(byteAtual1, multiplicacao[3][0]) ^ multiplyBytes(byteAtual2, multiplicacao[3][1]) ^ multiplyBytes(byteAtual3, multiplicacao[3][2]) ^ multiplyBytes(byteAtual4, multiplicacao[3][3]));

        return new Palavra(byteNovoByte1, byteNovoByte2, byteNovoByte3, byteNovoByte4);
    }

    private static byte multiplyBytes(byte r, byte i) {
        if (r == 0 || i == 0) {
            return 0;
        }
        if (r == 1) {
            return i;
        }
        if (i == 1) {
            return r;
        }

        int rByte = TabelaL.getByte(r);
        int iByte = TabelaL.getByte(i);

        int soma = rByte + iByte;
        if (soma > 255) {
            soma -= 0xFF;
        }

        return TabelaE.getByte((byte) soma);
    }
}
