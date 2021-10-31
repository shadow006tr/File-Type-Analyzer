import java.util.*;

class AsciiCharSequence implements CharSequence {
    // implementation
    byte[] bytes;

    public AsciiCharSequence(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }

    @Override
    public int length() {
        return this.bytes.length;
    }

    @Override
    public char charAt(int index) {
        return (char) this.bytes[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        byte[] subBytes = Arrays.copyOfRange(this.bytes, start, end);
        return new AsciiCharSequence(subBytes);
    }

    @Override
    public String toString() {
        StringBuilder sub = new StringBuilder();
        for (byte aByte : this.bytes) {
            sub.append((char) aByte);
        }
        return sub.toString();
    }

}