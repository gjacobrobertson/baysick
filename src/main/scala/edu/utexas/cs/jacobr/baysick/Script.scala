package edu.utexas.cs.jacobr.baysick

class Script(body: => Unit = ()) extends Language {
  val program = new Program
  def run = program.run
  body
}
