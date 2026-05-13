package xyz.teamgravity.coresdkview.konfetti

import androidx.core.content.ContextCompat
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Spread
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Shape
import nl.dionsegijn.konfetti.core.models.Size
import nl.dionsegijn.konfetti.xml.KonfettiView
import xyz.teamgravity.coresdkview.R
import java.util.concurrent.TimeUnit

fun KonfettiView.parade() {
    val start = Party(
        angle = Angle.RIGHT - 45,
        spread = Spread.SMALL,
        speed = 10F,
        maxSpeed = 30F,
        damping = 0.9F,
        size = listOf(
            Size(
                sizeInDp = 12
            ),
            Size(
                sizeInDp = 16,
                mass = 6F
            )
        ),
        colors = listOf(
            ContextCompat.getColor(context, R.color.lt_yellow),
            ContextCompat.getColor(context, R.color.lt_orange),
            ContextCompat.getColor(context, R.color.lt_pink),
            ContextCompat.getColor(context, R.color.lt_purple),
            ContextCompat.getColor(context, R.color.dk_blue),
            ContextCompat.getColor(context, R.color.dk_cyan),
            ContextCompat.getColor(context, R.color.dk_green),
            ContextCompat.getColor(context, R.color.dk_red)
        ),
        shapes = listOf(
            Shape.Circle,
            Shape.Square
        ),
        position = Position.Relative(
            x = 0.0,
            y = 0.3
        ),
        emitter = Emitter(
            duration = 2,
            timeUnit = TimeUnit.SECONDS
        ).perSecond(30)
    )

    val end = start.copy(
        angle = start.angle - 90,
        position = Position.Relative(
            x = 1.0,
            y = 0.3
        )
    )

    start(start, end)
}