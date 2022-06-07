package explorer

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import model.Video
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.useEffectOnce
import react.useState

val mainScope = MainScope()

val Explorer = FC<Props> {

    var currentVideo: Video? by useState(null)
    var unWatchedList: List<Video> by useState(emptyList())
    var watchedList: List<Video> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            unWatchedList = fetchVideos()
        }
    }

    h1 {
        +"KotlinConf Explorer"
    }
    div {
        h3 {
            +"Videos to Watch"
        }
        VideoList {
            videos = unWatchedList
            selectedVideo = currentVideo
            onSelectVideo = {
                currentVideo = it
            }
        }
        h3 {
            +"Videos Watched"
        }
        VideoList {
            videos = watchedList
            selectedVideo = currentVideo
            onSelectVideo = {
                currentVideo = it
            }
        }
    }

    currentVideo?.let {
        VideoPlayer {
            video = it
            unWatchedVideo = currentVideo in unWatchedList
            onWatchedVideoPressed = {
                if (it in unWatchedList) {
                    unWatchedList = unWatchedList - it
                    watchedList = watchedList + it
                } else {
                    watchedList = watchedList - it
                    unWatchedList = unWatchedList + it
                }
            }
        }
    }
}

val unwatchedVideos = listOf(
    Video(1, "Opening Keynote", "Andrey Breslav", "https://youtu.be/PsaFVLr8t4E"),
    Video(2, "Dissecting the stdlib", "Huyen Tue Dao", "https://youtu.be/Fzt_9I733Yg"),
    Video(3, "Kotlin and Spring Boot", "Nicolas Frankel", "https://youtu.be/pSiZVAeReeg")
)

val watchedVideos = listOf(
    Video(4, "Creating Internal DSLs in Kotlin", "Venkat Subramaniam", "https://youtu.be/JzTeAM8N1-o")
)