import munit.ScalaCheckSuite
import org.scalacheck._

import org.scalacheck.Prop._
import org.scalacheck.Arbitrary.arbitrary

import petro.b.lab1._
import scala.util.Random

class SetSuite extends ScalaCheckSuite {

  given [A: Arbitrary]: Arbitrary[Set[A]] =
    Arbitrary(Gen.listOf(arbitrary[A]).map(Set(_*)))

  property("set constructed with sequence contains it's random element, if Seq is empty, Set == Empty") {
    forAll{
      (xs: Seq[Int]) =>
        if xs.length > 0 then
          Set(xs*).contains(Random.shuffle(xs).head)
        else
          Set(xs*) == Set.Empty
    }
  }


  property("size of set constructed with sequence is equal to number of unique elements in sequence") {
    forAll{
      (xs: Seq[Int]) =>
        Set(xs*).size() == xs.distinct.length
    }
  }


  property("after adding element that already in set, it's size shouldn't increase") {
    forAll{
      (set: Set[Int], x: Int) =>
        if(set.contains(x))
          set.size() == Set.add(set, x).size()
        else
          set.size() == Set.add(set, x).size() - 1
    }
  }


  property("set should contain object that was added") {
    forAll {
      (set: Set[Int], x: Int) =>
        Set.add(set, x).contains(x)
    }
  }


  property("set is it's own subset"){
    forAll {
      (set: Set[Int]) =>
        Set.is_subset(set, set)
    }
  }


  property("intersection should return set that smaller than parameters") {
    forAll { 
      (left: Set[Int], right: Set[Int]) =>
        Set.intersect(left, right).size() <= left.size().min(right.size())
    }
  }


  property("intersection should return subset of parameters") {
    forAll{
      (left: Set[Int], right: Set[Int]) =>
        Set.is_subset(Set.intersect(left, right), left) 
         && Set.is_subset(Set.intersect(left, right), right) 
    }
  }


  property("set intersection with itself is that same set") {
    forAll {
      (set: Set[Int]) =>
        Set.is_subset(set, Set.intersect(set, set)) 
          && set.size() == Set.intersect(set, set).size()
    }
  }


  property("union should return set that not smaller than parameters") {
    forAll { 
      (left: Set[Int], right: Set[Int]) =>
        Set.union(left, right).size() >= left.size().max(right.size())
    }
  }


  property("set union with itself is that same set") {
    forAll {
      (set: Set[Int]) =>
        Set.is_subset(set, Set.union(set, set)) 
          && set.size() == Set.union(set, set).size()
    }
  }


  property("union should return set that contains parameters") {
    forAll{
      (left: Set[Int], right: Set[Int]) =>
        Set.is_subset(left, Set.union(left, right)) 
         && Set.is_subset(right, Set.union(left, right)) 
    }
  }


  property("map for set must be equal to set, constructed with map for sequence") {
    def f (x: Int): Int = x*x - x + 2;
    forAll{
      (xs: Seq[Int]) =>
        Set.is_subset(
          Set.map(Set(xs*), f),
          Set(xs.distinct.map(f)*)
        ) 
        && Set.map(Set(xs*), f).size() == Set(xs.distinct.map(f)*).size()
    }
  }
}
