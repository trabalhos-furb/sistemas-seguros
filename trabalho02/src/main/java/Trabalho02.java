public class Trabalho02 {

    private static RoundKey[] keySchedule = new RoundKey[11];

    public static void main(String[] args) {
        // ABCDEFGHIJKLMNOP
        byte[] chave = {
                (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44,
                (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x48,
                (byte) 0x49, (byte) 0x4a, (byte) 0x4b, (byte) 0x4c,
                (byte) 0x4d, (byte) 0x4e, (byte) 0x4f, (byte) 0x50
        };
        RoundKey chaveOriginal = keyFromBytes(chave);
        keySchedule[0] = chaveOriginal;

        for (int i = 0; i < 10; i++) {
            keySchedule[i + 1] = geraRoundKey(i + 1);
        }

        // Desenvolvimento
        byte[] textoSimples = {
                (byte) 0x44, (byte) 0x45, (byte) 0x53, (byte) 0x45,
                (byte) 0x4e, (byte) 0x56, (byte) 0x4f, (byte) 0x4c,
                (byte) 0x56, (byte) 0x49, (byte) 0x4d, (byte) 0x45,
                (byte) 0x4e, (byte) 0x54, (byte) 0x4f, (byte) 0x21
        };
        MatrizEstado matrizEstado = stateFromBytes(textoSimples);
        matrizEstado = matrizEstado.xor(keySchedule[0]);

        for (int i = 1; i < 10; i++) {
            matrizEstado = SBox.getNovaMatriz(matrizEstado);
            matrizEstado = matrizEstado.shiftRows();
            // MixColumns
            matrizEstado = matrizEstado.mixColumns();
            // AddRoundKey
            matrizEstado = matrizEstado.xor(keySchedule[i]);
        }
        matrizEstado = SBox.getNovaMatriz(matrizEstado);
        matrizEstado = matrizEstado.shiftRows();
        matrizEstado = matrizEstado.xor(keySchedule[10]);

        System.out.println(matrizEstado.toString());
    }

    private static RoundKey keyFromBytes(byte[] chave) {
        RoundKey roundKey = new RoundKey();
        for (int i = 0; i < chave.length; i += 4) {
            roundKey.setWord(i / 4, new Palavra(chave[i], chave[i + 1], chave[i + 2], chave[i + 3]));
        }
        return roundKey;
    }

    private static MatrizEstado stateFromBytes(byte[] chave) {
        MatrizEstado matrizEstado = new MatrizEstado();
        for (int i = 0; i < 16; i += 4) {
            matrizEstado.setWord(i / 4, new Palavra(chave[i], chave[i + 1], chave[i + 2], chave[i + 3]));
        }
        return matrizEstado;
    }

    private static RoundKey geraRoundKey(int roundKeyNumber) {
        RoundKey chaveAnterior = keySchedule[roundKeyNumber - 1];
        Palavra primeira = geraPrimeiraPalavra(roundKeyNumber);
        Palavra segunda = chaveAnterior.getPalavra(1).xor(primeira);
        Palavra terceira = chaveAnterior.getPalavra(2).xor(segunda);
        Palavra quarta = chaveAnterior.getPalavra(3).xor(terceira);
        return new RoundKey(primeira, segunda, terceira, quarta);
    }

    private static Palavra geraPrimeiraPalavra(int roundKeyNumber) {
        //Buscar ultima palavra da chave anterior
        RoundKey chaveAnterior = keySchedule[roundKeyNumber - 1];
        Palavra ultimaPalavraChaveAnterior = chaveAnterior.getPalavra(3);
        // Rotacionar palavra (RotWord)
        Palavra rotWord = ultimaPalavraChaveAnterior.rotate();
        // Substituir palavra (SubWord)
        Palavra subWord = SBox.getNovaPalavra(rotWord);
        // Gerar nova palavra (RoundConstant)
        Palavra roundConstant = RoundConstant.getRoundConstant(roundKeyNumber);
        // XOR entre SubWord e RoundConstant
        Palavra xor = subWord.xor(roundConstant);
        // XOR da primeira palavra da chave anterior com a palavra gerado pelo XOR anterior
        return chaveAnterior.getPalavra(0).xor(xor);
    }
}
