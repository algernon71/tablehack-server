package org.tablehack.tablehackserver.db.entities;

import java.util.ArrayList;

public class CardTypeFieldList extends ArrayList<CardTypeField>{

    private static final long serialVersionUID = 1L;

    public CardTypeFieldList() {
    }
    public CardTypeFieldList(CardTypeField.CardTypeFieldBuilder ... fields) {
        if (fields != null) {
            for (CardTypeField.CardTypeFieldBuilder field : fields) {
                add(field.build());
            }
        }
    }
    
    
}
