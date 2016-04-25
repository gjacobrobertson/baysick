package edu.utexas.cs.jacobr.baysick.macros

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

class Dartmouth extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro DartmouthImpl.annotate
}

class DartmouthImpl(val c: Context) {
  import c.universe._

  def newDefs = {
    val letters = ('A' to 'Z') map (_.toString)
    val digits = "" +: (0 to 9) map (_.toString)
    val names = for (l <- letters; d <- digits) yield (l + d)
    names map { n =>
      val term = TermName(n)
      val sym = scala.Symbol(n)
      q"def $term = $sym"
    }
  }

  def modifiedDeclaration(decl: ClassDef): c.Expr[Any] = {
    val ast = decl match {
      case q"trait $name" => q"trait $name {..$newDefs}"
    }
    c.Expr[Any](ast)
  }
  def annotate(annottees: c.Expr[Any]*): c.Expr[Any] = {
    annottees.map(_.tree) match {
      case (decl: ClassDef) :: _ => modifiedDeclaration(decl)
      case _ => c.abort(c.enclosingPosition, "Invalid annottee")
    }
  }
}
