package entities

data class Store(
    var tasks: List<Task> = emptyList(),
    var inputParameters: MutableMap<String, String> = mutableMapOf()
)
