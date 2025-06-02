package net.zero.core.scheduling

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * Used by tasks to indicate whether they will execute synchronously with the
 * server, or asynchronously from the server.
 *
 * Note: It is not always necessary or preferable to schedule a task
 * asynchronously. If you are unsure, don't set the type when building a task,
 * and the implementation will decide which to use.
 */
@SidedApi(Side.SERVER)
enum class ExecutionType {

    SYNCHRONOUS,
    ASYNCHRONOUS

}
