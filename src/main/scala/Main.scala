package petro.b.lab1

import scala.annotation.tailrec

enum Set[+A]:
  case Empty
  case NonEmpty private[Set] (a: A, rest: Set[A])

  def contains[A](a: A): Boolean = 
    @tailrec
    def cont_rec[A](set: Set[A], x: A): Boolean = 
      set match
        case Empty => false
        case NonEmpty(b, tail) => 
          if (a == b) then 
            true 
          else 
            cont_rec(tail, a)

    cont_rec(this, a)


  override def toString(): String = 
    @tailrec
    def build_out(set: Set[A], result: StringBuilder): String =
      set match
        case Empty => 
          (result += '}')
        case NonEmpty(x, tail) =>
          build_out(
            tail, 
            (result ++= x) ++= (if tail != Empty then ", " else ' ')
          )
    
    build_out(this, new StringBuilder("{ "))

object Set:
  def makeSet[A](xs: A*): Set[A] = ???
    
  def add[A](st: Set[A], a: A): Set[A] = 
    if st.contains(a) then 
      st
    else
      NonEmpty(a, st)


  def intersect[A](left: Set[A], right: Set[A]): Set[A] = 
    @tailrec
    def rec[A](l: Set[A], r: Set[A],
      result: Set[A]): Set[A] = l match
        case Empty => result
        case NonEmpty(x, tail) =>
          if r.contains(x) then
            rec(tail, r, NonEmpty(x, result))
          else
            rec(tail, r, result)
    
    rec(left, right, Empty)


  @tailrec
  def union[A](left: Set[A], right: Set[A]): Set[A] = 
    left match
      case Empty => right
      case NonEmpty(b, tail) => union(tail, add(right, b))


  def map[A, B](base: Set[A], f: A => B): Set[B] =
    @tailrec
    def rec[A, B](bs: Set[A], 
      f: A => B, result: Set[B]): Set[B] = 
        bs match
          case Empty => result
          case NonEmpty(x, tail) => rec(tail, f, add(result, f(x)))

    rec(base, f, Empty)


@main def hello: Unit = 
  println("Still compiling :)")
