# Lab 1 : Functional Programming on Scala
**Name:** Bondar Petro

**Group:** FI-03

**Variant:** 13

## Task
Set[+A]. 
Структура аналогічна List[A], за винятком вимоги, що усі елементи унікальні. Треба робити перевірки на дотримання цього інваріанту там, де це релевантно. Вимог до складності немає (тобто O(n)-додавання та перевірка на включення - це нормально)

1. makeSet(xs: A*): Set[A] (як не дозволити конструювати невалідні інстанси Set? Треба так і зробити)
2. add(s: Set[A], a: A): Set[A]
3. intersect(l: Set[A], r: Set[A]): Set[A]
4. union(l: Set[A], r: Set[A]): Set[A]
5. contains(s: Set[A], a: A): Boolean
6. map(b:  Set[A], f: A => B): Set[B]
