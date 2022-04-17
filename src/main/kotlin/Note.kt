data class Note(
    val title: String,
    val text: String,
    val id: Long = 0,
    val visibility: Boolean = true,
) {
    override fun toString(): String {
        return "Note: $id Title: $title \n$text\n"
    }
}
