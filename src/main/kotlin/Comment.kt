data class Comment(
    val id: Long = 0,
    val message: String,
    val visibility: Boolean = true,
) {
    override fun toString(): String {
        return "Comment: $id \n$message\n"
    }
}