package org.tablehack.tablehackserver.controllers.dto;

import org.tablehack.tablehackserver.db.entities.ResourceEntry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceInfo {
	private String path;

	private String type;
	private String mimeType;
	private int size;
	
	public
	ResourceInfo(ResourceEntry resource) {
		this.path = resource.getPath();
		this.type = resource.getType();
		this.mimeType = resource.getMimeType();
		this.size = resource.getData().length;
	}
}
