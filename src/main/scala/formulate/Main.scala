package formulate

import formulate.react._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import monocle._
import org.scalajs.dom
import scala.scalajs.js.JSApp

object Main extends JSApp {
  val form: Form =
    GroupForm((1 to 100).map(i => TextField(s"Item $i")).toList)

  val Main = ReactComponentB[Unit]("FormBuilder example")
    .initialState(form)
    .render { proxy =>
      <.table(
        <.tbody(
          <.tr(
            <.td(FormRenderer.Default(proxy)),
            <.td(FormRenderer.ReadOnly(proxy))
          )
        )
      )
    }
    .build

  def main(): Unit =
    ReactDOM.render(Main(), dom.document.getElementById("app"))
}
