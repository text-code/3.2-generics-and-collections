import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {

    @Test
    fun addsPostReturnsId() {
        val post = Post(
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
        val expectedPostId: Long = 2

        val actualPostId = WallService.add(post)

        assertEquals(expectedPostId, actualPostId)
    }

    @Test
    fun deletedPost() {
        val expectedDeletedPost = WallService.posts.size

        WallService.delete(1)
        val actualDeletedPost = 1

        assertEquals(expectedDeletedPost, actualDeletedPost)
    }

    @Test
    fun editPost() {
        val post = Post(
            0,
            1,
            1,
            1,
            1,
            "test",
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
        val expectedText = "test"

        WallService.edit(post)
        val actualText = WallService.posts[0].text

        assertEquals(expectedText, actualText)
    }

    @Test
    fun readPost() {
        val expectedPost = WallService.posts

        val actualPost = WallService.read()

        assertEquals(expectedPost, actualPost)
    }

    @Test
    fun getById() {
        val expectedPostById = WallService.posts[0]

        val actualPostById = WallService.getById(0)

        assertEquals(expectedPostById, actualPostById)
    }

    @Test
    fun addsCommentReturnsId() {
        val post = Post(
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
        val comment = Comment(1, "test")

        val expectedCommentId:Long = 1
        WallService.add(post)

        val actualCommentId = WallService.addComment(comment)

        assertEquals(expectedCommentId, actualCommentId)
    }
}