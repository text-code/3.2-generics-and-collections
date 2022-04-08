object NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var counterId = 0

    fun add(note: Note) {
        counterId++
        val noteId = note.copy(id = counterId)
        notes.add(noteId)
    }

    fun createComment(comment: Comment) {
        for (note in notes) {
            if (note.id == comment.id && note.visibility) {
                comments.add(comment)
                return
            }
        }
        throw CommentOrNoteNotFoundException("Impossible to add a comment to a note that does not exist")
    }

    fun delete(noteId: Int) {
        for (note in notes) {
            val indexNote = notes.indexOf(note)
            if (note.id == noteId && note.visibility) {
                val delNote = note.copy(visibility = false)
                notes[indexNote] = delNote
                for (comment in comments) {
                    val indexComment = comments.indexOf(comment)
                    if (comment.id == noteId && comment.visibility) {
                        comments[indexComment] = comment.copy(visibility = false)
                    }
                }
                return
            }
        }
        throw CommentOrNoteNotFoundException("Note not founded")
    }

    fun deleteComment(commentId: Int) {
        for (comment in comments) {
            val indexComment = comments.indexOf(comment)
            if (comment.id == commentId && comment.visibility) {
                comments[indexComment] = comment.copy(visibility = false)
                return
            }
        }
        throw CommentOrNoteNotFoundException("Comment not founded")
    }

    fun edit(note: Note) {
        for (noteEdit in notes) {
            val indexNote = notes.indexOf(noteEdit)
            if (noteEdit.id == note.id) {
                if (noteEdit.visibility) {
                    notes[indexNote] = note
                    return
                }
                throw CommentOrNoteNotFoundException("Note was delete")
            }
        }
        throw CommentOrNoteNotFoundException("Note not found")
    }

    fun editComment(comment: Comment) {
        for (commentEdit in comments) {
            val indexComment = comments.indexOf(commentEdit)
            if (commentEdit.id == comment.id) {
                if (commentEdit.visibility) {
                    comments[indexComment] = comment
                    return
                }
                throw CommentOrNoteNotFoundException("Comment was delete")
            }
        }
        throw CommentOrNoteNotFoundException("Comment not founded")
    }

    fun get(): MutableList<Note> {
        val notDeleteNotes = mutableListOf<Note>()
        for (noteVisibility in notes) {
            if (noteVisibility.visibility) {
                notDeleteNotes += noteVisibility
            }
        }
        return notDeleteNotes
    }

    fun getById(noteId: Int): Note {
        for (note in notes) {
            val indexNote = notes.indexOf(note)
            if (note.id == noteId) return notes[indexNote]
        }
        throw CommentOrNoteNotFoundException("Not found note by id")
    }

    fun getComments(): MutableList<Comment> {
        val notDeleteComment = mutableListOf<Comment>()
        for (commentVisibility in comments) {
            if (commentVisibility.visibility) {
                notDeleteComment += commentVisibility
            }
        }
        return notDeleteComment
    }

    fun restoreComment(commentId: Int) {
        for (note in notes) {
            if (note.id == commentId && note.visibility) {
                for (comment in comments) {
                    val indexComment = comments.indexOf(comment)
                    if (comment.id == commentId && !comment.visibility) {
                        comments[indexComment] = comment.copy(visibility = true)
                        return
                    }
                }
            }
        }

        throw CommentOrNoteNotFoundException("There is no such comment among the delete")
    }
}