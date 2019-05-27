public class MatrizEstado extends RoundKey {
    public MatrizEstado xor(RoundKey roundKey) {
        MatrizEstado novaMatrizEstado = new MatrizEstado();
        novaMatrizEstado.setWord(0, roundKey.getPalavra(0).xor(this.getPalavra(0)));
        novaMatrizEstado.setWord(1, roundKey.getPalavra(1).xor(this.getPalavra(1)));
        novaMatrizEstado.setWord(2, roundKey.getPalavra(2).xor(this.getPalavra(2)));
        novaMatrizEstado.setWord(3, roundKey.getPalavra(3).xor(this.getPalavra(3)));
        return novaMatrizEstado;
    }

    public MatrizEstado shiftRows() {
        MatrizEstado matrizEstado = new MatrizEstado();
        Palavra palavra = this.getPalavra(0);
        Palavra palavra1 = this.getPalavra(1);
        Palavra palavra2 = this.getPalavra(2);
        Palavra palavra3 = this.getPalavra(3);
        Palavra novaPalavra0 = new Palavra(palavra.getByte(0), palavra1.getByte(1), palavra2.getByte(2), palavra3.getByte(3));
        Palavra novaPalavra1 = new Palavra(palavra1.getByte(0), palavra2.getByte(1), palavra3.getByte(2), palavra.getByte(3));
        Palavra novaPalavra2 = new Palavra(palavra2.getByte(0), palavra3.getByte(1), palavra.getByte(2), palavra1.getByte(3));
        Palavra novaPalavra3 = new Palavra(palavra3.getByte(0), palavra.getByte(1), palavra1.getByte(2), palavra2.getByte(3));
        matrizEstado.setWord(0, novaPalavra0);
        matrizEstado.setWord(1, novaPalavra1);
        matrizEstado.setWord(2, novaPalavra2);
        matrizEstado.setWord(3, novaPalavra3);
        return matrizEstado;
    }

    public MatrizEstado mixColumns() {
        MatrizEstado matrizEstado = new MatrizEstado();
        matrizEstado.setWord(0, TabelaMultiplicacao.multiply(this.getPalavra(0)));
        matrizEstado.setWord(1, TabelaMultiplicacao.multiply(this.getPalavra(1)));
        matrizEstado.setWord(2, TabelaMultiplicacao.multiply(this.getPalavra(2)));
        matrizEstado.setWord(3, TabelaMultiplicacao.multiply(this.getPalavra(3)));
        return matrizEstado;
    }
}
