package org.tablehack.tablehackserver.data.monsters;

import java.awt.Desktop.Action;

import org.tablehack.tablehackserver.data.CardPull;

import lombok.Data;

@Data
public class DeathData {
	
	CardPull cardPull;
	Action action;
}
