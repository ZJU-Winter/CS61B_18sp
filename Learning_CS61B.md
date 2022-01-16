## Berkeley CS61B Data Structure
### Introduction to Java
#### Essential

**Helloworld**

* The program consists of a class declaration, which is declared using the keywords public class. In Java, all code lives inside of classes.
* The code that is run is inside of a method called main, which is declared as `public static void main(String[] args)`.
* We use curly braces `{` and `}` to denote the beginning and the end of a section of code.
Statements must end with semi-colons.

**Running a Java program**

* The most common way to execute a Java program is to run it through a sequence of two programs. The first is the Java compiler, or `javac`. The second is the Java interpreter, or `java`.

**Variables and Loops**

```java
ublic class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
        x = "horse";
    }
}
```

To summarize, static typing has the following advantages:

- The compiler ensures that all types are compatible, making it easier for the programmer to debug their code.
- Since the code is guaranteed to be free of type errors, users of your compiled programs will never run into type errors. For example, Android apps are written in Java, and are typically distributed only as .class files, i.e. in a compiled format. As a result, such applications should never crash due to a type error since they have already been checked by the compiler.
- Every variable, parameter, and function has a declared type, making it easier for a programmer to understand and reason about code.

**Defining Functions in Java**

```java
public class LargerDemo {
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}
```

#### Objects

```java
public class Dog {
    public static void makeNoise() {
        System.out.println("Bark!");
    }
}
```

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog.makeNoise();
    }
}
```

* Every method is associated with class
* To run a class, we must define a main method
  * Not all classes have a main method

**Instance Variables and Object Instantiation**

```java
public class Dog {
    public int weightInPounds;// instance variable

    public Dog(int w) {//constructor
        weightInPounds = w;
    }

    public void makeNoise() {
      //Non-static method or instance method
      //if the method needs to use instance variabls, it should be 								non-static
      //if the method is going to be invoked by an instance, it should be 				non-static
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }    
    }
}
```



```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(20);
        d.makeNoise();
    }
}
```

#### Static and Instance Methods

* Static methods are invoked using the CLASS NAME

```java
public static Dog maxDog(Dog d1, Dog d2) {
  if (d1.weightInpounds > d2.weightInpounds) {
    return d1;
  } else
    return d2;
}

Dog d1 = new Dog(10);
Dog d2 = new Dog(100);
Dog bigger = Dog.maxDog(d1,d2);
```



* Instance methods are invoked using an INSTANCE NAME

```java
public Dog maxDog(Dog d) {
  if (this.weightInpounds > d.weightInpounds)
    return this;
  else
    return d;
}

Dog d1 = new Dog(10);
Dog d2 = new Dog(100);
Dog bigger = d1.maxDog(d2);
```



* Static methods can't access "my" instance variables



### Lists

8 primitive types and everything else, including arrays, is a **reference type**

#### Class instantiations

 When we instantiate an Object

* Java first allocates a box of bits for each instance variables of the class filled with a default value
* The constructor then fills every such box some other value

* Address in Java are 64 bits
* If the object is created in the memory location xxxx bit, then new return xxx .

When we declare a variable of any reference type

* Allocate exactly a box of size 64 bits.
* Theses bits can be either set to:
  * Null
  * **ARROW:**The 64 bit "address" of a specific instance of that class (return by value)
  * The box and pointer notation

#### Parameter Passing

Passing parameter obeys the same rule: simply copy the bits to the new scope

```java
public class PassByValueFigure {
    public static void main(String[] args) {
           Walrus walrus = new Walrus(3500, 10.5);
           int x = 9;

           doStuff(walrus, x);
           System.out.println(walrus);
           System.out.println(x);
    }

    public static void doStuff(Walrus W, int x) {
           W.weight = W.weight - 100;
           x = x - 5;
    }
}//x不会变 weight减小100
```

#### Instantiation of Arrays

![image-20220108213833663](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220108213833663.png)

```java
int[] a = new int[]{0,1,2,95,4};
```

* Creates a 64 bits for storing an int array address.(declaration)
* Creates a new Object, in this case an int array.(instantiation)
* Puts the address of this new object into the 64 bits box.(assignment)

#### IntLists

```java
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
}
```

```java
IntList L = new IntList(15, null);
L = new IntList(10, L);
L = new IntList(5, L);
```

```java
IntList L = new IntList(5, null);
L.rest = new IntList(10, null);
L.rest.rest = new IntList(15, null);
```

* IntLists_Size_Recursive

```java
public int size() {
  if (this.rest == null)
    return 1;
  else
    return 1 + this.rest.size();
}
```

* IntLists_Size_Iterative

```java
public int size_iter() {
  IntList p = this;
  int size = 0;
  while (p != null) {
    size++;
    p = p.rest;
  }
  return size;
}
```

* IntLists_Get_Recursive

```java
public int get(int i) {
  if (i == 0)
    return first;
  else
    return rest.get(i - 1);
}
```

* IntList_Get_Iterative

```java
public int get_iterative(int i) {
  if (i > this.size())
    return -1;
  IntList p = this;
  for (int index = 0; index < i; index++) {
    p = p.rest;
  }
  return p.first;
}
```

#### SLLists(Singly Linked)

```java
public class IntNode{
  public int item;
  public IntNode next;
  
