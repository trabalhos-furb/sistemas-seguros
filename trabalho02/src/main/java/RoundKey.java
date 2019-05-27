public class RoundKey {
    private Palavra[] chave = new Palavra[4];

    public RoundKey() {

    }

    public RoundKey(Palavra primeira, Palavra segunda, Palavra terceira, Palavra quarta) {
        chave[0] = primeira;
        chave[1] = segunda;
        chave[2] = terceira;
        chave[3] = quarta;
    }

    public void setWord(int i, Palavra word) {
        this.chave[i] = word;
    }

    public Palavra getPalavra(int i) {
        return this.chave[i];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("{");
        for (int i = 0; i < chave.length; i++) {
            builder.append(Integer.toHexString(chave[0].getByte(i) & 0xff));
            builder.append(" ");
            builder.append(Integer.toHexString(chave[1].getByte(i) & 0xff));
            builder.append(" ");
            builder.append(Integer.toHexString(chave[2].getByte(i) & 0xff));
            builder.append(" ");
            builder.append(Integer.toHexString(chave[3].getByte(i) & 0xff));
            if (i != chave.length -1) {
                builder.append("\n");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
