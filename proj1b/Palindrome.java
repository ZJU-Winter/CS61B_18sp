public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        } else {
            LinkedListDeque<Character> L = new LinkedListDeque<>();
            char charAtIndex;
            for (int index = 0; index < word.length(); index++) {
                charAtIndex = word.charAt(index);
                L.addLast(charAtIndex);
            }
            return L;
        }
    }

    public boolean isPalindrome(String word) {
        if (word.equals("") || word.length() == 1) {
            return true;
        } else {
            Deque L = wordToDeque(word);
            String leftToRight = "", rightToLeft = "";
            int index;
            for (index = 0; index < word.length() / 2; index += 1) {
                leftToRight += L.removeFirst();
                rightToLeft += L.removeLast();
            }
            return (leftToRight.equals(rightToLeft));
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return newHelper(word, 0, word.length() - 1, cc);
    }

    private boolean isPalindromeRecursive(String word) {
        return isPalindromeRecursiveHelper(word, 0, word.length() - 1);
    }

    private boolean isPalindromeRecursiveHelper(String word, int front, int back) {
        if (word.equals("") || front >= back) {
            return true;
        } else {
            return (word.charAt(front) == word.charAt(back))
                    &&
                    isPalindromeRecursiveHelper(word, front + 1, back - 1);
        }
    }

    private boolean newHelper(String word, int front, int back, CharacterComparator cc) {
        if (word.equals("") || front >= back) {
            return true;
        } else {
            return cc.equalChars(word.charAt(front), word.charAt(back))
                    &&
                    newHelper(word, front + 1, back - 1, cc);
        }
    }
}
