package org.tablehack.tablehackserver.db.entities;

import java.util.ArrayList;

public class CardTypeEntryList extends ArrayList<CardTypeEntry>{

    private static final long serialVersionUID = 1L;

    public CardTypeEntryList() {
    }
    public CardTypeEntryList(CardTypeEntry ... entries) {
        if (entries != null) {
            for (CardTypeEntry entry : entries) {
                add(entry);
            }
        }
    }
    
    
}
