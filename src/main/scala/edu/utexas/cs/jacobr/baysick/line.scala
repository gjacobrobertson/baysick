package edu.utexas.cs.jacobr.baysick


sealed trait Line
class Print(x: => Any) extends Line {
  override def toString = s"PRINT <code>"
  def get = x
}
class Let(name: Symbol, value: => Double) extends Line {
  override def toString = s"LET $name = <code>"
  def binding = (name -> value)
}
class Branch(pred: => Boolean, val line: Int) extends Line {
  override def toString = s"IF <code> THEN $line"
  def eval = pred
}
case class Goto(line: Int) extends Line {
  override def toString = s"GOTO $line"
}
case class Input(name: Symbol) extends Line {
  override def toString = s"INPUT $name"
}
case object End extends Line {
  override def toString = "END"
}
