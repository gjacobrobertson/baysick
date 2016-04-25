## BAYSICK - an embedded Insane-specific Language for Scala implementing a subset of BASIC

### Here is a small example:

```scala
    import edu.utexas.cs.jacobr.baysick.Script

    new Script {
      10 PRINT "Enter a number"
      20 INPUT N
      30 PRINT "Square root of " || "N is " || SQR(N)
      40 END
    }.run
```

### Here is a larger example

```scala
    object Lunar extends App with Environment {
      10 PRINT "Welcome to Baysick Lunar Lander v0.0.1"
      20 LET D = 100 // Distance
      30 LET V = 1 // Velocity
      40 LET F = 1000 // Fuel
      50 LET M = 1000 // Mass

      60 PRINT "You are a in control of a lunar lander."
      70 PRINT "You are drifting towards the surface of the moon."
      80 PRINT "Each turn you must decide how much fuel to burn."
      90 PRINT "To accelerate enter a positive number, to decelerate a negative"

      100 PRINT "Distance " || D || "km, " || "Velocity " || V || "km/s, " || "Fuel " || F
      110 INPUT B //Burn
      120 IF ABS(B) <= F THEN 150
      130 PRINT "You don't have that much fuel"
      140 GOTO 100
      150 LET V = V + B * 10 / (F + M)
      160 LET F = F - ABS(B)
      170 LET D = D - V
      180 IF D > 0 THEN 100
      190 PRINT "You have hit the surface"
      200 IF V < 3 THEN 240
      210 PRINT "Hit surface too fast (" || V || ")km/s"
      220 PRINT "You Crashed!"
      230 GOTO 250
      240 PRINT "Well done"

      250 END

      RUN
    }
```

Enjoy.

*note: [The BASIC programming language is 50 years old](https://www.dartmouth.edu/basicfifty/)*

## License

Original Work Copyright 2010-2014, [Michael Fogus](http://www.fogus.me)
Modified Work Copyright 2016, Jacob Robertson
Licensed under the MIT License.  
Redistributions of files must retain the above copyright notice.
