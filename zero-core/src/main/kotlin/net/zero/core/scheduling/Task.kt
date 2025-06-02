package net.zero.core.scheduling

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import java.time.Duration
import java.time.temporal.TemporalUnit

/**
 * A task that can be scheduled by the scheduler.
 *
 * The task has three states:
 * * Alive: The task is available for scheduling.
 * * Paused: The task is not available for scheduling, but can be made
 * available with [resume].
 * * Cancelled: The task is not available for scheduling, and cannot be made
 * available.
 */
@SidedApi(Side.SERVER)
interface Task {

    /**
     * Indicates how this task will be executed by the scheduler.
     */
    val executionType: ExecutionType

    /**
     * Checks if this task is alive, meaning it is not cancelled, and is
     * available for scheduling or currently being scheduled.
     *
     * @return Whether this task is alive.
     */
    fun isAlive(): Boolean

    /**
     * Checks if this task is currently paused, meaning it will not be
     * executed by the scheduler.
     *
     * @return Whether this task is paused.
     */
    fun isPaused(): Boolean

    /**
     * Resumes this task if it was paused, meaning it can be executed by the
     * scheduler again.
     */
    fun resume()

    /**
     * Cancels this task, with immediate effect.
     *
     * If the scheduler is currently executing the task, it will not be
     * rescheduled. It may finish its execution, however this behaviour is
     * implementation-defined.
     */
    fun cancel()

    /**
     * A builder for building a task.
     *
     * This builder is designed to provide a more familiar way of scheduling
     * tasks, using a more conventional delay and interval (period) system.
     * It is designed to be simple and easy to use, at the cost of flexibility.
     *
     * If you require greater flexibility over how the scheduler will execute
     * your task, you should use [Scheduler.submitTask].
     */
    @SidedApi(Side.SERVER)
    interface Builder {

        /**
         * Sets the execution type for the task.
         *
         * If this is not called, the implementation will choose which type is
         * best.
         *
         * @param type The execution type.
         *
         * @return This builder.
         */
        fun executionType(type: ExecutionType): Builder

        /**
         * Sets the initial execution delay for the task.
         *
         * This defines how long after the task's initial scheduling it will
         * first be ran.
         *
         * If this is not set, the task will be executed immediately.
         *
         * @param time The amount of time for the initial delay.
         *
         * @return This builder.
         */
        fun delay(time: TaskTime): Builder

        /**
         * Sets the initial execution delay for the task.
         *
         * @param duration The duration for the initial delay.
         *
         * @return This builder.
         *
         * @see delay
         */
        fun delay(duration: Duration): Builder = delay(TaskTime.duration(duration))

        /**
         * Sets the initial execution delay for the task.
         *
         * @param amount The amount of time for the initial delay.
         * @param unit The unit of time for the initial delay.
         *
         * @return This builder.
         *
         * @see delay
         */
        fun delay(amount: Long, unit: TemporalUnit): Builder = delay(TaskTime.duration(amount, unit))

        /**
         * Sets the delay between subsequent executions of the task.
         *
         * This defines how long after each execution the task will be ran
         * again.
         *
         * If this is not set, the task will only run once.
         *
         * @param time The amount of time for the delay between executions.
         *
         * @return This builder.
         */
        fun period(time: TaskTime): Builder

        /**
         * Sets the delay between subsequent executions of the task.
         *
         * @param duration The duration for the delay between executions.
         *
         * @return This builder.
         *
         * @see period
         */
        fun period(duration: Duration): Builder = period(TaskTime.duration(duration))

        /**
         * Sets the delay between subsequent executions of the task.
         *
         * @param amount The amount of time for the delay between executions.
         * @param unit The unit of time for the delay between executions.
         *
         * @return This builder.
         *
         * @see period
         */
        fun period(amount: Long, unit: TemporalUnit): Builder = period(TaskTime.duration(amount, unit))

        /**
         * Requests for the scheduler to schedule this task for execution.
         *
         * There is no defined period within which the scheduler must execute
         * the task. It may be executed immediately, it may be executed on the
         * next tick, or if the scheduler is especially busy, it may be
         * executed an arbitrary amount of time after it has been scheduled.
         *
         * @return The scheduled task.
         */
        fun schedule(): Task
    }
}
