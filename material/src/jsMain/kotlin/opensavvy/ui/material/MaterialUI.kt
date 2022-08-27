package opensavvy.ui.material

import opensavvy.ui.core.UI
import opensavvy.ui.material.basic.MaterialButtons
import opensavvy.ui.material.basic.MaterialTexts
import opensavvy.ui.material.layout.MaterialLinearLayouts

actual interface MaterialUI : UI, MaterialButtons, MaterialLinearLayouts, MaterialTexts
