package edu.utexas.cs.jacobr.baysick

import scala.util.Random

trait Functions {
  def ABS(x: Double) = math.abs(x)
  def ATN(x: Double) = math.atan(x)
  def COS(x: Double) = math.cos(x)
  def EXP(x: Double) = math.exp(x)
  def INT(x: Double) = math.floor(x)
  def LOG(x: Double) = math.log(x)
  def RND = Random.nextDouble
  def SIN(x: Double) = math.sin(x)
  def SQR(x: Double) = math.sqrt(x)
  def TAN(x: Double) = math.tan(x)
}
