import sbt._
import Keys._
import PlayProject._
import com.typesafe.sbtscalariform.ScalariformPlugin._
import scalariform.formatter.preferences._

object ApplicationBuild extends Build {

    val appName         = "mapperdao-sample"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // sbtで依存関係を解決しようとすると、classgeneratorの取得で失敗する(mavenじゃないと解決できないpomになってしまってるようだ）
      // そのため、仕方なく unmanaged dependencies の仕組みを使う
//      "com.googlecode.mapperdao" % "mapperdao" % "1.0.0.rc15-2.9.2",
      "jp.furyu" %% "play-velocity-plugin" % "1.0"
    )

  lazy val appScalariformSettings = ScalariformKeys.preferences := FormattingPreferences().
    setPreference(IndentWithTabs, true).setPreference(DoubleIndentClassDeclaration, true).setPreference(PreserveDanglingCloseParenthesis, true)

  lazy val s = Defaults.defaultSettings ++ Seq(defaultScalaSettings: _*) ++ Seq(scalariformSettings: _*) ++ Seq(appScalariformSettings)
  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA, settings = s)

}
