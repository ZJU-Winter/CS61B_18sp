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

### Inheritance: Extends, Casting, HoFs

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

#### The Object class

*   Every type in Java is a descendant of the Object class

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220116192707897.png" alt="image-20220116192707897" style="zoom:50%;" />

#### Type Checking and Casting

*   Dynamic Method Selection

![img](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/dynamic_selection.png)

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220116210106172.png" alt="image-20220116210106172" style="zoom:30%;" />

*   Compile time
    *   Compiler allows assignments based on compile-time types

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220116210444382.png" alt="image-20220116210444382" style="zoom:50%;" />

*   Compile-time Types and Expressions

    ```java
    SLList<Integer> sl = new VengefulSLList<Integer>();		
    ```

    *   Compile-time type of right hand side expression is VengefulSLList
    *   A VengefulSLList `is-an` SLList, so assignment is allowed

    ```java
    VengefulSLList<Integer> sl = new SLList<Integer>();	
    ```

    *    Compile-time type of right hand side expression is SLList

    *   An SLList is not necessarily a VengefulSLList, compilation error!

*   Expressions

    *   expressions using the `new` keyword also have compile-time types

    ```java
    SLList<Integer> sl = new VengefulSLList<Integer>();
    
    VengefulSLList<Integer> vsl = new SLList<Integer>();//error!
    ```

    *   method calls have compile-time types equal to their declared type

    ```java
    public static Dog maxDog(Dog d1, Dog d2) { ... }
    
    Poodle frank = new Poodle("Frank", 5);
    Poodle frankJr = new Poodle("Frank Jr.", 15);
    
    Dog largerDog = maxDog(frank, frankJr);
    Poodle largerPoodle = maxDog(frank, frankJr); //does not compile! RHS has compile-time type Dog
    ```

*   Casting

    ```java
    Poodle largerPoodle = (Poodle) maxDog(frank, frankJr);
    ```

*   Higher Order Function

    *   A function that treats another function as data

    ```python
    def tenX(x):
      return 10*x
    
    def de_twice(f,x):
      return f(f(x))
    
    print(do_twice(tenX,2))#200 
    ```

    ```java
    public interface IntUnaryFunction {
        public int apply(int x);
    }
    ```

    ```java
    public class TenX implements IntUnaryFunction {
        public int apply(int x) {
            return 10 * x;
        }
    }
    ```

    ```java
    public class HoFDemo {
        public static int do_twice(IntUnaryFunction f, int x) {
            return f.apply(f.apply(x));
        }
    
        public static void main(String[] args) {
            TenX tenX = new TenX();
            System.out.println(do_twice(tenX, 2));
        }
    }
    ```

    

### Inheritance: Comparables, Abstract Classes, HoFs

#### Subtype Polymorphism

*   Subtype Polymorphism vs.  Explicit Higher Order Function

    *   Explicit Higher Order Function

    ```python
    def print_larger(x,y,compare,stringify):
      if compare(x,y):
        return stringify(x)
      return stringif(y)
    ```

    *   Subtype Polymorphism

    ```python
    def print_larger(x,y):
      if x.largerthan(y):
        return x.str()
      return y.str()
    ```

#### The Max Function

```java
public static Object max(Object[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        if (items[i] > items[maxDex]) {//error!
            maxDex = i;
        }
    }
    return items[maxDex];
}

public static void main(String[] args) {
    Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
    Dog maxDog = (Dog) max(dogs);
    maxDog.bark();
}
```

Errors while comparing, one solution is to write a `maxDog` in thee dog class

```java
public static Dog maxDog(Dog[] dogs) {
    if (dogs == null || dogs.length == 0) {
        return null;
    }
    Dog maxDog = dogs[0];
    for (Dog d : dogs) {
        if (d.size > maxDog.size) {
            maxDog = d;
        }
    }
    return maxDog;
}
```

then we'd have to do the same for any class we define later.

We'd need to write a `maxCat` function, a `maxPenguin` function 

Without overriding the operators, we use `interface` with `compareTo`to guarantee that a `Dog` class *overrides the operator* 

```java
OurComparable.java

public interface OurComparable {
    public int compareTo(Object o);
}
```

```java
Dog.java
  
public class Dog implements OurComparable {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareTo(Object o) {//important!!
        Dog dog = (Dog) o;
        return this.size - dog.size;
    }
}
```

```java
Maximizer.java
  
public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i += 1) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog[] dogs = { new Dog("1", 10), new Dog("2", 15), new Dog("Max", 20) };
        Dog maxDog = (Dog) Maximizer.max(dogs);
        maxDog.bark();
    }
}
```

#### Comparables

*   Two issues about `OurComparable`
    *   Awkward casting to/from Objects
        *   `Dog maxDog = (Dog) Maximizer.max(dogs);`
        *   `Dog dog ='' (Dog) o;`
    *   We made it up:
        *   No existing classes implement `OurComparable`(e.g. String)
        *   No existing classes use `OurComparable`(e.g. no built-in max Function)

*   Use comparable

    ```java
    public class Dog implements Comparable<Dog> {
        private String name;
        private int size;
    
        public Dog(String n, int s) {
            name = n;
            size = s;
        }
    
        public void bark() {
            System.out.println(name + " says: bark");
        }
    		@Override
        public int compareTo(Dog dog) {
            return this.size - dog.size;
        }
    }
    ```

    

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220117180418977.png" alt="image-20220117180418977" style="zoom:50%;" />

#### Comparators

*   Natural Order
    *   The terms "Natural Order" is sometimes used to refer to the ordering implied by a `Comparable's CompareTo` method.

