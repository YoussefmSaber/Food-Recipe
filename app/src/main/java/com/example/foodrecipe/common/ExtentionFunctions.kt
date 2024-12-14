package com.example.foodrecipe.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.CompositingStrategy


/**
 * Applies a fading edge effect to a composable using a specified brush.
 *
 * This modifier creates a fading edge by drawing a rectangle with the given brush
 * on top of the composable's content, using a Darken blend mode.
 *
 * @param brush The brush to use for the fading edge.
 * @return A modifier that applies the fading edge effect.
 */
fun Modifier.fadingEdge(brush: Brush) = this
    // Apply a graphics layer with offscreen compositing strategy.
    // This ensures that the fading edge is drawn separately and doesn't affect
    // the composable's content.
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    // Draw the composable's content and then draw a rectangle with the brush.
    .drawWithContent {
        // Draw the original content of the composable.
        drawContent()
        // Draw a rectangle over the content using the provided brush and Darken blend mode.
        // This creates the fading edge effect.
        drawRect(brush = brush, blendMode = BlendMode.Darken)
    }