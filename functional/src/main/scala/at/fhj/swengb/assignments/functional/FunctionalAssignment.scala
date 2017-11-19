package at.fhj.swengb.assignments.functional

/**
  * In this assignment you have the chance to demonstrate basic understanding of
  * functions like map/filter/foldleft a.s.o.
  **/
object FunctionalAssignment {

  /**
    * A function which returns its parameters in a changed order. Look at the type signature.
    */
  def flip[A, B](t: (A, B)): (B, A) = { (t._2, t._1)}

  /**
    * given a Seq[A] and a function f : A => B, return a Seq[B]
    */
  def unknown[A, B](as: Seq[A], fn: A => B): Seq[B] = {

    for {
      x <- as
    } yield fn(x)
  }

  /**
    * Returns the absolute value of the parameter i.
    *
    * @param i a value, either with a positive or a negative sign.
    * @return
    */
  def abs(i: Int): Int = if (i < 0) i * -1 else i


  // Describe with your own words what this function does.
  // in the comment below, add a description what this function does - in your own words - and give
  // the parameters more descriptive names.
  //
  // Afterwards, compare your new naming scheme with the original one.
  // What did you gain with your new names? What did you loose?
  //
  /**
    * Die Funktion op nimmt eine Sequenz (as) und wendet darauf eine Fold-Left Funktion an. Mithilfe dieser wird jeder Wert
    * aus der Sequenz herangenommen und mit dem Akkumulator (Parameter b) gemäß der Funktion fn kombiniert. Z.B. man hat
    * den Wert 1 aus einer Liste, der Akkumulator ist Null und die Funktion ist (_+_) -> dann wird 1 und 0 addiert. Das ergibt
    * 1 und dieser Wert ist der neue Akkumulator, welcher dann wiederum zum nächsten Wert in der Liste addiert wird, bis die Liste
    * leer ist. A und B bedeutet, dass die verschiedenen Parameter unterschiedliche Typen haben können (aber nicht müssen) ->
    * müssten sie den gleichen Typ haben, würden alle Parameter z.B. A haben.
    *
    * @param as
    * @param b
    * @param fn
    * @tparam A 
    * @tparam B
    * @return
    *
    * neue Parameternamen für diese Funktion (op)
    * @param list
    * @param acc
    * @param fn
    * @tparam A
    * @tparam B
    * @return
    *
    *  im Fall der Funktion op würde ich den Parameter für die Sequenz in list umtaufen, da so besser hervorgeht, dass es sich um eine
    *  Liste handelt. Den Akkumulator würde ich mit acc bezeichnet, da somit klarer ist, dass es sich um einen Akkumulator handelt, als
    *  wenn nur der Buchstabe b dort steht. fn würde ich nicht verändern, eventuell zu func erweitern, damit klarer hervorgeht, dass
    *  es sich um eine Funktion handelt. A und B zu ändern ist meiner Meinung nach nicht notwendig, es ist klar ersichtlich, dass es sich
    *  um unterschiedliche Typen handelt.
    */
  def op[A, B](as: Seq[A], b: B)(fn: (B, A) => B): B = as.foldLeft(b)(fn)

  /**
    * implement the summation of the given numbers parameter.
    * Use the function 'op' defined above.
    *
    * @param numbers
    * @return
    */
  def sum(numbers: Seq[Int]): Int = {

    return(op(numbers,0)(_+_))
  }


  /**
    * calculate the factorial number of the given parameter.
    *
    * for example, if we pass '5' as parameter, the function should do the following:
    *
    * 5! = 5 * 4 * 3 * 2 * 1
    *
    * @param i parameter for which the factorial must be calculated
    * @return i!
    */
  def fact(i: Int): Int = {

    var n: Int = 1
    var m: Int = 1

    while (n < i) {

      n = n + 1

      m = n * m
    }

    return (m)
  }

  /**
    * compute the n'th fibonacci number
    *
    * hint: use a internal loop function which should be written in a way that it is tail
    * recursive.
    *
    * https://en.wikipedia.org/wiki/Fibonacci_number
    */
  def fib(n: Int): Int = n match {

    case 0 => n
    case 1 => n
    case _ => fib(n-1) + fib(n-2)

  }

  /**
    * Implement a isSorted which checks whether an Array[A] is sorted according to a
    * given comparison function.
    *
    * Implementation hint: you always have to compare two consecutive elements of the array.
    * Elements which are equal are considered to be ordered.
    */
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {

    var v: Boolean = true

    for (i <- 0 until as.length-1){
      v = v && gt(as(i),as(i+1))
    }
    return(v)
  }



  /**
    * Takes both lists and combines them, element per element.
    *
    * If one sequence is shorter than the other one, the function stops at the last element
    * of the shorter sequence.
    */
  def genPairs[A, B](as: Seq[A], bs: Seq[B]): Seq[(A, B)] = {
    for {

      x <- as
      y <- bs if as.indexOf(x) == bs.indexOf(y)
    } yield (x,y)

  }

  // a simple definition of a linked list, we define our own list data structure
  sealed trait MyList[+A]

  case object MyNil extends MyList[Nothing]

  case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

  // the companion object contains handy methods for our data structure.
  // it also provides a convenience constructor in order to instantiate a MyList without hassle
  object MyList {

    def sum(list: MyList[Int]): Int = list match {

        case MyNil => 0
        case Cons(head,tail) => head + sum(tail)

    }

    def product(list: MyList[Int]): Int = list match {

        case MyNil => 1
        case Cons(head,tail) => head * product(tail)
    }

    def apply[A](as: A*): MyList[A] = {
      if (as.isEmpty) MyNil
      else Cons(as.head, apply(as.tail: _*))
    }

  }

}

