package net.zero.core.scheduling

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.TypeFactory
import net.zero.core.Zero
import org.jetbrains.annotations.ApiStatus
import java.util.concurrent.CompletableFuture

/**
 * The action to take after a task has been executed.
 *
 * This allows for tasks to be rescheduled, parked, or stopped.
 */
@SidedApi(Side.SERVER)
interface TaskAction {

    @TypeFactory
    @ApiStatus.Internal
    @SidedApi(Side.SERVER)
    interface Factory {

        fun scheduleAfter(time: TaskTime): TaskAction

        fun scheduleWhenComplete(future: CompletableFuture<*>): TaskAction

        fun pause(): TaskAction

        fun cancel(): TaskAction
    }

    companion object {

        /**
         * An action that indicates the scheduler should schedule the task
         * for execution in the given amount of [time].
         *
         * This can be used for both initial schedules and reschedules.
         *
         * @param time The time to schedule the task for.
         *
         * @return A schedule action.
         */
        @JvmStatic
        @SidedApi(Side.SERVER)
        fun scheduleAfter(time: TaskTime): TaskAction = Zero.factory<Factory>().scheduleAfter(time)

        /**
         * An action that indicates the scheduler should schedule the task
         * when the given [future] is complete.
         *
         * This is useful for tasks that have to wait on resources or actions
         * to be completed before they can be safely executed.
         *
         * @param future The future to schedule a task when complete.
         *
         * @return A schedule after action.
         */
        @JvmStatic
        @SidedApi(Side.SERVER)
        fun scheduleWhenComplete(future: CompletableFuture<*>): TaskAction = Zero.factory<Factory>().scheduleWhenComplete(future)

        /**
         * An action that indicates the scheduler should pause the task,
         * meaning it will not execute until it is explicitly resumed
         * with [Task.resume].
         *
         * This is useful for tasks that need to be stopped and started
         * arbitrarily, and avoids the need to cancel the task and
         * reschedule it.
         *
         * @return A park action.
         */
        @JvmStatic
        @SidedApi(Side.SERVER)
        fun pause(): TaskAction = Zero.factory<Factory>().pause()

        /**
         * An action that indicates the scheduler should cancel the task,
         * meaning it can never be executed again.
         *
         * Once a task has been cancelled, it cannot be restarted under any
         * circumstances. If you wish to pause a task and resume it later,
         * use [pause] instead.
         *
         * @return An action that stops a task from executing.
         */
        @JvmStatic
        @SidedApi(Side.SERVER)
        fun cancel(): TaskAction = Zero.factory<Factory>().cancel()
    }
}
