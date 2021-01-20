package actions

import entities.Task
import redux.RAction

class ChangeInputParameters(val key: String, val value: String) : RAction

class AddTask(val task: Task) : RAction

class UpdateTaskCompletion(val id: Int) : RAction