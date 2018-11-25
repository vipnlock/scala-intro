/**
  * Examples based on:
  * https://tproger.ru/articles/scala-tutorial-for-beginners/
  */
object Introduction {
  // public int i = 0;
  val i: Int = 0

  def main(args: Array[String]): Unit = {
    testImmutable()
    testTypes()
    testFunction()
    testClass()
    testMyUselessClass()
    testTraits()
    println(testIfElse(1))
    println(testIfElse(5))
    testTryCatch(10)
    testTryCatch(0)
    testFor()
  }

  def testImmutable(): Unit = {
    println("=== Test Immutable")
    // public final String x = "Some immutable string";
    val x = "Some immutable string"
    var y = "Can be changed"

    // x = "Oops" - x is final
    y = "Was changed"
    println(x + ";".+(y))
  }

  def testTypes(): Unit = {
    println("=== Test Types")
    // public boolean flag = true;
    val flag: Boolean = true
    println("" + i + flag)
  }

  def testFunction() = {
    println("=== Test Function")
    val functionValue = addOne _
    println(functionValue.apply(1))
    println(functionValue(2))

    val func = (x: Int) => x + 1
    println(func(3))
  }

  def testClass(): Unit = {
    println("=== Test Class")
    val foo = new Foo(1)
    println(foo)
    println(foo.bar(2))
    println(Singleton.squareMe(4))
  }

  def testMyUselessClass(): Unit = {
    println("=== Test MyUselessClass")
    val myUselessObject = MyUselessClass(1)
    val myUselessObject2 = MyUselessClass.apply(2)
    val myUselessObject3 = MyUselessClass(1, 2, 3)

    println(myUselessObject.instanceMethod())
    println(myUselessObject.mutableField)
    println(myUselessObject.immutableField)
    myUselessObject.mutableField = 9
    println(myUselessObject.mutableField)
    println(MyUselessClass.staticMethod(3))
  }

  def testTraits(): Unit = {
    println("=== Test Traits")

    val obj = new ComplexClass
    println(obj.foo(2))
    println(obj.bar(2))
  }

  def testIfElse(x: Int = 1): Int = {
    println("=== Test if-else")
    if (x == 1) {
      100
    } else {
      x * 1000
    }
  }

  def testTryCatch(x: Int): Int = {
    println("=== Test try-catch")
    try {
      10 / x
    } catch {
      case e: Exception => println("Exception " + e); 0
    } finally {
      println("Finally block")
    }
  }

  def testFor(): Unit = {
    println("=== Test for")
    val numbers = 1 to 10
    var sum = 0
    for (n <- numbers) {
      sum += n
    }
    println(sum)
  }

  def addOne(i: Int): Int = i + 1

}

class Foo(x: Int) {
  def bar(y: Int): Int = x + y
}

object Singleton {
  def squareMe(x: Int): Int = x * x
}

object MyUselessClass {
  def staticMethod(x: Int): Int = x + 5

  def apply(immutableField: Int): MyUselessClass = {new MyUselessClass(immutableField, 2)}
  def apply(immutableField: Int, mutableField: Int): MyUselessClass = {new MyUselessClass(immutableField, 2)}
  def apply(immutableField: Int, mutableField: Int, privateField: Int): MyUselessClass = {new MyUselessClass(immutableField, 2)}
}

class MyUselessClass(val immutableField: Int, var mutableField: Int, privateField: Int = 8) {
  def instanceMethod(): Int = {
    val sum = immutableField + mutableField + privateField
    MyUselessClass.staticMethod(sum)
  }
}

trait FirstTrait {
  def foo(x: Int): Int
}

trait SecondTrait {
  def bar(y: Int): Int = y + 5
}

class ComplexClass extends FirstTrait with SecondTrait {
  override def foo(x: Int): Int = x * 2
}