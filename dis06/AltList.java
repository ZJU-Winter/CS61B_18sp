public class AltList<X, Y> {
    private X item;
    private AltList<Y, X> next;

    AltList(X item, AltList<Y, X> next) {
        this.item = item;
        this.next = next;
    }

    public AltList<Y, X> pairsSwapped() {
        AltList<Y, X> rst = new AltList<Y, X>(next.item, new AltList<X, Y>(item, null));
        if (next.next != null) {
            rst.next.next = this.next.next.pairsSwapped();
        }
        return rst;
    }

    public AltList<Y, X> pairsSwappedIter() {
        AltList<Y, X> rst = new AltList<Y, X>(next.item, new AltList<X, Y>(item, null));
        AltList<X, Y> ptrOfOriginal = this.next.next;
        AltList<Y, X> ptrOfResult = rst;
        while (ptrOfOriginal != null) {
            ptrOfResult.next.next = new AltList<Y, X>(ptrOfOriginal.next.item,
                    new AltList<X, Y>(ptrOfOriginal.item, null));
            ptrOfOriginal = ptrOfOriginal.next.next;
            ptrOfResult = ptrOfResult.next.next;
        }
        return rst;
    }

    public static void main(String[] args) {
        AltList<Integer, String> list = new AltList<Integer, String>(5,
                new AltList<String, Integer>("cat",
                        new AltList<Integer, String>(10,
                                new AltList<String, Integer>("dog", null))));
        AltList<String, Integer> res = list.pairsSwappedIter();
    }
}