*   May wish to order objects in a different way, e.g. by Name

*   `Comparator`

    ```java
    Dog.java
    
    //a nested class
    private class NameComparator implements Comparator<Dog> {
      public int compare(Dog dog1, Dog dog2) {
        return dog1.name.compareTo(dog2.name);
      }
    }
    
    public static Comparator<Dog> getNameComparator() {
      return new NameComparator();
    }
    ```

    ```java
    DogLauncher.java
    
    public class DogLauncher {
      public static void main(String[] args) {
        Dog[] dogs = { new Dog("1", 10), new Dog("2", 15), new Dog("Max", 20) };
        Comparator<Dog> nc = Dog.getNameComparator();
        if (nc.compare(dogs[0], dogs[1]) > 0) {
          dogs[0].bark();
        } else {
          dogs[1].bark();
        }
      }
    }
    ```

### Inheritance: Libraries, Abstract Classes and Packages

#### Abstract Data Type(ADT)

<img src="https://joshhug.gitbooks.io/hug61b/content/assets/deque.png" alt="deque" style="zoom:50%;" />

`interface Deque` that both Array and LinkedList implements.

`Deque` provides a list of methods, which are actually **implemented** by ArrayDeque and LinkedListDeque.

`Deque` is an ADT

#### Libraries

*   List
    *   Linked List 
    *   ArrayList

```java
ListDemo

public static List<String> getWords(String inputFileName) {
    List<String> words = new ArrayList<String>();
    In in = new In();
    while (!in.isEmpty()) {
       	words.add(in.readString());     
    }
    return words;
}
```

*   Set
    *   HashSet
    *   TreeSet

```java
SetDemo

public static int countUniqueWords(List<String> words) {
    Set<String> wordSet = new HashSet<>();
    for (String ithWord : words) {
           wordSet.add(ithWord);        
    }
    return wordSet.size();
}
```

```java
public static int countUniqueWords(List<String> words) {
    Set<String> wordSet = new HashSet<>(words);
    return wordSet.size();
}
```

*   Map
    *   HashMap
    *   TreeMap

```java
public static Map<String, Integer> collectWordCount(List<String> words, List<String> target) {
    Map<String, Integer> counts = new HashMap<String, Integer>();
    for (String t: target) {
        counts.put(s, 0);
    }
    for (String s: words) {
        if (counts.containsKey(s)) {
            counts.put(word, counts.get(s)+1);
        }
    }
    return counts;
}
```

#### Abstract Classes

##### Interface summary

*   Interfaces may combine a mix of **abstract** and **default** methods
    *   Abstract methods are **what**. And must be overridden by subclass.
    *   Default methods are **how**
*   Every method in an interface must be public, so `public` is redundant
*   Can provide variables, but they are `public static final`
    *   `final` means the value can never change

##### Abstract Classes

*   Use `abstract` keyword for abstract methods
*   Use no keyword for concrete methods
*   Can't be instantiate
*   Provide implementation inheritance for features other than public methods, including instance variables

#### Packages

*   A package is a `namespace` that organizes classes and interfaces.

    ```java
    ug.hoshh.animal.Dog d = new ug.hoshh.animal.Dog();
    ```

*   Can use `import` to provide shorthand notation for usage of a single class

    ```java
    import ug.hoshh.animal.Dog
    Dog d = new Dog();
    ```

*   Wildcard import: also possible to import multiple classes, but this is often a bad idea.

    *   Cause conflicts

### Generics and Autoboxing

#### Autoboxing and Unboxing

##### Reference Types

*   Java has 8 primitive types. All other types are reference types
*   For each primitive type, there is a corresponding reference type called a wrapper class.

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220119200901460.png" alt="image-20220119200901460" style="zoom:30%;" />

##### Auto-boxing/auto-unboxing

*   Implicit conversion between wrapper/primitives

    ```java
    public class BasicArrayList {
        public static void main(String[] args) {
          ArrayList<Integer> L = new ArrayList<Integer>();
          L.add(new Integer(5));
          L.add(new Integer(6));
    
          /* Use the Integer.valueOf method to convert to int */
          int first = L.get(0).valueOf();
        }
    }
    ```

    ```java
    public class BasicArrayList {
        public static void main(String[] args) {
          ArrayList<Integer> L = new ArrayList<Integer>();
          L.add(5);
          L.add(6);
          int first = L.get(0);
        }
    }
    ```

##### Primitive Widening

```java
public static void blahDouble(double x) {
    System.out.println(“double: “ + x);
}

int x = 20;
blahDouble(x);
```

```java
public static void blahInt(int x) {
    System.out.println(“int: “ + x);
}

double x = 20;
blahInt((int) x);
```

#### Immutability

*   Mutable: ArrayDeque, Planet.
*   Immutable: Integer, String, Date

```java
public class Date {
    public final int month;
    public final int day;
    public final int year;
    private boolean contrived = true;
    public Date(int m, int d, int y) {
        month = m; day = d; year = y;
    }
}
```

`String` objects are immutable: you can call any method on that `String`, but it will remain completely unchanged. 

e.g. when `String` objects are concatenated, neither of the original Strings are modified, instead, a totally new `String` object is returned.

#### ArrayMap Implementation

```java
 put(key, value): Associate key with value.
 containsKey(key): Checks if map contains the key.
 get(key): Returns value, assuming key exists.
 keys(): Returns a list of all keys.
 size(): Returns number of keys.
```

