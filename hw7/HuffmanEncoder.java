import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : inputSymbols) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        String filename = args[0];
        char[] content = FileUtils.readFile(filename);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(content);
        BinaryTrie binaryTrie = new BinaryTrie(frequencyTable);
        ObjectWriter ow = new ObjectWriter(filename + ".huf");
        ow.writeObject(binaryTrie);
        Map<Character, BitSequence> lookupTable = binaryTrie.buildLookupTable();
        List<BitSequence> sequences = new LinkedList<>();
        for (char ch : content) {
            sequences.add(lookupTable.get(ch));
        }
        BitSequence bigOne = BitSequence.assemble(sequences);
        ow.writeObject(bigOne);
    }
}
