package edu.utexas.cs.jacobr.baysick

import scala.collection.SortedMap
import scala.collection.mutable.HashMap
import scala.io.StdIn

class Program {
  var lines: SortedMap[Int, Line] = SortedMap.empty
  var bindings: HashMap[Symbol, Double] = HashMap.empty
  var pc: Iterator[Line] = Iterator.empty

  def evaluate(line: Line) = {
    line match {
      case p: Print => println(p.get)
      case l: Let => bindings += l.binding
      case b: Branch => if (b.eval) pc = lines.valuesIteratorFrom(b.line)
      case Goto(n) => pc = lines.valuesIteratorFrom(n)
      case Input(k) => bindings += (k -> StdIn.readDouble)
      case End => pc = Iterator.empty
    }
  }

  def run = {
    bindings = HashMap.empty
    pc = lines.valuesIterator
    while (pc.hasNext) (evaluate(pc.next))
  }

  def +=(kv: (Int, Line)) = lines += kv
  def apply(k: Symbol) = bindings(k)

  override def toString = (lines map {case (n, l) => s"$n $l"}).mkString("\n")
}