```java
import java.util.*;

public class ArrayMap<K, V> {

    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /**
     * Returns the index of the key, if it exists. Otherwise returns -1.
     **/
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            // not keys.length (instead: real size)
            if (keys[i].equals(key)) {
                // not keys[i] == key: "==" means points to the same thing
                return i;
            }
        }
        return -1;
    }

    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size += 1;
        } else {
            values[index] = value;
        }
    }

    public V get(K key) {
        int index = keyIndex(key);
        return values[index];
    }

    public int size() {
        return size;
    }

    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
    }
}
```

#### Automatic Conversion Puzzle

```java
@Test
public void test() { 
    ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
    am.put(2, 5);
    int expected = 5;
    assertEquals(expected, am.get(2));//compile error!
}
```

`compile-time error`: reference to assertEquals is ambiguous

#### Generic Methods

##### get()

```java
MapHelper.java
  
public class MapHelepr {
  
  public static <K,V> V get(Map61B<K,V> map, K key) {
    if map.containsKey(key) {
        return map.get(key);
    }
    return null;
	}
}
```

Don't need to `public class MapHelper <K,V>`

Since nobody will instantiate a MapHelper, it doesn't work

To call a generic method:

```
ArrayMap<Integer, String> isMap = new ArrayMap<Integer, String>();
System.out.println(mapHelper.get(isMap, 5));
```

##### maxKey()

```java
MapHelper.java

public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {
    List<K> keylist = map.keys();
    K largest = map.get(0);
    for (K k: keylist) {
        if (k.compareTo(largest)) {//k > largest
            largest = k;
        }
    }
    return largest;
}
```

### Exceptions, Iterators, Iterables

##### Throw Exception

```java
public V get(K key) {
  int index = keyIndex(key);
  if(index == -1) {
    throw new IllegealArgumentException("THe key provided" + key + "was not in ArrayMap.");
  }
  return values[index];
}
```

##### Catch Exception

```java
Dog d = new Dog("Lucy", "Retriever", 80);
d.becomeAngry();

try{
  d.reveivePat();
} catch(Exception e) {
  System.out.println("Tried to pat: " + e);
}
```

May include corrective action

```java
Dog d = new Dog("Lucy", "Retriever", 80);
d.becomeAngry();

try{
  d.reveivePat();
} catch(Exception e) {
  System.out.println("Tried to pat: " + e);
  d.earTreat("banana");
}
d.reveivePat();
```

##### The Philosophy of Exceptions

Why Exceptions?

*   Allows you keep error handling code separate from 'real' code

```java
readFile
  
try{
  open the file;
  determine its size;
  allocate that much memory;
  read the file into memory;
  close the file;
} catch (fileOpenFailed) {
  doSomethings;
} catch (sizeDeterminationFailed) {
  doSomething;
} ...
```

##### Exceptions and the Call Stack

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220122133222058.png" alt="image-20220122133222058" style="zoom:50%;" />

*   If exceptions reaches the bottom of stack, the program crashes and Java provides a message for the user

##### Checked and Unchecked Exceptions

Maybe you will find that code won't compile with `must be caught or declared to be thrown`

*   Some exceptions are considered so disgusting by the compiler that you MUST handle them.
*   We call theses 'checked' exceptions
*   Any subclass of `RuntimeException` or `Error` is unchecked

![image-20220122134053471](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220122134053471.png)

Two ways to satisfy the compiler:

*   `catch`

    ```java
    public static void gulgate() throws IOException {
      ...
      throw new IOException("hi");
      ...
    }
    
    public static void main(String[] args) {
      try {
        gulgate();
      } catch(IOException e) {
        ...
      }
    }
    ```

*   Specify method as dangers with `throws` keyword

    *   Defers to someone else to handle exception

    ```java
    public static void gulgate() throws IOException {
      ...
      throw new IOException("hi");
      ...
    }
    
    public static void main(String[] args) throws IOExpcetion {
    	gulgate();
    }
    ```

#### Iteration

```java
List<Integer> friends = new ArrayList<Integer>();
...
for (int x : friends) {
  ...
}
```

```java
List<Integer> friends = new ArrayList<Integer>();

Iterator<Integer> seer = friends.iterator();

while(seer.hasNext()) {
  ...
}
```

##### Implementing Iterators

```java
import java.util.*;

public class ArrayMap<K, V> {

    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    public class KeyIterator {
        int wizardPosition;

        KeyIterator() {
            wizardPosition = 0;
        }

        public boolean hasNext() {
            return wizardPosition < size;
        }

        public K next() {
            K keyValue = keys[wizardPosition];
            wizardPosition += 1;
            return keyValue;
        }
    }

    /**
     * Returns the index of the key, if it exists. Otherwise returns -1.
     **/
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            // not keys.length (instead: real size)
            if (keys[i].equals(key)) {
                // not keys[i] == key: "==" means points to the same thing
                return i;
            }
        }
        return -1;
    }

    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size += 1;
        } else {
            values[index] = value;
        }
    }

    public V get(K key) {
        int index = keyIndex(key);
        return values[index];
    }

    public int size() {
        return size;
    }

    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
    }
}
```

But we still can't use it like this

```java
for(String k : am){
  
}
```

Need to add an `iterator` in the class

```java
public class ArrayMap<K, V> implements Iterable<K>
  
  @Override
  public Iterator<K> iterator() {
  return new KeyIterator();
}

public class KeyIterator implements Iterator<K>

```

or

```java
@Override
public Iterator<K> iterator() {
  List<K> keyList = keys();
  return keyList.iterator();
}
```

### Packages and Access Control

