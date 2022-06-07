// ReactYoutube.kt
@file:JsModule("@lite-embed/react")
@file:JsNonModule

package explorer

import react.ComponentClass
import react.Props

@JsName("ReactYouTubeLite")
external val YoutubeLite: ComponentClass<ReactYouTubeProps>

external interface ReactYouTubeProps : Props {
    var urlOrId: String
}