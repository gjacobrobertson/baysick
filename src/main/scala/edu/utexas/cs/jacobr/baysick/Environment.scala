package edu.utexas.cs.jacobr.baysick

import scala.language.dynamics

trait Environment extends Language { self =>
  var catalog = Map[String, Program]()
  var name: String = ""
  var program = new Program

  object NEW extends Dynamic {
    def apply(name: String) = {
      program = new Program
      self.name = name
    }
    val selectDynamic = apply _
  }

  object OLD extends Dynamic {
    def apply(name: String) = program = catalog(name)
    val selectDynamic = apply _
  }

  object RENAME extends Dynamic {
    def apply(name: String) = self.name = name
    val selectDynamic = apply _
  }

  object REPLACE extends Dynamic {
    def apply(name: String) = catalog += name -> program
    val selectDynamic = apply _
  }

  def LIST = println(program)
  def SAVE = catalog += name -> program
  def UNSAVE = catalog -= name
  def CATALOG = catalog.keys foreach println
  def SCRATCH = program = new Program

  def RUN = program.run
}

object Environment extends Environment
