object WallService : OverallService<Post> {
    var posts = mutableListOf(
        Post(
            0,
            1,
            1,
            1,
            1,
            "",
            1,
            1,
            false,
            null,
            null,
            null,
            null,
            null,
            "",
            1,
            false,
            false,
            false,
            1,
            false,
            false,
            null,
            1
        )
    )
    private var commentsPost = mutableListOf(Comment(message = "test"))
    private var listId: Long = 0

    override fun add(entity: Post): Long {
        listId++
        val postId = entity.copy(id = listId)
        posts += postId
        return listId
    }

    override fun delete(id: Long) {
        posts.forEach { post -> if (post.id == id) posts.remove(post) }
    }

    override fun edit(entity: Post) {
        for ((index, post) in posts.withIndex()) {
            if (entity.id == post.id) {
                val newPost = entity.copy(ownerId = post.ownerId, date = post.date)
                posts[index] = newPost
            }
        }
    }

    override fun read(): List<Post> {
        return posts
    }

    override fun getById(id: Long): Post {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) return posts[index]
        }
        throw CommentNotePostNotFoundException("Post with this id was not found")
    }

    fun addComment(comment: Comment): Long {
        for (post in posts) {
            if (post.id == comment.id) {
                commentsPost += comment
                return comment.id
            }
        }
        throw CommentNotePostNotFoundException("Post not founded")
    }
}