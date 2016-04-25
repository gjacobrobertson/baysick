package edu.utexas.cs.jacobr.baysick

import scala.language.implicitConversions

trait Language extends Functions with Variables {
  def program: Program

  class BranchBuilder(n: Int, pred: => Boolean) {
    def THEN(line: Int) = program += n -> new Branch(pred, line)
  }

  implicit class LineBuilder(n: Int) {
    def PRINT(x: => Printable) = program += n -> new Print(x)
    def GOTO(line: Int) = program += n -> Goto(line)
    def INPUT(name: Symbol) = program += n -> Input(name)
    def IF(pred: => Boolean) = new BranchBuilder(n, pred)
    def END = program += n -> End
    object LET {
      def update(name: Symbol, value: => Double) =
        program += n -> new Let(name, value)
    }
  }

  implicit class Printable(x: Any) {
    override def toString = x.toString
    def ||(y: Printable) = toString + y.toString
  }
  implicit def sym2Printable(sym: Symbol) = Printable(sym2Double(sym))
  implicit def sym2Double(sym: Symbol) = program(sym)
}
