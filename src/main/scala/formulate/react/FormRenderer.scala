package formulate
package react

import monocle._
import monocle.macros.GenPrism
import monocle.function.Index._
import japgolly.scalajs.react._
import japgolly.scalajs.react.CompState._
import japgolly.scalajs.react.MonocleReact._
import japgolly.scalajs.react.vdom.prefix_<^._

trait FormRenderer {
  def apply(proxy: ReadDirectWriteCallbackOps[Form]): ReactElement
}

object FormRenderer {
  object Default extends FormRenderer {
    def apply(proxy: ReadDirectWriteCallbackOps[Form]): ReactElement = {
      def loop(form: Form, focus: Optional[Form, Form] = Optional.id[Form]): ReactElement =
        form match {
          case GroupForm(subforms) =>
            <.div(subforms.zipWithIndex.map {
              case (subform, i) =>
                val subformFocus: Optional[Form, Form] =
                  focus
                  .composePrism(GenPrism[Form, GroupForm])
                  .composeLens(GroupForm.subforms)
                  .composeOptional(index(i))

                loop(subform, subformFocus)
            })

          case Label(text) =>
            <.p(text)

          case TextField(value) =>
            val valueFocus: Optional[Form, String] =
              focus
              .composePrism(GenPrism[Form, TextField])
              .composeLens(TextField.value)

            def update(event: ReactEventI) =
              proxy.setState(valueFocus.set(event.target.value)(proxy.state))

            <.p(<.input(^.onChange ==> update, ^.value := value))
        }

      loop(proxy.state)
    }
  }

  object ReadOnly extends FormRenderer {
    def apply(proxy: ReadDirectWriteCallbackOps[Form]): ReactElement = {
      def loop(form: Form, focus: Optional[Form, Form] = Optional.id[Form]): ReactElement =
        form match {
          case GroupForm(subforms) =>
            <.ul(subforms.map(subform => <.li(loop(subform))))

          case Label(text) =>
            <.p(text)

          case TextField(value) =>
            <.p(value)
        }

      loop(proxy.state)
    }
  }
}