#### Packages

##### Creating a Package

*   At the top of every file in the package, put the package name.
*   Make sure that the file is stored in a folder with the appropriate folder name, e.g. for a package with ug.joshh.animal, use folder ug/joshh/animal
    *   In intelliJ, `create package named ug.joshh.animal` ,and it will create the folders

##### Using Packages

To use a class from package A in a class from package B, we use the `canonical name`

```java
ug.joshh.animal.Dog d = new ug.joshh.animal.Dog();
```

```java
import ug.joshh.animal.Dog;
Dog d = new Dog();
```

#### JAR files

actually are .zip files, not safe 

#### Access Control

##### Private

Only code from the given class can access **private** members.

It is truly *private* from everything else, as subclasses, packages, and other external classes

##### Protected

Same package or subclasses can access **protected** members.

But the rest of the world (e.g. classes external to the package or non-subclasses) cannot

##### Package private(default)

This is the default access given to Java members if there is no explicit modifier written. 

**Package private** entails that classes that belong in the same package can access, but not subclasses

![access](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/access_modifiers.png)

Why packages members considered more "secret" than subclass members?

*   Extending classes you didn't write is common.
*   Packages are typically modified only by a specific team of humans

Don't have any access modifiers or package names?

*   Everything is **package-private**
*   Everything is part of the same (unnamed) default package

```java
package universe;

public interface BlackHole {
  void add(Object x);//in an interface, it is public, not package private
}
```

#### Object Methods

*   `String toString()`
*   `Boolean equals(Object obj)`
*   `Class<?> getClass`
*   `int hashCode()`

##### ToString

When we run `System.out,println(dog)`

`String s = dog.toString();`

`System.out.println(s)`

For classes that we've written by ourselves like `ArrayDeque`, `LinkedListDeque`. we need to provide our own `toString()` method if we want to be able to see the objects printed in a readable format.

##### '=='

`==` Checks if two objects are actually the same object in memory

For primitives, this means checking the value.

For objects, this means checking if the address/pointer is equal.

##### equals

`equals(Object o)` is a method in the object that, by default, acts like == in that it checks if the memory address of the this is the same as o.

We can override the equals function to behave whatever we want.

```java
@Override
public boolean equals(Object x) {
  if (this == x) return true;
  if (x == null) return false;
  if (this.getClass() != x.getClass()) return false;
  Date that = (Data) x;
  ...
}
```

## Part II

### Introduction to Asymptotic

#### Duplicate Finding

![image-20220127101357920](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220127101357920.png)

##### Simplification: 

*   Consider only the worst case
*   Restrict attention to one operation
*   Ignore lower order terms
*   Ignore multiplicative constants

##### Simplified Analysis

*   exact count

    ```java
    int N = A.length;
    for (int i = 0; i < N; i += 1)
       for (int j = i + 1; j < N; j += 1)
          if (A[i] == A[j])
             return true;
    return false;
    ```

    Cost model: number of '==' operations

    *   Cost = 1 + 2 + 3 + … + (N-2) + (N-1) = N(N-1)/2
    *   Order of Growth: N^2

    <img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220127103858674.png" alt="image-20220127103858674" style="zoom: 50%;" />

*   Geometric Argument
    *   We can see that the number of equals can be given by the area of a right triangle, which has a side length of N - 1
    *   Therefore, the order of growth of area is N^2

#####  Big-Theta

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220127104740168.png" alt="image-20220127104740168" style="zoom:50%;" />

##### Formal Definition

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220127104912276.png" alt="image-20220127104912276" style="zoom:50%;" />

##### Big-O

Big-Theta can be thought of as something like "equals"

Big-O can be thought of as "less than or equal"

![image-20220127112103923](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220127112103923.png)

##### Formal Definition

![image-20220127112159409](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220127112159409.png)

##### Why Big-O is useful?

Big-Theta requires us to qualify what we are talking about

```java
public boolean dup4(int[] a) {
    int N = a.length;
    for (int i = 0; i < N; i += 1) {
        for (int j = i + 1; j < N; j += 1) {
            if (a[i] == a[j]) {
                return true;
            }
        }
    }
    return false;
}
```

*   The best case runtime is *R*(*N*) ∈ Θ(1)
*   in the worst case, *R(N)* ∈ Θ(N^2)
*   The runtime of above code is O(N^2)

##### Big Omega

Big-Omega can be though of as "greater than or equal"

![image-20220129124829483](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220129124829483.png)

### Runtime Analysis Examples

#### Nested Loops

```java
public static void printParty(int N) {
  for (int i = 1; i <= N; i = i * 2) {
    for (int j = 0; j < i; j += 1){
      System.out.println("hello");
    }
  }
}
```

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220128200954126.png" alt="image-20220128200954126" style="zoom:33%;" />



$$
R(N) = \Theta(1+2+4+8+...+N)=2N-1 = \Theta(N) = N
$$
<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220128202216092.png" alt="image-20220128202216092" style="zoom:30%;" />

#### Tree Recursion

```java
public static int f3(int n) {
  if(n <= 1)
    return 1;
  return f3(n-1) + f3(n-1);
}
```

![tree recursion](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/asymptotics2_tree.png)
$$
C(N) = 1+2+4+8+...+2^{N-1}
\\=2(2^{N-1})-1 = 2^N-1
$$

#### Binary Search

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220128205942929.png" alt="image-20220128205942929" style="zoom:50%;" />

![image-20220128210014215](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220128210014215.png)
$$
C(N) = \lfloor logn \rfloor + 1
$$

