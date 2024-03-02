package com.example.todo;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final List<Note> notes = new ArrayList<>();
    private long nextId = 1;

    public List<Note> listAll() {
        return notes;
    }

    public Note add(Note note) {
        note.setId(nextId++);
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        notes.removeIf(note -> note.getId() == id);
    }

    public void update(Note note) {
        for (int i = 0; i < notes.size(); i++) {
            Note existingNote = notes.get(i);
            if (existingNote.getId() == note.getId()) {
                existingNote.setTitle(note.getTitle());
                existingNote.setContent(note.getContent());
                return;
            }
        }
        throw new IllegalArgumentException("Note with id " + note.getId() + " not found");
    }

    public Note getById(long id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        throw new IllegalArgumentException("Note with id " + id + " not found");
    }
}