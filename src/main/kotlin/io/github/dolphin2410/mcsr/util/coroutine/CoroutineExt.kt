package io.github.dolphin2410.mcsr.util.coroutine

import io.github.dolphin2410.mcsr.MCSR
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

fun <T> awaitCoroutine(scope: suspend () -> T): T {
    val future = CompletableFuture<T>()
    MCSR.mainScope.launch {
        future.complete(scope())
    }
    return future.join()
}