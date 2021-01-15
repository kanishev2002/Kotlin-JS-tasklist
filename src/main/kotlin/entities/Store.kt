package entities

data class Store(
    var tasks: List<Task>,
    var inputParameters: MutableMap<String, String>
)
