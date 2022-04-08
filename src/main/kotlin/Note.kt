data class Note(
    val title: String,
    val text: String,
    val id: Int = 0,
    override val visibility: Boolean = true,
) : Visibility {
    override fun toString(): String {
        return "Note: $id Title: $title \n$text\n"
    }
}
