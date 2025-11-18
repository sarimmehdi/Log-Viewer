package com.sarim.utils.test

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.printToString

/**
 * Prints the Compose semantics tree for this [SemanticsNodeInteraction] to the console.
 * Useful for debugging why nodes are not found or not displayed in tests.
 *
 * @param maxDepth the maximum depth of the tree to print, defaults to [Int.MAX_VALUE]
 *
 * Usage example:
 * ```
 * val testRule = createComposeRule()
 * testRule.setContent { YourComposable() }
 * testRule.onRoot().printToLog()
 * ```
 */
fun SemanticsNodeInteraction.printToLog(maxDepth: Int = Int.MAX_VALUE) {
    val result = "printToLog:\n" + printToString(maxDepth)
    println(result)
}