  public IntNode(int i, IntNode n){
    item = i;
    next = n;
  }
}
```

```java
public class SLList{
  private IntNode first;
  
  public SLList(int x){
    first = IntNode(x, null);
  }
}
```

```java
IntList L1 = new IntList(5,null);
SLList L2 = new SLList(5); 
```

* Methods

```java
public void addFirst(int x){
  first = new IntNode(x,first);
}

public int getFirst(){
  return first.item;
}

public void addLast(int x){
  IntNode p = first;
  while(p.next != null) {
    p = p.next;
  }
  p.next = new IntNode(x,null);
}

private static int size(IntNode p) {
        if (p.next == null)
            return 1;
        return 1 + size(p.next);
	}

public int size_recur() {
        return size(first);
  }

public int size(){
  IntNode p = first;
  int size=0;
  while(p.next != null){
    p = p.next;
    size++;
  }
  return size;
}
SLList L = new SLList(15);
L.addFirst(10);
L.addFirst(5);
```

* Nested Classes

```java
public class SLList{
  private static class IntNode{//static: never looks outwards
 // static cannot access any of the outer class's stuff
    public int item;
    public IntNode next;
    public IntNode(int i, IntNode n){
      item = i;
      next = n;
    }
  }
  
  private IntNode first;
  public SLList(int x){
    first = new IntNode(x,null);
  }
}
```

* Improvement_size()

```java
public class SLList{
  private static class IntNode{//static: never looks outwards
 // static cannot access any of the outer class's stuff
    public int item;
    public IntNode next;
    public IntNode(int i, IntNode n){
      item = i;
      next = n;
    }
  }
  
  private IntNode first;
  private int size;
  public SLList(int x){
    first = new IntNode(x,null);
    size = 1;
  }
}
```

* Improvement_emptySLList

```java
  public SLList(int x){
    first = new IntNode(x,null);
    size = 1;
  }
	public SLList(){
    first = null;
    size = 0;
  }//a bug for adding a Node at the last