#### Merge Sort

##### Array Merging

*   Given two sorted arrays, the merge operation combines them into a single sorted array

![image-20220128212211414](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220128212211414.png)
$$
Merge:\Theta(N)
$$

##### Selection Sort and Merge 

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220128213435427.png" alt="image-20220128213435427" style="zoom:30%;" />

![image-20220128213638583](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220128213638583.png)

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220128214348949.png" alt="image-20220128214348949" style="zoom:40%;" />

*   Every level takes ~N Arbitrary Unit(AU)
    *   Top level take ~N AU
    *   Next level takes ~N/2 + ~N/2 = ~N 
    *   One more level down: ~N/4 + ~N/4 + ~N/4 + ~N/4 = ~N
*   Total runtime is ~N*K, where k is the number of levels
    *   Size go down till to 1
    *   K = log_2(N)

### Amortized Analysis

#### Array Resizing and Amortized Analysis

```java
public void addLast(int x) {
  if (size == items.length) {
    resize(size * RFACTOR);
  }
  items[size] = x;
  size += 1;
}
```

Let RFACTOR be 2. When the array is full, resize doubles its size. Most add operations take Θ(1) time, but some are very expensive, and linear to the current size.

 it turns out that **on average**, the runtime of add is Θ(1)

<img src="https://joshhug.gitbooks.io/hug61b/content/assets/amortized_adds.png" alt="amortized_add_operations" style="zoom: 67%;" />

![image-20220129141332748](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220129141332748.png)

*   Copy: read and write according to the current size

### Disjoint Sets

#### Intro to Disjoint Sets

*   `connect(x,y)`:Connects x and y
*   `isConnected(x,y)`: Return true is x and y are connected

##### Disjoint Sets on Integers

*   Force all times to be integers
*   Declare the number of items in advance

##### Connected Components

Rather than manually writing out every single connecting line, only record the set that each item belongs to.

![image-20220202125724009](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220202125724009.png)

#### Quick Find

##### implementation

*   Use a list of integer

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220202130452419.png" alt="image-20220202130452419" style="zoom:33%;" />

```java
public void connect(int p,int q) { // O(n)
  int pid = id[p];
  int qid = id[q];
  for (int i = 0; i < id.length; i++){
    if (id[i] == pid){
      id[i] = qid;
    }
  }
}
```

```java
public boolean isConnected(int p, int q){// O(1)
  	return (id[p] == id[q]);
	}
}
```

#### Quick Union

*   Change the numbers we store in the array to make `connect` faster
    *   assign each item a parent (instead of an id).
    *   Result in a tree-like shape
*   represent `{0, 1, 2, 4}, {3, 5}, {6}` as

![img](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/9.3.1.png)

*   `connect(5,2)`
    *   findRoot(5) - > 3
    *   findRoot(2) -> 0
    *   `parent[3] = 0`

*   `isConnected(x,y)`

    `return findRoot(x) == findRoot(y)`

```java
public class QuickUnionDS implements DisjointSets {
    private int[] parent;

    public QuickUnionDS(int num) {
        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = -1;
        }
    }

    private int find(int p) {
        while (parent[p] >= 0) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j= find(q);
        parent[i] = j;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
```

#### Weighted Quick Union

Modify quick-union to avoid tall trees

*   Track tree size (**number** of elements) / not height
*   New rule: Always link root of smaller tree to lager tree

##### Weighted quick union performance

*   Consider the worst case where the tree height grow as fast as possible
    *   The smallest tree whose height is 3

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220202135349074.png" alt="image-20220202135349074" style="zoom:50%;" />

*   Worst case tree height is `logN`

*   Why weights instead of heights?
    *   Worst case performance for Height Quick Union is the same! Both are log N
    *   But more complicated with no performance gain

#### Weighted Quick Union with Path Compression

*   Idea: when we do isConnected(15,10), tie all nodes seen to the root



<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220202142324163.png" alt="image-20220202142324163" style="zoom: 33%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220202142352398.png" alt="image-20220202142352398" style="zoom: 33%;" />

### Map and BST

#### Map

```java
Map<String,Integer> m = new TreeMap<>();
String[] text = {"..."}

for(String s : text) {
  int currentCount = m.getOrDefault(s,0);
  m.put(s,currentCount + 1);
}
```

![image-20220202151146841](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220202151146841.png)

*   The topic of this lecture is to implement the TreeSet and TreeMap

#### Binary Search Trees

![image-20220202162150751](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220202162150751.png)

#### BST Definitions

A binary search tree is a rooted binary tree with the BST property.

*   Every key in the left subtree is less than X's key.
*   Every key in the right subtree is larger than X's key.

#### BST Search

If searchKey equals. T.key, return

*   If searchKey < T.key, search T.left
*   If searchKey > T.key, search T.right

```java
static BST find(BST T, Key sk) {
   if (T == null)
      return null;
   if (sk.equals(T.key))
      return T;
   else if (sk ≺ T.key)
      return find(T.left, sk);
   else
      return find(T.right, sk);
}
```

##### Runtime

O(log N) if it is balanced

O(N)

#### BST Insert

Search for key

*   If found, do nothing
*   If not found
    *   Create new node.
    *   Set appropriate link

**Understand it in the lab**

```java
static BST insert(BST T, Key ik) {
  if (T == null)
    return new BST(ik);
  if (ik ≺ T.key)
    T.left = insert(T.left, ik);
  else if (ik ≻ T.key)
    T.right = insert(T.right, ik);
  return T;
}
```

#### BST Deletion

