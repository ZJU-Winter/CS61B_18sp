import java.util.LinkedList;
import java.util.List;

public class HuffmanDecoder {
    public static void main(String[] args) {
        String hufFile = args[0];
        String fileToWrite = args[1];
        ObjectReader or = new ObjectReader(hufFile);
        BinaryTrie binaryTrie = (BinaryTrie) or.readObject();
        BitSequence bitSequence = (BitSequence) or.readObject();
        List<Character> list = new LinkedList<>();
        while (bitSequence.length() != 0) {
            Match match = binaryTrie.longestPrefixMatch(bitSequence);
            list.add(match.getSymbol());
            int newLength = bitSequence.length() - match.getSequence().length();
            bitSequence = bitSequence.lastNBits(newLength);
        }
        char[] content = new char[list.size()];
        int i = 0;
        for (char ch : list) {
            content[i] = ch;
            i += 1;
        }
        FileUtils.writeCharArray(fileToWrite, content);
    }
}
