/**
 * Original Work Copyright (c) 2010-2014 Michael Fogus, http://www.fogus.me
 * Modified Work Copyright (c) 2016 Jacob Robertson
 *
 * Licensed under The MIT License
 * Re-distributions of files must retain the above copyright notice.
 *
 * #######        ##      ##    ##    ######     ####     ######     ##   ##
 * ##    ##      ####      ##  ##    ##           ##     ##    ##    ##  ##
 * #######      ##  ##       ##       ######      ##     ##          ####
 * ##    ##    ########      ##            ##     ##     ##    ##    ##  ##
 * #######    ##      ##     ##       ######     ####     ######     ##   ##
 **/

import edu.utexas.cs.jacobr.baysick.Script
import org.scalatest.FlatSpec
import java.io.{StringReader, ByteArrayOutputStream}

/**
 * Tests for Environment.
 * TODO implement tests that actually check output and input.
 */
class BaysickTests extends FlatSpec {
  def testScript(script: Script, input: String, output: String) = {
    val in = new StringReader(input)
    val out = new ByteArrayOutputStream()
    Console.withIn(in) {
      Console.withOut(out) {
        script.run
        assert(out.toString == output)
      }
    }
  }

  "Hello world" should "say hello to the world" in {
    val script = new Script {
      10 PRINT "Hello Cleveland!"
      20 END
    }
    testScript(script, "", "Hello Cleveland!\n")
  }

  "HelloNumbers" should "print some numbers" in {
    val script = new Script {
      10 PRINT "Here are some numbers:"
      20 PRINT 42
      30 PRINT 102845646545646546L
      40 PRINT "... and some math"
      50 PRINT "2 + 2"
      60 PRINT 2 + 2
      70 PRINT "102845646545646546 * 2"
      80 PRINT 102845646545646546L * 2
      90 PRINT "5 - 100"
      100 PRINT 5 - 100
      110 PRINT "3/2"
      120 PRINT 3 / 2
      130 END
    }
    testScript(script, "",
s"""Here are some numbers:
42
102845646545646546
... and some math
2 + 2
4
102845646545646546 * 2
${102845646545646546L * 2}
5 - 100
-95
3/2
1
""")
  }

  "HelloInput" should "say hello" in {
    val script = new Script {
      10 PRINT "What is your name: "
      20 INPUT A
      30 PRINT "Hello " || A
      40 END
    }
    testScript(script, "42\n", "What is your name: \nHello 42.0\n")
  }

  "HelloLet" should "let hello" in {
    val script = new Script {
      10 LET A = 42
      20 PRINT A
      30 END
    }
    testScript(script, "", "42.0\n")
  }

  "HelloPrint" should "print hello" in {
    val script = new Script {
      40 LET A = 42
      50 PRINT "Hello " || 42
      60 PRINT "Hello " || A
      70 PRINT 42 || " World"
      80 PRINT A || " World"
      90 PRINT A || A
      100 PRINT 42 || 42
      110 PRINT "Hello " || "World"
      120 END
    }
    testScript(script, "",
"""Hello 42
Hello 42.0
42 World
42.0 World
42.042.0
4242
Hello World
"""
    )
  }

  "HelloIf" should "do branching" in {
    val script = new Script {
      10 LET A = 5
      20 IF A >= 5 THEN 40
      30 PRINT "This will never execute"
      40 PRINT "They were equal!"
      50 IF A == 6 THEN 70
      60 PRINT "This will execute 1st..."
      70 PRINT "...then this!"
      80 END
    }
    testScript(script, "",
"""They were equal!
This will execute 1st...
...then this!
"""
    )
  }
}
