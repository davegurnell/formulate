package formulate

import monocle.macros._

sealed abstract class Form extends Product with Serializable
@Lenses final case class GroupForm(subforms: List[Form]) extends Form
@Lenses final case class Label(text: String) extends Form
@Lenses final case class TextField(value: String) extends Form
