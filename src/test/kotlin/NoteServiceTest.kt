import org.junit.After
import org.junit.Assert.*
import org.junit.Test

class NoteServiceTest {
    @After
    fun clean() {
        NoteService.clean()
    }

    @Test
    fun addsNoteReturnsId() {
        val note = Note("", "")
        val expectedNoteId: Long = 1

        val actualPostId = NoteService.add(note)

        assertEquals(expectedNoteId, actualPostId)
    }

    @Test
    fun deleteNote() {
        val note = Note("", "")
        val expectedDeletedPost = false

        NoteService.add(note)
        NoteService.delete(1)
        val actualDeletedPost = NoteService.notes[0].visibility

        assertEquals(expectedDeletedPost, actualDeletedPost)
    }

    @Test
    fun editNote() {
        val note = Note("", "")
        val noteEdit = Note("", "test", 1)
        val expectedText = "test"

        NoteService.add(note)
        NoteService.edit(noteEdit)
        val actualText = NoteService.notes[0].text

        assertEquals(expectedText, actualText)
    }

    @Test
    fun readNote() {
        val expectedPost = NoteService.notes

        val actualPost = NoteService.read()

        assertEquals(expectedPost, actualPost)
    }

    @Test
    fun getById() {
        val note = Note("", "")
        NoteService.add(note)
        val expectedPostById = NoteService.notes[0]

        val actualPostById = NoteService.getById(1)

        assertEquals(expectedPostById, actualPostById)
    }

    @Test
    fun addComment() {
        val note = Note("", "")
        val comment = Comment(1, "test")

        val expectedCommentId: Long = 1
        NoteService.add(note)

        val actualCommentId = NoteService.addComment(comment)

        assertEquals(expectedCommentId, actualCommentId)
    }

    @Test
    fun deleteComment() {
        val note = Note("", "")
        val comment = Comment(1, "")
        val expectedDeletedPost = false

        NoteService.add(note)
        NoteService.addComment(comment)
        NoteService.deleteComment(1)
        val actualDeletedPost = NoteService.commentsNote[0].visibility

        assertEquals(expectedDeletedPost, actualDeletedPost)
    }

    @Test
    fun editComment() {
        val note = Note("", "")
        val comment = Comment(1, "")
        val commentEdit = Comment(1, "test")
        val expectedEditComment = "test"

        NoteService.add(note)
        NoteService.addComment(comment)
        NoteService.editComment(commentEdit)
        val actualComment = NoteService.commentsNote[0].message

        assertEquals(expectedEditComment, actualComment)
    }

    @Test
    fun readComments() {
        val expectedPost = NoteService.commentsNote

        val actualPost = NoteService.readComments()

        assertEquals(expectedPost, actualPost)
    }

    @Test
    fun restoreComment() {
        val note = Note("", "")
        val comment = Comment(1, "")
        val expectedDeletedPost = true

        NoteService.add(note)
        NoteService.addComment(comment)
        NoteService.deleteComment(1)
        NoteService.restoreComment(1)
        val actualDeletedPost = NoteService.commentsNote[0].visibility

        assertEquals(expectedDeletedPost, actualDeletedPost)
    }
}