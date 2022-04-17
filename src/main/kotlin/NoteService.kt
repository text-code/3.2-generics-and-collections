object NoteService : OverallService<Note> {
    var notes = mutableListOf<Note>()
    var commentsNote = mutableListOf<Comment>()
    private var counterId: Long = 0

    override fun add(entity: Note): Long {
        counterId++
        val noteId = entity.copy(id = counterId)
        notes.add(noteId)
        return counterId
    }

    override fun delete(id: Long) {
        for ((index, note) in notes.withIndex()) {
            if (note.id == id && note.visibility) {
                val delNote = note.copy(visibility = false)
                notes[index] = delNote
                for ((index, comment) in commentsNote.withIndex()) {
                    if (comment.id == id && comment.visibility) {
                        commentsNote[index] = comment.copy(visibility = false)
                    }
                }
                return
            }
        }
        throw CommentNotePostNotFoundException("Note not founded")
    }

    override fun edit(entity: Note) {
        for ((index, noteEdit) in notes.withIndex()) {
            if (noteEdit.id == entity.id) {
                if (noteEdit.visibility) {
                    notes[index] = entity
                    return
                }
                throw CommentNotePostNotFoundException("Note was delete")
            }
        }
        throw CommentNotePostNotFoundException("Note not found")
    }

    override fun read(): List<Note> {
        return notes
    }

    override fun getById(id: Long): Note {
        for ((index, note) in notes.withIndex()) {
            if (note.id == id) return notes[index]
        }
        throw CommentNotePostNotFoundException("Note with this id was not found")
    }

    fun addComment(comment: Comment): Long {
        for (note in notes) {
            if (note.id == comment.id && note.visibility) {
                commentsNote += comment
                return comment.id
            }
        }
        throw CommentNotePostNotFoundException("Impossible to add a comment to a note that does not exist")
    }

    fun deleteComment(commentId: Long) {
        for ((index, comment) in commentsNote.withIndex()) {
            if (comment.id == commentId && comment.visibility) {
                commentsNote[index] = comment.copy(visibility = false)
                return
            }
        }
        throw CommentNotePostNotFoundException("Comment not founded")
    }

    fun editComment(comment: Comment) {
        for ((index, commentEdit) in commentsNote.withIndex()) {
            if (commentEdit.id == comment.id) {
                if (commentEdit.visibility) {
                    commentsNote[index] = comment
                    return
                }
                throw CommentNotePostNotFoundException("Comment was delete")
            }
        }
        throw CommentNotePostNotFoundException("Comment not founded")
    }

    fun readComments(): MutableList<Comment> {
        val notDeleteComment = mutableListOf<Comment>()
        for (commentVisibility in commentsNote) {
            if (commentVisibility.visibility) {
                notDeleteComment += commentVisibility
            }
        }
        return notDeleteComment
    }

    fun restoreComment(commentId: Long) {
        for (note in notes) {
            if (note.id == commentId && note.visibility) {
                for ((index, comment) in commentsNote.withIndex()) {
                    if (comment.id == commentId && !comment.visibility) {
                        commentsNote[index] = comment.copy(visibility = true)
                        return
                    }
                }
            }
        }
        throw CommentNotePostNotFoundException("There is no such comment among the delete")
    }

    fun clean() {
        notes = mutableListOf()
        commentsNote = mutableListOf()
        counterId = 0
    }
}