## Berkeley CS61B Data Structure
### 1. Introduction to Java
#### 1.1 Essential

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

### 1.2 Objects

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



### Week2

 