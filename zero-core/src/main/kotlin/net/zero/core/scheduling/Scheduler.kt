package net.zero.core.scheduling

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import java.util.function.Supplier
import javax.annotation.concurrent.ThreadSafe

/**
 * A scheduler that will execute tasks with a precision dependent on the tick
 * rate of the server.
 *
 * This does not necessarily mean that task execution times are dependent on
 * the server's tick rate, only that the scheduler will attempt to execute
 * tasks at the same rate as the server's tick rate.
 */
@ThreadSafe
@SidedApi(Side.SERVER)
interface Scheduler {

    /**
     * Submits a task to be executed by the scheduler.
     *
     * This is the primitive method for scheduling tasks. The task's execution
     * depends entirely on the action returned by the given [task] supplier.
     *
     * This is designed to allow much more fine-grained control over the
     * execution of a task, and it is recommended that, if you wish to specify
     * a standard delay and period, as you would with many other schedulers,
     * that you use the provided [buildTask] or [scheduleTask] methods.
     *
     * @param task The task to submit.
     * @param executionType The execution type of the task.
     *
     * @return The scheduled task.
     */
    fun submitTask(task: Supplier<TaskAction>, executionType: ExecutionType): Task

    /**
     * Submits a task to be executed by the scheduler.
     *
     * This is equivalent to [submitTask], except that the execution type will
     * be chosen by the implementation.
     *
     * @param task The task to submit.
     *
     * @return The scheduled task.
     */
    fun submitTask(task: Supplier<TaskAction>): Task

    /**
     * Creates a new task builder that will execute the given [task].
     *
     * This is the recommended method for scheduling tasks, as it is easier to
     * use, and provides a more conventional way of scheduling tasks that
     * should be more familiar to most users.
     *
     * If more fine-grained control over the scheduling of a task is required,
     * the [submitTask] method should be used instead.
     *
     * @param task The task to execute.
     *
     * @return A new task builder.
     */
    fun buildTask(task: Runnable): Task.Builder

    /**
     * Schedules the given [task] to be executed by the scheduler after the
     * given [delay], with a period between executions of [period], and with
     * the given [executionType].
     *
     * This is a shortcut to building a task with [buildTask].
     *
     * @param task The task to execute.
     * @param delay The initial delay before execution.
     * @param period The delay between subsequent executions.
     * @param executionType The execution type of the task.
     *
     * @return The scheduled task.
     */
    fun scheduleTask(task: Runnable, delay: TaskTime, period: TaskTime, executionType: ExecutionType): Task {
        return buildTask(task).delay(delay).period(period).executionType(executionType).schedule()
    }

    /**
     * Schedules the given [task] to be executed by the scheduler after the
     * given [delay], with a period between executions of [period].
     *
     * This is equivalent to [scheduleTask], except that the execution type
     * is chosen by the implementation.
     *
     * @param task The task to execute.
     * @param delay The initial delay before execution.
     * @param period The delay between subsequent executions.
     *
     * @return The scheduled task.
     */
    fun scheduleTask(task: Runnable, delay: TaskTime, period: TaskTime): Task {
        return buildTask(task).delay(delay).period(period).schedule()
    }
}
