package org.tablehack.tablehackserver.db.entities;

import java.util.ArrayList;

public class CardTypeEntryList extends ArrayList<CardTypeEntry>{

    private static final long serialVersionUID = 1L;

    public CardTypeEntryList() {
    }
    public CardTypeEntryList(CardTypeEntry.CardTypeEntryBuilder ... entries) {
        if (entries != null) {
            for (CardTypeEntry.CardTypeEntryBuilder entry : entries) {
                add(entry.build());
            }
        }
    }
    
    public void add(CardTypeEntry.CardTypeEntryBuilder ... entries) {
        if (entries != null) {
            for (CardTypeEntry.CardTypeEntryBuilder entry : entries) {
                add(entry.build());
            }
        }
    }
    
}
