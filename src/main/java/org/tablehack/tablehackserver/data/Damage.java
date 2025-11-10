package org.tablehack.tablehackserver.data;

import java.util.List;

import lombok.Data;


@Data
public class Damage  {
	String physical;
	String poison;
	String fire;
	String cold;
	String electricity;
	String life;
	String energy;
	String fear;
	
	String group;
	String area;
	String cone;
	String range;
	String ray;
}
