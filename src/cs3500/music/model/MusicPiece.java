package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Created by natdempk on 11/3/15.
 */

public class MusicPiece {
    //ArrayList<MusicNote> notes;

    //ArrayList<Integer> pitchIds;
    TreeSet<Integer> pitchIds;
    // mapping start time to length
    TreeMap<Integer, ArrayList<MusicNote>> notes;
    int beatsPerMeasure;
    int tempo;

    /**
     * represents a piece of music with a collection of notes
     * @param beatsPerMeasure number of beats in a measure for this piece of music
     */
    MusicPiece(int beatsPerMeasure, int tempo) {

        if (beatsPerMeasure > 0) {
            this.beatsPerMeasure = beatsPerMeasure;
        } else {
            throw new IllegalArgumentException("beatsPerMeasure must be > 0.");
        }

        if (tempo > 0) {
            this.tempo = tempo;
        } else {
            throw new IllegalArgumentException("tempo must be > 0.");
        }

        //this.pitchIds = new ArrayList<>();
        // TODO: add comparator to make this work better
        this.pitchIds = new TreeSet<>();
        this.notes = new TreeMap<>();

        //this.notes = new ArrayList<MusicNote>();
    }

    public ArrayList<MusicNote> getNotesStartingOnBeat(Integer beat) {
        return this.notes.get(beat);
    }

    public MusicPiece() {
        this(4, 100); // default to 4 beats per measure
    }

    /**
     * adds a note to the piece of music
     * @param note note to be added to the music
     */
    public void addNote(MusicNote note) {

        if (note.getNumericNote() > 127) {
            throw new IllegalArgumentException("Note must have a lower pitch between 0 and 127");
        }
        // usersByCountry.computeIfAbsent(user.getCountry(), v -> new ArrayList<>()).add(user);
        ArrayList<MusicNote> startNotes = this.notes.get(note.getStartBeat());
        if (startNotes == null) {
            startNotes = new ArrayList<>();
        }
        // TODO: deduplicate list of notes
        startNotes.add(note);
        this.pitchIds.add(note.getNumericNote());
        this.notes.put(note.getStartBeat(), startNotes);
    }

    public void deleteNote(MusicNote note) {
        ArrayList<MusicNote> startNotes = this.notes.get(note.startBeat);
        if (startNotes != null) {
            startNotes.remove(note);
        }
    }

    /**
     * renders the musical piece as text, also outputs the render it to the console
     * @return String representation of the musical piece
     */
    /*
    public String render() {
        String header = "";
        String lines = "";
        Collections.sort(this.notes, new CompStartBeat());
        int lastBeat = Collections.max(this.notes, new CompEndBeat()).endBeat();
        int numDisplayWidth = String.valueOf(lastBeat).length();

        header += String.format("%" + (numDisplayWidth + 1) + "s", "");

        // fixed number of displayable notes
        HashSet<MusicNote> displayNotes = new HashSet<MusicNote>(231);

        // add notes to set to see what notes we actually need to display
        for (MusicNote note : this.notes) {
            displayNotes.add(note);
        }

        for (MusicNote note : displayNotes) {
            header += String.format("%-4s ", note.noteName());
        }
        header += "\n";

        int curBeat = 0;
        while (curBeat <= lastBeat) {
            lines += String.format("%-" + numDisplayWidth + "d ", curBeat);
            for (MusicNote displayNote : displayNotes) {
                boolean found = false;
                for (MusicNote note : this.notes) {
                    if (note.getNumericNote() == displayNote.getNumericNote()
                            && note.activeOnBeat(curBeat)) {
                        if (note.startBeat == curBeat) {
                            lines += "X    ";
                        } else {
                            lines += "|    ";
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    lines += "     ";
                }
            }
            lines += "\n";
            curBeat++;
        }

        System.out.print(header + lines);
        return header + lines;
    }
    */
}