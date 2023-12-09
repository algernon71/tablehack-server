package org.tablehack.tablehackserver.db.entities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.tablehack.tablehackserver.db.JSONData;
import org.tablehack.tablehackserver.db.JSONDataConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resources")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceEntry implements Resource {
	@Id
	private String path;

	private String type;
	private String mimeType;
	
	@Lob
	@Column(length = 10000000)
	private byte data[];

	public
	Resource toResource() {
		return new ByteArrayResource(data);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(data);
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public URL getURL() throws IOException {
		return null;
	}

	@Override
	public URI getURI() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long contentLength() throws IOException {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public long lastModified() throws IOException {
		return 0;
	}

	@Override
	public Resource createRelative(String relativePath) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFilename() {
		return path;
	}

	@Override
	public String getDescription() {
		return path;
	}
	
}
