import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiseTest {

    @Test
    fun delete_notDeleteIDNotEquals() {
        val service = NoteServise()
        val note1 = Note(id = 3, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 5, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val result = service.delete(5)
        assertFalse(result)
    }

    @Test
    fun delete_notDeleteIsDeletedTrue() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = true)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val result = service.delete(1)
        assertFalse(result)
    }

    @Test
    fun delete_itIsOK() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val result = service.delete(1)
        assertTrue(result)
    }

    @Test
    fun edit_notEditIDNotEquals() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val control = Note(id = 9, title = "", text = "", isDeleted = false)
        val result = service.edit(control)
        assertFalse(result)
    }

    @Test
    fun edit_notEditIsDeletedTrue() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = true)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val control = Note(id = 1, title = "", text = "", isDeleted = false)
        val result = service.edit(control)
        assertFalse(result)
    }

    @Test
    fun edit_itIsOK() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val control = Note(id = 1, title = "a", text = "b", isDeleted = false)
        val result = service.edit(control)
        assertTrue(result)
    }

    @Test(expected = CommentExeption::class)
    fun getByID_notListIDNotEquals() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        service.getByID(4)
    }

    @Test(expected = CommentExeption::class)
    fun getByID_notListIsDeletedTrue() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = true)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        service.getByID(1)
    }

    @Test
    fun getByID_itIsOK() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        service.getByID(1)
    }
}

class CommentServiceTest {


    @Test(expected = CommentExeption::class)
    fun add_notAddIDNotEquals() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val control = Comment(id = 1, NoteId = 6, message = "b", isDeleted = false)
        service.CommentService().add(control)
    }

    @Test(expected = CommentExeption::class)
    fun add_notAddNoteIsDeletedTrue() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = true)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val control = Comment(id = 1, NoteId = 1, message = "b", isDeleted = false)
        service.CommentService().add(control)
    }

    @Test(expected = CommentExeption::class)
    fun add_notAddIsDeletedTrue() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val control = Comment(id = 1, NoteId = 1, message = "", isDeleted = true)
        service.CommentService().add(control)
    }

    @Test
    fun add_itIsOK() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val control = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        service.CommentService().add(control)
    }

    @Test(expected = CommentExeption::class)
    fun delete_notDeleteIDNotEquals() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().delete(5)
    }

    @Test(expected = CommentExeption::class)
    fun delete_notDeleteIsDeletedTrue() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 2, NoteId = 1, message = "", isDeleted = true)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().delete(2)
    }

    @Test
    fun delete_itIsOK() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().delete(2)
    }

    @Test(expected = CommentExeption::class)
    fun edit_notEditIDNotEquals() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        val control = Comment(id = 49, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().edit(control)
    }

    @Test(expected = CommentExeption::class)
    fun edit_notEditIsDeletedTrue() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = true)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        val control = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().edit(control)
    }

    @Test
    fun edit_itIsOK() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        val control = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        service.CommentService().edit(control)
    }

    @Test(expected = CommentExeption::class)
    fun getByID_notGetIDNotEquals() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        service.add(note1)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().getByID(5)
    }

    @Test(expected = CommentExeption::class)
    fun getByID_notGetIsDeletedTrue() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        service.add(note1)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = true)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = true)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().getByID(1)
    }

    @Test
    fun getByID_itIsOK() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        service.add(note1)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().getByID(1)
    }

    @Test(expected = CommentExeption::class)
    fun restore_notRestoreIDNotEquals() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().delete(1)
        service.CommentService().restore(7)
    }

    @Test(expected = CommentExeption::class)
    fun restore_notRestoreIsDeletedFalse() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().restore(1)
    }

    @Test
    fun restore_itIsOK() {
        val service = NoteServise()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, NoteId = 1, message = "", isDeleted = false)
        val comment2 = Comment(id = 1, NoteId = 1, message = "c", isDeleted = false)
        service.CommentService().add(comment1)
        service.CommentService().add(comment2)
        service.CommentService().delete(1)
        service.CommentService().restore(1)
    }
}