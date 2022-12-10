package opensavvy.decouple.material

import opensavvy.decouple.core.UI

expect interface MaterialUI : UI, opensavvy.decouple.material.basic.MaterialButtons,
                              opensavvy.decouple.material.layout.MaterialLinearLayouts,
                              opensavvy.decouple.material.layout.MaterialLazyLayouts,
                              opensavvy.decouple.material.basic.MaterialTexts