public void addLast(int x){
  IntNode p = first;
  if (first == null){
    first = new IntNode(x,null);
    return;
  }//ugly!!!
  while(p.next != null) {
    p = p.next;
  }
  p.next = new IntNode(x,null);
}
```

* Sentinel Node

```java
public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /* the first item (if it exsits) is at sentinel.next */
    private IntNode sentinel;
    private int size;

    public SLList(int x) {
        sentinel = new IntNode(-1, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public SLList() {
        sentinel = new IntNode(-1, null);
        size = 0;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst() {
        return sentinel.next.item;
    }

    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /** return the size of list from IntNode p */
    private static int size(IntNode p) {
        if (p.next == null)
            return 0;
        return 1 + size(p.next);
    }

    public int size_recur() {
        return size(sentinel);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        System.out.println(L.size_recur());
    }
}
```

#### DLList(Doubly Linked)

Why we need Double Linked Lists?

* Add `IntNode last` to make `addLast` fast
* `last` makes remove the last slow (needs to fine the second to last node)

Sometimes the `last` points the real node and sometime it points the sentinel

* Solution: create two sentinel `sentFront` and `sentBack`

![image-20220110215724458](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220110215724458.png)

![image-20220110215929665](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220110215929665.png)

#### Generic List

```java
public class SLList<LochNess> {
    private static class StuffNode {
        public LochNess item;
        public IntNode next;

        public IntNode(LochNess i, IntNode n) {
            item = i;
            next = n;
        }
    }
}

public class SLListLauncher{
  public static void main(String[] args){
    SLList<String> s1 = new SLList<String>("bone");
    s1.addFirst("thugs");
  }
}
```

#### ArrayList

Why we need ArrayList?

* get(int i) is slow to any i not near the sentinel nod

```java
public class AList {
    private int[] items;
    private int size;

    /** create an empty Array List */
    public AList() {
        items = new int[100];
        size = 0;
    }

    /** insert x into the back of the List */
    public void addLast(int x) {
        items[size] = x;
        size++;
    }

    /** return the item from the back of the list */
    public int getLast() {
        return items[size - 1];
    }

    /** get the ith item from the list */
    public int get(int i) {
        return items[i];
    }

    /** return the number of items in the list */
    public int size() {
        return size;
    }

    /**
     * delete the last item of the list
     * return the value of the deleted item
     */

    public int removeLast() {
        int value = getLast();
        size = size - 1;
        return value;
    }
}
```

* Resizing the array

```java
public void addLast(int x) {
  if (size == items.length){
    resizeList(size + RFACTOR);//unusably bad
  }
  items[size] = x;
  size++;
}

private void resizeList(int capacity){
    int[] a = new int[capacity];
    System.arraycopy(items,0,a,0,size);
    items = a;
}
```

![image-20220111131224107](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220111131224107.png)

```java
public void addLast_Fast(int x) {
  if (size == item.length){
    resize(size * RFACTOR);
  }
  items[size] = x;
  size++;
}
```

* Memory Efficiency
    * Define the "usage ratio" R = size/items.length;
    * Half array size when R < 0.25
* Generic Array

```java
public class AList<Item> {
    private Item[] items;
    private int size;
    private int RFACTOR = 2;

    /** create an empty Array List */
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    private void resizeList(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** insert x into the back of the List */
    public void addLast(Item x) {
        if (size == items.length) {
            resizeList(size * RFACTOR);// unusably bad
        }
        items[size] = x;
        size++;
    }

    /** return the item from the back of the list */
    public Item getLast() {
        return items[size - 1];
    }

    /** get the ith item from the list */
    public Item get(int i) {
        return items[i];
    }

    /** return the number of items in the list */
    public int size() {
        return size;
    }

    /**
     * delete the last item of the list
     * return the value of the deleted item
     */

    public Item removeLast() {
        Item value = getLast();
        size = size - 1;
        return value;
    }
}
```

* Loitering 
    * After removeLast(): `items[size - 1] = null;`

### Test

#### Ad Hoc Testing

```java
public class TestSort{
  public static void testSort(){
    String[] input = {"i", "have", "an", "egg"};
    String[] expected = {"an", "egg", "have", "i"};
    
    Sort.sort(input);
    if (input != expected){//wrong!! input and expected are address
    if (!java.util.Arrays.equals(input,expected)){  
      System.out.println("Error! There seems to be a problem with Sor.sort.")
    }
    for (int i = 0; i < input.length; i += 1) {
            if (!input[i].equals(expected[i])) {
                System.out.println("Mismatch in position " + i + ", expected: " + expected + ", but got: " + input[i] + ".");
                break;
            }
  }
  
  
  
  public static void main(String[] args){
    testSort();
  }
}
```



#### J-Unit

```java
org.junit.Assert.assertArrayEquals(expected, input);
```

#### J-Unit-beter syntax 

```java
@org.junit.Test
org.junit.Assert.asserArrayEquals(expected, input);
```



```java
import org.junit.Test;
import org.junit.Assert.*
@Test
asserArrayEquals(expected, input);
@Test
...
  
```

### Inheritance

![subclass](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/subclass.png)

*   Step1: Define a type for the general list hypernym
*   Step2: Specify that ArrayList and SLList are hyponyms of that type

#### interface

*   The new List61B is what Java calls an **interface**. It is essentially a contract that specifies what a list must be able to do, but it doesn't provide any implementation for those behaviors.

```java
public interface List61B<Item> {
    public void addFirst(Item x);
    public void add Last(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();
}
```

#### implements

*   Use the **implements** keyword to tell the java compiler that SLList and ArrayList are hyponyms of List61B

```java 
public class AList<Item> implements List61B<Item> {
  
}
```

```java
public static String longest(List61B<String> list) {
    int maxDex = 0;
    for (int i = 0; i < list.size(); i += 1) {
        String longestString = list.get(maxDex);
        String thisString = list.get(i);
        if (thisString.length() > longestString.length()) {
            maxDex = i;
        }
    }
    return list.get(maxDex);
}
```

#### Overriding and Overloading

*   If a subclass has a method with the exact same signature as in the superclass, the subclass **overrides** the method

```java
public interface List61B<Item>{
  public void addLast(Item y);
}

public class AList<Item> implements List61B<Item>{
  public void addLast(Item x){
    ...
  }
}
```

*   Methods with the same name but different signature are overloaded

```java
public interface Animal{
  public void makeNoise();
}

@Overide
public class Pig implements Animal{
  public void makeNoise(){//override
    
  }
}

public class Dog implements Animal{
  public void makeNoise(Dog d){//overload
    
  }
}
```

*   Hard every overriding method with the `@Override` annotation
    *   The only effect of this tag is that the code won't compile if it is not actually an overriding method

#### Interface Inheritance

The capabilities of a subclass using the **implements** keyword is known as **interface inheritance**

*   Interface: the list of all method signatures
*   Inheritance: the subclass "inherits" the interface from a superclass
*   Specifies what the subclass can do, but not how
*   Subclasses <u>must</u> **override** all of these methods
    *   Will fail to compile otherwise

#### Implementation Inheritance:Default Method

*   Interface inheritance:
    *   Subclass inherits signatures, but not implemtation
*   Implementation inheritance:
    *   Subclass can inherit signatures and implementation.
*   Use the default keyword to specify a method that subclasses should inherit from an interface

```java
public interface List61B<Item> {
    public void addFirst(Item x);
    public void add Last(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();
  
  	default public void print(){
     	for (int i = 0; i < size(); i += 1) {
        System.out.prinln(get(i) + " ");
      }
    }
}
```

#### Overriding default method

Since the `print` method is not efficient for SLList

```java
public class SLList<Item> implements List61B<Item>{
  @Override
  public void print(){
    for (IntNode p = sentinel.next; p != null; p = p.next){
      System.out.println(p.item + " ");
    }
  }
}
```

#### Dynamic method selection

```java
Demo.java
public static void main(String[] args) {
  List61B<String> someList = new SLList<String>();
  someList.inserFront("elk").
  ...
  someList.print();//SLList override the "print" function in the List61B
} 
```

*   Every variable in Java has a "compile-time type", a.k.a. "static type"
    *   This is the type specified at **declaration**. Never changes!
*   Variables also have a "run-time type", a.k.a. "dynamic type"
    *   This is the type specified at **instantiation** (e.g. when using new)
    *   Equal to the type of the object being pointed at.

![image-20220112205227720](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220112205227720.png)

*   Suppose we call a method of an object using a variable with:
    *   Compile-time type X
    *   Run-time type Y
*   If Y **override** the method, Y' method is used instead

#### More Dynamic Method Selection, Overloading vs. Overriding

Consider the function call foo.bar(x1), where foo has static type TPrime, the x1 has static type T1.



At compile time, the compiler verifies that TPrime has a method that can handle T1. It then records the signature of this method.

*   Note: if there are multiple methods that can handle T1, the compiler records the "most specific" one. For example, if T1 = Dog, and TPrime has bar(Dog) and bar(Animal), it will record bar(Dog).

At the run time, if foo's dynamic type overrides the **recorded signature**, use the override one! Otherwise, use TPrime's version.

### Inheritance 2

#### Implementation Inheritance: Extends

*   When a class is a hyponym of an interface, we used **implements**
    *   `SLList<Item> implements List61B<Item>`

*   If you want one class to be a hyponym of another class, you use **extend**

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220116180125670.png" alt="image-20220116180125670" style="zoom:50%;" />

```java
public class RotatingSLList<Item> extends SLList<Item> {
  public void rotateRight() {
    Item x = removeLast();
    addFirst(x);
  }
}
```

*   Because of extends, RotatingSLList inherits all members of **SLList**

    *   All instance and static variables
    *   All methods
    *   All nested classes

    *   Constructors are not inherited

#### Extends with Overriding

*   Suppose we want to build an SLList that:
    *   Remembers all items that have been destroyed by `removeLast()`
    *   Has an additional method `printLostItems()`

```java
public class VengefulSLList<Item> extends SLList<Item> {
  private SLList<Item> deletedItems;
  public VengefulSLList() {
    deletedItems = new SLList<Item>();
  }
  @Override
  public Item removeLast() {
    Item x = super.removeLast();//we can't just copy the method from SLList because some members are private
    deletedItems.addLast(x);
  }
  public void printLostItems() {
    deletedItems.print();
  }
}
```

#### Constructor

*   All constructors must start with a class to one of the super class's constructors
    *   If a VengefulSLList "is-an" SLList, then it follows that every VengefulSLList must be set up like an SLList.
    *   If you didn't call SLList constructor, sentinel would be null !
*   You can explicitly call the constructor with the keyword `super();`
*   If you don't explicitly call the constructor, java will automatically do it.

```java
public VengefulSLList() {
  super();
  deletedItems = new SLList<Item>();
}
```

```java
public VengefulSLList() {
  deletedItems = new SLList<Item>();
}
```

*   If you want to use a super constructor other than the no-arguments constructor, you can give parameters to super.

```java
public VengefulSLList(Item x) {
  super(x);
  deletedItems = new SLList<Item>();
}

not equivalent!!
call super()!!
public VengefulSLList(Item x) {
  deletedItems = new SLList<Item>();
}
```