*   Delete a node with no child
    *   Delete its parent pointer
*   Delete a node with one child
    *   Reassign the parent's child pointer to the node's child
*   Delete a node with two children
    *   Find a new root node
    *   Choose either the **largest in the left** of the node or the **smallest in the right** of the node
    *   IMPORTANT: the  deletion guaranteed to be either case 1 or case 2

### B-Tree

#### Tree Height

**Worst case:** Θ(*N*)

**Best-case:** Θ(log*N*) (where N*N* is number of nodes in the tree)

#### BST Performance

*   The **depth of a node** is how fat it is from the root, e.g. depth(g) = 2
*   The **height of a tree** is the depth of its deepest leaf
*   The **average depth** is the average of the total depths in the tree

![image-20220203125107773](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203125107773.png)

*   The **height of a tree** determines the worst case runtime
    *   Requires 5 comparisons to find **'s'** (height + 1)
*   The **average depth** determines the average case runtime
    *   Average case is 3.35 comparisons (average depth + 1)

#### B-Tree

avoid new leaves by **overstuffing** the leaf nodes

*   It has balanced height, because leaf depths never change

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203173420869.png" alt="image-20220203173420869" style="zoom:50%;" />

##### Revise the Overstuffed Tree

*   Set a limit L on the number of items, say L = 3
*   If any node has more than L items, give an item to parent.
    *   Choose the left-middle one
    *   And splits the node into left and right

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203173739908.png" alt="image-20220203173739908" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203173944772.png" alt="image-20220203173944772" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203174315574.png" alt="image-20220203174315574" style="zoom:50%;" />

##### If the root is too full

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203175556267.png" alt="image-20220203175556267" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203175619945.png" alt="image-20220203175619945" style="zoom:50%;" />

##### Runtime Analysis

L: Max number of items per node.
$$
Height: Between ~~log_{L+1}(N)~~and~~log_{L}(N)
$$

*   Largest possible height is all non-leaf nodes have 1 item
*   Smallest possible height is all nodes have L items

![](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203220429175.png)

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203220443369.png" alt="image-20220203220443369" style="zoom:50%;" />

##### Runtime for contains

![image-20220203220631885](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220203220631885.png)

*   Worst case number of nodes to inspect: H + 1
*   Worst case number of items to inspect per node: L
*   Overall runtime: O(HL)
    *   Since the H = \Theta log N and L is a constant
    *   Overall runtime is O(log N)

#### Red Black Tree

##### Rotation

rotateLeft: Let x be the right child of G, make G the new left child of x

*   Can think of as temporarily merging G and P, then sending G down and left.

![image-20220204112242125](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204112242125.png)

##### AVL Trees

$$
Balance(n)=H(T_L) - H(T_R)
\\AVL_{Tree} = |Balance(n)|\leq1~or~a~threshold
$$

*   Left heavy: H(TL) is larger
    *   Right rotation
    *   Left-right rotation
*   Right heavy: H(TR) is larger
    *   Left rotation
    *   Right-left rotation

![image-20220204122526309](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204122526309.png)

![image-20220204123153941](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204123153941.png)

```java
private Node rotateRight(Node h) {
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    return x;
}

private Node rotateLeft(Node h) {
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    return x;
}
```

##### Red Black Tree Definition

*   Build a BST that is structurally identical to a 2-3 tree.
    *   Since a 2-3 tree are balanced, so will the special BST

![image-20220204135050452](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204135050452.png)

![image-20220204163508997](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204163508997.png)

glue link as **red**

A BST with left glue links that represents a 2-3 tree if often called a "Left Leaning Red Black Binary Search Tree" or LLRB

*   LLRB are normal BSTs
*   There is a 1-1 correspondence between an LLRB and an equivalent  2-3 tree
*   Red links don't do anything special

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204164147632.png" alt="image-20220204164147632" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204164157542.png" alt="image-20220204164157542" style="zoom:50%;" />

##### Red Black Tree Properties

*   No node has two red links
*   Every path from root to a leaf has same number of black links

##### Red Black Tree Insertion

*   Task 1: Insertion on the Right
*   LLRB

![image-20220204172326825](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204172326825.png)

*   2-3 tree

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204172400396.png" alt="image-20220204172400396" style="zoom:50%;" />

*   New rule: Representation of Temporary 4-Nodes

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204172813123.png" alt="image-20220204172813123" style="zoom:50%;" />

*   Task 2: double insertion on the left

![image-20220204172958685](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204172958685.png)

*   Task 3: splitting temporary 4-nodes
    *   Flip the colors of all edges touching B

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220204173531602.png" alt="image-20220204173531602" style="zoom:50%;" />

*   Summary
    *   When inserting: use a red link
    *   If there is a right leaning "3-node"
        *   Rotate left the appropriate node to fix
    *   If there are two consecutive left links
        *   Rotate right the appropriate node to fix
    *   If there are any nodes with two red children
        *   Color flip the node to emulate the spilt operation

##### LLRB Runtime and Implementation

*   LLRB tree has height O(log N)
*   Contains is trivially O(log N)
*   Insert is O(log N)
    *   O(log N) to add the new node
    *   O(log N) rotation and color flip

```java
private Node put(Node h, Key key, Value val) {
    if (h == null) { return new Node(key, val, RED); }

    int cmp = key.compareTo(h.key);
    if (cmp < 0)      { h.left  = put(h.left,  key, val); }
    else if (cmp > 0) { h.right = put(h.right, key, val); }
    else              { h.val   = val;                    }

    if (isRed(h.right) && !isRed(h.left))      { h = rotateLeft(h);  }
    if (isRed(h.left)  &&  isRed(h.left.left)) { h = rotateRight(h); }
    if (isRed(h.left)  &&  isRed(h.right))     { flipColors(h);      } 

    return h;
}
```

