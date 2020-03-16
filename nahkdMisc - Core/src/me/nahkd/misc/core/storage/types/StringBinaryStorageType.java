package me.nahkd.misc.core.storage.types;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.nahkd.misc.core.storage.BinaryStorageType;

public class StringBinaryStorageType implements BinaryStorageType<String> {

	public static final StringBinaryStorageType INSTANCE = new StringBinaryStorageType();
	
	@Override
	public void write(OutputStream stream, String val) throws IOException {
		char[] arr = val.toCharArray();
		for (char c : arr) stream.write(new byte[] {
				(byte) ((c >> 24) | 0b10000000),
				(byte) ((c - ((c >> 24) << 24)) >> 16),
				(byte) ((c - ((c >> 16) << 16)) >> 8),
				(byte) (c - ((c >> 8) << 8))
		});
		stream.write(0);
		// 1_______ ________ ________ ________
		// ^
		// |
		// +--------- Next character
		// The char value is from 0 to 31-bit max limit
	}

	@Override
	public String read(InputStream stream) throws IOException {
		StringBuilder b = new StringBuilder();
		int c;
		while ((c = stream.read()) != 0b00000000) {
			char cc = (char) (((c ^ 0b10000000) << 24) + (stream.read() << 16) + (stream.read() << 8) + stream.read());
			b.append(cc);
		}
		return b.toString();
	}

	@Override
	public int getId() {
		return 0;
	}
	
}
