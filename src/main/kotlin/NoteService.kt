class NoteServise : CrudServise<Note> {

    private val noteList = mutableListOf<Note>()
    private val commentList = mutableListOf<Comment>()

    private var nextNoteID = 1
    private var nextCommentID = 1

    override fun add(element: Note): Note {
        val newNote = element.copy(id = nextNoteID++)
        noteList.add(newNote)
        return noteList.last()
    }

    override fun delete(id: Int): Boolean {
        for ((index, value) in noteList.withIndex()) {
            if (id == value.id && !value.isDeleted) {
                noteList[index] = value.copy(isDeleted = true)
                return true
            }
        }
        return false
    }

    override fun edit(element: Note): Boolean {
        for ((index, value) in noteList.withIndex()) {
            if (element.id == value.id && !value.isDeleted) {
                noteList[index] = value.copy(
                    title = element.title,
                    text = element.text
                )
                return true
            }
        }
        return false
    }

    override fun getByID(id: Int): Note {
        val listByID = mutableListOf<Note>()
        for (note in noteList) {
            if (id == note.id && !note.isDeleted)
                listByID.add(note)
        }
        return if (listByID.isNotEmpty()) listByID.last() else throw CommentExeption("По данному ID записей не найдено")
    }

    override fun restore(id: Int): Boolean {
        throw CommentExeption("Восстановление записей невозможно")
    }

    inner class CommentService : CrudServise<Comment> {

        override fun add(element: Comment): Comment {
            for ((index, value) in noteList.withIndex()) {
                when {
                    value.id == element.NoteId && !value.isDeleted && !element.isDeleted -> {
                        val newComment = element.copy(id = nextCommentID++)
                        commentList.add(newComment)
                        break
                    }
                    index < noteList.size - 1 -> continue
                    else -> break
                }
            }
            return if (commentList.isNotEmpty()) commentList.last() else throw CommentExeption("Невозможно добавить комментарий к заметке")
        }

        override fun delete(id: Int): Boolean {
            for ((index, value) in commentList.withIndex()) {
                when {
                    id != value.id && (index < commentList.size - 1) -> continue
                    id == value.id && !value.isDeleted -> {
                        commentList[index] = value.copy(isDeleted = true)
                        return true
                    }
                    else -> throw CommentExeption("Этот комментарий уже удален, удаление невозможно")
                }
            }
            return false
        }

        override fun edit(element: Comment): Boolean {
            for ((index, value) in commentList.withIndex()) {
                when {
                    element.id != value.id && (index < commentList.size - 1) -> continue
                    element.id == value.id && !value.isDeleted -> {
                        commentList[index] = value.copy(message = element.message)
                        return true
                    }
                    else -> throw CommentExeption("Этот комментарий уже удален,редактирование невозможно")
                }
            }
            return false
        }

        override fun getByID(id: Int): Comment {
            val listByID = mutableListOf<Comment>()
            for (comment in commentList) {
                if (id == comment.id && !comment.isDeleted)
                    listByID.add(comment)
            }
            return if (listByID.isNotEmpty()) listByID.last() else throw CommentExeption("По данному ID комментариев не найдено")
        }

        override fun restore(id: Int): Boolean {
            for ((index, value) in commentList.withIndex()) {
                when {
                    id != value.id && (index < commentList.size - 1) -> continue
                    id == value.id && value.isDeleted -> {
                        commentList[index] = value.copy(isDeleted = false)
                        return true
                    }
                    else -> throw CommentExeption("Невозможно восстановить неудаленный комментарий")
                }
            }
            return false
        }

    }
}