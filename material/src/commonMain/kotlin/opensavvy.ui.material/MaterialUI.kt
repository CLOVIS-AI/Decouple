package opensavvy.ui.material

import opensavvy.ui.core.UI
import opensavvy.ui.material.basic.MaterialButtons
import opensavvy.ui.material.basic.MaterialTexts
import opensavvy.ui.material.layout.MaterialLazyLayouts
import opensavvy.ui.material.layout.MaterialLinearLayouts

expect interface MaterialUI : UI, MaterialButtons, MaterialLinearLayouts, MaterialLazyLayouts, MaterialTexts
