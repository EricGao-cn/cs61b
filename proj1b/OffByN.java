public class OffByN implements CharacterComparator{
    private int N;
    public OffByN(int n) {
        N = n;
    }

    public boolean equalChars(char a, char b) {
        return Math.abs(a - b) == N;
    }
}
