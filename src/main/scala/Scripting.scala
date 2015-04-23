import javax.script._
import jdk.nashorn.api.scripting.ScriptObjectMirror

import scala.collection.JavaConversions._

object Scripting extends App {

  val sem = new ScriptEngineManager()
  sem.getEngineFactories.toList foreach { f =>
    println(s"${f.getEngineName} | ${f.getEngineVersion}")
    println(s"${f.getLanguageName} | ${f.getLanguageVersion}")
    println(s"${f.getExtensions.toList}")
    println(s"${f.getNames.toList}")
    println(s"${f.getMimeTypes.toList}")
    println("-----")
  }

  println("---- javascript ----")

  val js = sem.getEngineByName("javascript")

  val jsInt = js.eval("456")
  val jsIntS = jsInt.asInstanceOf[Int]
  println(jsIntS)

  val jsDouble = js.eval("23.4 * 23.1")
  val jsDoubleS = jsDouble.asInstanceOf[Double]
  println(jsDoubleS)

  val jsBool = js.eval("false || true")
  val jsBoolS = jsBool.asInstanceOf[Boolean]
  println(jsBoolS)

  val jsStr = js.eval(" 'dupa' + '<>' + 'biskupa' ")
  val jsStrS = jsStr.asInstanceOf[String]
  println(jsStrS)

  val jsArray = js.eval("[2,3,4,5].map(function (x) { return x * 2; }) ")
  val jsArrayS = jsArray.asInstanceOf[ScriptObjectMirror]
  println(jsArrayS.values().toList)

  val jsDict = js.eval("var x = {a: 123, b:true, c: 23.0}; x")
  val jsDictS = jsDict.asInstanceOf[java.util.Map[_,_]]
  println(jsDictS.toMap)

  val jsFun = js.eval("function (x) { return x + x; }")
  val jsFunS = null
  println(jsFun + " " + jsFun.getClass.getName)

  val jsDate = js.eval("new Date()")
  println(jsDate + " " + jsDate.getClass.getName)

  val jsFunDef = js.eval("function f(x) { return x + x + x; }")
  println(jsFunDef + " " + jsFunDef.getClass.getName)
  val jsFunInv = js.eval("f(10)").asInstanceOf[Double]
  println(jsFunInv)


  println("---- ruby ----")

  val rb = sem.getEngineByName("ruby")

  val rbInt = rb.eval("456")
  println(rbInt + " " + rbInt.getClass.getName)

  val rbDouble = rb.eval("23.4 * 23.1")
  println(rbDouble + " " + rbDouble.getClass.getName)

  val rbBool = rb.eval("false or true")
  println(rbBool + " " + rbBool.getClass.getName)

  val rbStr = rb.eval(""" 'dupa' + '<>' + "biskupa"  """)
  println(rbStr + " " + rbStr.getClass.getName)

  val rbArray = rb.eval(" [1,2,3,4].map {|x| x * 3}")
  println(rbArray.asInstanceOf[java.util.List[_]].toList + " " + rbArray.getClass.getName)

  val rbDict = rb.eval("{ a: 123, b: true, c: 23.0 }")
  println(rbDict.asInstanceOf[java.util.Map[_,_]].toMap + " " + rbDict.getClass.getName)

  val rbDict2 = rb.eval("{ :a => 123, :b => true, :c => 23.0 }")
  println(rbDict2.asInstanceOf[java.util.Map[_,_]].toMap + " " + rbDict2.getClass.getName)

  val rbFun = rb.eval("lambda { |x| x + x }")
  println(rbFun + " " + rbFun.getClass.getName)

  val rbDate = rb.eval("Time.now")
  println(rbDate + " " + rbDate.getClass.getName)

  val rbFunDef = rb.eval("def f(x) return x + x + x end")
  println(rbFunDef)// + " " + rbFunDef.getClass.getName)
  val rbFunInv = rb.eval("f(10.0)").asInstanceOf[Double]
  println(rbFunInv)


  println("---- scala ----")
  val sc = sem.getEngineByName("scala")

  val settings = sc.asInstanceOf[scala.tools.nsc.interpreter.IMain].settings
  settings.embeddedDefaults[Scripting.type]
  settings.usejavacp.value = true

  val scInt = sc.eval("456")
  println(scInt + " " + scInt.getClass.getName)

  val scDouble = sc.eval("23.4 * 23.1")
  println(scDouble + " " + scDouble.getClass.getName)

  val scBool = sc.eval("false || true")
  println(scBool + " " + scBool.getClass.getName)

  val scStr = sc.eval(""" "dupa" + "<>" + "biskupa" """)
  println(scStr + " " + scStr.getClass.getName)

  val scArray = sc.eval("Array(1,2,3,4).map(_ * 2)")
  println(scArray.asInstanceOf[Array[_]].toList + " " + scArray.getClass.getName)

  val scArray2 = sc.eval("List(1,2,3,4).map(_ * 2)")
  println(scArray2 + " " + scArray2.getClass.getName)

  val scDict = sc.eval("Map(\"a\" -> 123, \"b\" -> true, \"c\" -> 23.0)")
  println(scDict + " " + scDict.getClass.getName)

  val scFun = sc.eval("{ x: Int => x + x }")
  println(scFun + " " + scFun.getClass.getName)

  val scDate = sc.eval("import java.util._; new Date()")
  println(scDate + " " + scDate.getClass.getName)

  val scDate2 = sc.eval("import java.time._; LocalDateTime.now")
  println(scDate2 + " " + scDate2.getClass.getName)

  val scFunDef = sc.eval("def f(x: Double): Double = { x + x + x }")
  println(scFunDef)// + " " + scFunDef.getClass.getName)
  val scFunInv = sc.eval("f(10)").asInstanceOf[Double]
  println(scFunInv)



}

