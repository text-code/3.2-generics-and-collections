data class Comment(
    val id: Int = 0,
    val message: String,
    override val visibility: Boolean = true,
) : Visibility {
    override fun toString(): String {
        return "Comment: $id \n$message\n"
    }
}