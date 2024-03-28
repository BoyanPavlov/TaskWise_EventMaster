import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun OutlinedStar(
    modifier: Modifier = Modifier,
    onStarClicked: () -> Unit
) {
    Canvas(
        modifier = modifier
            .size(34.dp)
            .clickable(onClick = onStarClicked)
    ) {
        val path = Path()
        val center = size.width / 1.9
        val radius = size.width / 2.5
        val angle = (2 * PI) / 5 // angle between points (360 degrees / 5 points)

        for (i in 0 until 5) {
            val outerX = center + radius * cos(i * angle - PI / 2)
            val outerY = center + radius * sin(i * angle - PI / 2)
            val innerX = center + (radius / 2) * cos((i * angle + angle / 2) - PI / 2)
            val innerY = center + (radius / 2) * sin((i * angle + angle / 2) - PI / 2)

            if (i == 0) {
                path.moveTo(outerX.toFloat(), outerY.toFloat())
            } else {
                path.lineTo(outerX.toFloat(), outerY.toFloat())
            }
            path.lineTo(innerX.toFloat(), innerY.toFloat())
        }
        path.close()

        drawPath(
            path = path,
            color = Color.Gray,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}

@Composable
fun Star(
    isFilled: Boolean,
    modifier: Modifier = Modifier,
    onStarClicked: () -> Unit
) {
    if (isFilled) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            modifier = modifier
                .clickable(onClick = onStarClicked)
                .size(34.dp),
            tint = Color.Yellow
        )
    } else {
        OutlinedStar(
            modifier = modifier,
            onStarClicked = onStarClicked
        )
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit,
) {
    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            Star(
                isFilled = i <= currentRating,
                onStarClicked = { onRatingChanged(i) }
            )
        }
    }
}
