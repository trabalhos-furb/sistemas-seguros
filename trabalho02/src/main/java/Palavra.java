public class Palavra {

    private byte[] bytes = new byte[4];

    public Palavra(byte first, byte second, byte third, byte fourth) {
        bytes[0] = first;
        bytes[1] = second;
        bytes[2] = third;
        bytes[3] = fourth;
    }

    public byte getByte(int i) {
        return bytes[i];
    }

    public void setByte(int i, byte value) {
        bytes[i] = value;
    }

    /**
     * O rotate nunca altera a palavra existente, para evitar problemas.
     * @return nova palavra com os bytes rotacionados
     */
    public Palavra rotate() {
        return new Palavra(bytes[1], bytes[2], bytes[3], bytes[0]);
    }

    public Palavra xor(Palavra palavra) {
        byte byte0 = (byte) (palavra.bytes[0] ^ this.bytes[0]);
        byte byte1 = (byte) (palavra.bytes[1] ^ this.bytes[1]);
        byte byte2 = (byte) (palavra.bytes[2] ^ this.bytes[2]);
        byte byte3 = (byte) (palavra.bytes[3] ^ this.bytes[3]);
        return new Palavra(byte0, byte1, byte2, byte3);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");
        builder.append(Integer.toHexString(bytes[0] & 0xff));
        builder.append(",");
        builder.append(Integer.toHexString(bytes[1] & 0xff));
        builder.append(",");
        builder.append(Integer.toHexString(bytes[2] & 0xff));
        builder.append(",");
        builder.append(Integer.toHexString(bytes[3] & 0xff));
        builder.append(" ");
        builder.append("]");
        return builder.toString();
    }
}
