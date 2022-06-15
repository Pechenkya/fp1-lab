package petro.b.lab1

import scala.annotation.tailrec

enum Set[+A]:
  case Empty
  case NonEmpty private[Set] (a: A, rest: Set[A])


object Set:
  def makeSet[A](xs: A*): Set[A] = ???
    
  def add[A](st: Set[A], a: A): Set[A] = 
    if contains(st, a) then 
      st
    else
      NonEmpty(a, st)


  def intersect[A](left: Set[A], right: Set[A]): Set[A] = 
    @tailrec
    def rec[A](l: Set[A], r: Set[A],
      result: Set[A]): Set[A] = left match
        case Empty => result
        case NonEmpty(x, tail) =>
          if contains(r, x) then
            rec(tail, r, NonEmpty(x, result))
          else
            rec(tail, r, result)
    
    rec(left, right, Empty)


  @tailrec
  def union[A](left: Set[A], right: Set[A]): Set[A] = 
    left match
      case Empty => right
      case NonEmpty(b, tail) => union(tail, add(right, b))


  @tailrec
  def contains[A](s: Set[A], a: A): Boolean = 
    s match
      case Empty => false
      case NonEmpty(b, set) => 
        if (a == b) then 
          true 
        else 
          contains(set, a)


  def map[A, B](base: Set[A], f: A => B): Set[B] =
    @tailrec
    def rec[A, B](bs: Set[A], 
      f: A => B, result: Set[B]): Set[B] = 
        bs match
          case Empty => result
          case NonEmpty(x, tail) => rec(tail, f, add(result, x))

    rec(base, f, Empty)


@main def hello: Unit = 
  print("Still compiling :)")