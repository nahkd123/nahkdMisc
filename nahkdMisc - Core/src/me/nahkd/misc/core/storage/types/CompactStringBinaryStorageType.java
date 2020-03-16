package me.nahkd.misc.core.storage.types;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.nahkd.misc.core.storage.BinaryStorageType;

/**
 * Compact/ASCII String type
 * @author nahkd123
 *
 */
public class CompactStringBinaryStorageType implements BinaryStorageType<String> {

	public static final CompactStringBinaryStorageType INSTANCE = new CompactStringBinaryStorageType();
	
	@Override
	public void write(OutputStream stream, String val) throws IOException {
		char[] arr = val.toCharArray();
		for (char c : arr) stream.write(c);
		stream.write(0);
	}

	@Override
	public String read(InputStream stream) throws IOException {
		StringBuilder b = new StringBuilder();
		int c;
		while ((c = stream.read()) != 0) {
			b.append((char) c);
		}
		return b.toString();
	}

	@Override
	public int getId() {
		return 1;
	}
	
}
