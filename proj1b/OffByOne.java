public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(Character.toUpperCase(x) - Character.toUpperCase(y)) == 1;
    }
}
