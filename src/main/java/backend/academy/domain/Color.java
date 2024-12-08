package backend.academy.domain;

public record Color(int r, int g, int b) {

    private static final int H_BYTE = 16;
    private static final int M_BYTE = 8;

    public int getRGB() {
        return (r << H_BYTE) | (g << M_BYTE) | b;
    }
}
