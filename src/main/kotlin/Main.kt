fun main() {
    NoteService.add(Note("1", "Test comment 1"))
    NoteService.add(Note("2", "Test comment 2"))
    NoteService.add(Note("3", "Test comment 3"))
    NoteService.edit(Note("3", "Test comment edit", 3))
//    NoteService.delete(2)

    NoteService.createComment(Comment(1, "Comment one the first note"))
    NoteService.createComment(Comment(2, "Comment one the second note"))
    NoteService.createComment(Comment(3, "Comment one the third note"))

    NoteService.deleteComment(2)
//    NoteService.restoreComment(2)

    val comment = NoteService.getComments()
    println(comment)

    val note = NoteService.get()
    println(note)
}