#### Hashing

Our search tress based sets require items to be comparable.

*   Need to be able to ask "is X < Y" Not true for all types
*   Could we somehow avoid this comparison?

Our search tress based sets have excellent performance, but could maybe be better?

*   Better than log N?

##### Data Indexed Integer Set

-   The `add(int x)` method simply sets the `x` position in our ArrayList to true. This takes Θ(1) time.
-   The `contains(int x)` method simply returns whether the `x` position in our ArrayList is `true` or `false`. This also takes Θ(1) time!

```java
public class DataIndexedIntegerSet {
    private boolean[] present;

    public DataIndexedIntegerSet() {
        present = new boolean[2000000000];
    }

    public void add(int x) {
        present[i] = true;
    }

    public boolean contains(int x) {
        return present[i];
    }
```

##### Hash Codes

Since java has a maximum integer, we won't get the numbers we expect

*   Hash code: projects a value from a set with many members to a value from a set with a fixed numbers of members
*   Our target set os the set of Java integers, which is of size 4294967296

##### Separate Chains and Hash Tables

-   Add item
    -   Get hashCode (i.e., index) of item.
    -   If index has no item, create new List, and place item there.
    -   If index has a List already, check the List to see if item is already in there. If *not*, add item to List.
-   Contains item
    -   Get hashCode (i.e., index) of item.
    -   If index is empty, return `false`.
    -   Otherwise, check all items in the List at that index, and if the item exists, return `true`.

![image-20220205124821595](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220205124821595.png)

##### Hash Table Performance and Resizing

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220205125122271.png" alt="image-20220205125122271" style="zoom:50%;" />

Suppose we have:

*   A fixed number of buckets M.
*   An increasing number of items N.
    *   Even the items are spread out evenly, lists are of length Q = N/M
        *   To improve the linear time complexity to a constant one, using an **increasing number of buckets**

*   Resizing takes Θ(N) time. Have to redistribute all items.

##### Hash Tables in Java

`int hashCode()`:default implementation simply returns the memory address of the object.

`Math.floorMod(hashCOde)`

*   Warning #1: never store objects that can change in a HashSet or HashMap!
    *   If an object's variables changes, then its hashCode changes.
*   Warning #2: never override equals without also overriding hashCode
    *   HashMaps and HashSets use equals to determine if an item exist in a bucket.

##### Good Hash Functions

*   Goal: achieve a hashTable like the right one

![image-20220205165108330](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220205165108330.png)

*   A typical hash code base is a **small prime**
    *   Never even: avoid the overflow issue(126^33=126^34=...=0)
    *   Lower chance of resulting hashCode have a bad relationship with the number of buckets(Lab9)
    *   Lower cost to compute

#### Priority Queue

```java
public interface MinPQ<Item> {
  public void add(Item x);
  
  public Item getSmallest();
  
  public Item removeSmallest();
  
  public int size();
}
```

##### Implementation

*   BST would work, but need to be kept bushy and avoid duplicate
*   Binary min-heap:
    *   Binary tree that is complete and obeys min-heap property
        *   Min-heap: every node is less than or equal to both of its children.
        *   Complete: Missing items only at the bottom level, all nodes are as far left as possible

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208125602633.png" alt="image-20220208125602633" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208125621888.png" alt="image-20220208125621888" style="zoom:50%;" />

##### Heap Operations

`getSmalllest()`

Return the item in the root node

`add()`

*   Add to the end of heap temporarily
*   Swim up the hierarchy to the rightful place

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208131013767.png" alt="image-20220208131013767" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208131029717.png" alt="image-20220208131029717" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208131045040.png" alt="image-20220208131045040" style="zoom:50%;" />

`removeSmallest()`

*   Swap the last item in the heap into the root
*   Sink the way down to the right place

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208132758338.png" alt="image-20220208132758338" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208132819881.png" alt="image-20220208132819881" style="zoom:50%;" />

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208132834731.png" alt="image-20220208132834731" style="zoom:50%;" />

##### Tree Representations

```java
public class Tree1A<Key> {
  Key k;
  Tree1A left;
  Tree1A middle;
  Tree1A right;
  ...
}
```

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208135306812.png" alt="image-20220208135306812" style="zoom:50%;" />

```java
public class Tree1B<Key> {
  Key k;
  Tree1B[] children;
}
```

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208135353642.png" alt="image-20220208135353642" style="zoom:50%;" />

```java
public class Tree1C<Key> {
  Key k;
  Tree1C child;
  Tree1C sibling;
}
```



<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208135420235.png" alt="image-20220208135420235" style="zoom:50%;" />

```java
public class Tree2<Key> {
  Key[] keys;
  int[] parents;
}
```

![image-20220208165917496](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208165917496.png)

```java
public class Tree3<Key> {// for complete tree
  Key[] keys;
}
```

![image-20220208165957740](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208165957740.png)

```java
public void swim(int k) {
  if (keys[parent(k)] > keys[k]]) {
    swap(k, parent(k));
    swim(parent(k));
  }
}

public int parent(int k) {
  return (k - 1) / 2;
}
```

##### PQ Implementation

![image-20220208170915626](/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220208170915626.png)

*   leftChild(k) = k * 2
*   rightChild(k) = k * 2 + 1
*   Parent(k) = k / 2

