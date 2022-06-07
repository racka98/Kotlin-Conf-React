package explorer

import csstype.Display
import csstype.NamedColor
import csstype.Position
import csstype.px
import emotion.react.css
import model.Video
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.p
import react.key
import react.memo

external interface VideoListProps : Props {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

val VideoList = memo(
    FC<VideoListProps> { props ->
        for (vid in props.videos) {
            p {
                key = vid.id.toString()
                onClick = {
                    props.onSelectVideo(vid)
                }

                if (vid == props.selectedVideo) +"▶ "
                +"${vid.speaker}: ${vid.title}"
            }
        }
    }
)

external interface VideoPlayerProps : Props {
    var video: Video
    var onWatchedVideoPressed: (Video) -> Unit
    var unWatchedVideo: Boolean
}

val VideoPlayer = memo(
    FC<VideoPlayerProps> { props ->
        div {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            h3 {
                +"${props.video.speaker}: ${props.video.title}"
            }
            button {
                css {
                    display = Display.block
                    backgroundColor = if (props.unWatchedVideo) NamedColor.lightgreen else NamedColor.red
                }
                onClick = { props.onWatchedVideoPressed(props.video) }
                if (props.unWatchedVideo) {
                    +"Mark as Watched"
                } else +"Mark as UnWatched"
            }

            div {
                css {
                    display = Display.flex
                    marginBottom = 10.px
                }
                EmailShareButton {
                    url = props.video.videoUrl
                    EmailIcon {
                        size = 32
                        round = true
                    }
                }
                TelegramShareButton {
                    url = props.video.videoUrl
                    TelegramIcon {
                        size = 32
                        round = true
                    }
                }
            }

            /*YoutubeLite {
                urlOrId = props.video.videoUrl
            }*/
        }
    }
)