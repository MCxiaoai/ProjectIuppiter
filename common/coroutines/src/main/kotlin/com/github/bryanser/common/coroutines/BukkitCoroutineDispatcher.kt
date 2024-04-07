package com.github.bryanser.common.coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Runnable
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

class BukkitCoroutineDispatcher(
    private val plugin:Plugin
) : CoroutineDispatcher() {
    private val mainThread: Thread = Thread.currentThread()

    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return Thread.currentThread() != mainThread
    }
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        plugin.server.scheduler.runTask(plugin, block)
    }

}