| Methods          | Ordered Array | Bushy BST | Hash Table | Heap      |
| ---------------- | ------------- | --------- | ---------- | --------- |
| `add`            | Θ(*N*)        | Θ(log*N*) | Θ(1)       | Θ(log*N*) |
| `getSmallest`    | Θ(1)          | Θ(log*N*) | Θ(*N*)     | Θ(1)      |
| `removeSmallest` | Θ(*N*)        | Θ(log*N*) | Θ(*N*)     | Θ(log*N*) |

##### Data Structures Summary

##### The Search Problem 

| Name          | Store Operation(s)               | Primary Retrieval Operation | Retrieve By              |
| ------------- | -------------------------------- | --------------------------- | ------------------------ |
| List          | `add(key)`, `insert(key, index)` | `get(index)`                | index                    |
| Map           | `put(key, value)`                | `get(key)`                  | key identity             |
| Set           | `add(key)`                       | `containsKey(key)`          | key identity             |
| PQ            | `add(key)`                       | `getSmallest()`             | key order (aka key size) |
| Disjoint Sets | `connect(int1, int2)`            | `isConnected(int1, int2)`   | two integer values       |



`Set / Map`: BST, 2-3 Tree, LLRBs, Separate Chaining Hash Tables

`Stack / List `:Linked List, Resizing Arrays 

`Priority Queue`: Ordered Array, LLRB, Hash Table, Heap

`DisjointSets`: Quick find, Quick Union, Weighted QU, Weighted QU with path compression

#### Graphs

##### Tree Traversals

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220209132114754.png" alt="image-20220209132114754" style="zoom:50%;" />

*   Preorder: Visit every time we pass the **left** of the node
*   Inorder: Visit every time we cross the **bottom** of the node
*   Postorder: Visit every time we pass the **right** of the node

##### Graph Definition

*   A set of nodes
*   A set of zero or more edges, each of which connects two nodes

*   No edges that connect a vertex to itself, no loops
*   No two edges that connect the same vertices, no parallel edges

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220209185135117.png" alt="image-20220209185135117" style="zoom:50%;" />

*   Graph:
    *   Set of **vertices**, a.k.a. **nodes**
    *   Set of edges
    *   Vertices with an edge between are **adjacent**
*   **Path** is a sequence of vertices connected by edges
*   **Cycle** is a path whose first and last vertices are the same

##### Graph Problems

-   **s-t Path**: Is there a path between vertices s and t?
-   **Connectivity**: Is the graph connected, i.e. is there a path between all vertices?
-   **Biconnectivity**: Is there a vertex whose removal disconnects the graph?
-   **Shortest s-t Path**: What is the shortest path between vertices s and t?
-   **Cycle Detection**: Does the graph contain any cycles?
-   **Euler Tour**: Is there a cycle that uses every edge exactly once?
-   **Hamilton Tour**: Is there a cycle that uses every vertex exactly once?
-   **Planarity**: Can you draw the graph on paper with no crossing edges?
-   **Isomorphism**: Are two graphs isomorphic (the same graph in disguise)?

##### Depth First Traversal

*   s-t Path:

```java
mark s  // i.e., remember that you visited s already
if (s == t):
    return true;

for child in unmarked_neighbors(s): // if a neighbor is marked, ignore!
    if isconnected(child, t):
        return true;

return false;
```

##### DFS Preorder

**Action** is **before DFS** calls to neighbors

Our action is setting `edgeTo`

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220210113835540.png" alt="image-20220210113835540" style="zoom:50%;" />

##### DFS Postorder

**Action** is **after DFS** calls to neighbors

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220210114237392.png" alt="image-20220210114237392" style="zoom:50%;" />

##### BreadthFirstSearch

Goal: Find shortest path between s and every other vertex

*   **Initialize the fringe** (a queue with a starting vertex s) and mark the vertex
*   Repeat until fringe is empty:
    *   Remove vertex x from the fringe (dequeue)
    *   For each unmarked neighbor n of v:
        *   mark n, add n to fringe (enqueue)
        *   Set edgeTo[n] = v, set disTo[n] = disTo[v] + 

##### Graph Representation

*   Representation 1: Adjacency Matrix
    *   G.adj (2) would return an iterator where we can call next() up to two times
        *   next() return 1
        *   Next() return 3
    *   Total runtime to iterate over all neighbors of v is Θ(V)

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220210190618506.png" alt="image-20220210190618506" style="zoom:50%;" />

*   Representation 2: Adjacency lists

    *   Maintain array of lists indexed by vertex number 
    *   Most popular

    <img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220210191307008.png" alt="image-20220210191307008" style="zoom:50%;" />

Runtime Analysis : Θ(V + E), where v is the number of vertices and E is the total number of edges

```java
for (int v = 0; v < G.V(); v += 1) {
  for (int w :G.adj(v)) {
    System.out.printlb(v + "-" + w);
  }
}
```

##### Depth First Search Implementation

*   Create a graph object
*   Pass the graph to a graph-processing methods in a client class
*   Query the client class for information

```java
public class Paths {
  public Paths(Graph G, int s);
  boolean hasPathTo(int v);
  Iterable<Integer> pathTo(int v);
}
```

<img src="/Users/winter/Desktop/Introduction to Algorithms/CS61B/Learning_CS61B.assets/image-20220210193740597.png" alt="image-20220210193740597" style="zoom:50%;" />

O (V + E) for the DFS

*   Each vertex is visited at most once O(V) `dfs()`
*   Each edge is considered at most twice O(E) `marked[w]`
