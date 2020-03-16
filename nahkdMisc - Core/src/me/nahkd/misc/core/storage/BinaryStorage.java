package me.nahkd.misc.core.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import me.nahkd.misc.core.storage.types.CompactStringBinaryStorageType;
import me.nahkd.misc.core.storage.types.StringBinaryStorageType;

/**
 * Store data as binary
 * @author nahkd123
 *
 */
public class BinaryStorage {
	
	private static HashMap<Integer, BinaryStorageType<?>> types;
	public static HashMap<Integer, BinaryStorageType<?>> getTypes() {
		if (types == null) {
			types = new HashMap<Integer, BinaryStorageType<?>>();
			
			StringBinaryStorageType.INSTANCE.register();
			CompactStringBinaryStorageType.INSTANCE.register();
		}
		return types;
	}
	
	HashMap<String, BinaryStorageObject> tempStorage;
	
	public BinaryStorage() {
		tempStorage = new HashMap<String, BinaryStorageObject>();
	}
	
	public Object get(String key) {
		return tempStorage.get(key).obj;
	}
	public HashMap<String, BinaryStorageObject> getStorageMap() {
		return tempStorage;
	}
	
	/**
	 * This will load data from stream. Make sure to close stream after you've done with it.
	 * @param stream
	 */
	public BinaryStorage(InputStream stream) {
		this();
		
		try {
			int typeId;
			while ((typeId = stream.read()) != -1) {
				String name = CompactStringBinaryStorageType.INSTANCE.read(stream);
				Object value = BinaryStorage.getTypes().get(typeId).read(stream);
				tempStorage.put(name, new BinaryStorageObject(BinaryStorage.getTypes().get(typeId), value));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Write to output stream
	 * @param stream
	 * @throws IOException If it can't write to stream
	 */
	public void write(OutputStream stream) throws IOException {
		for (Entry<String, BinaryStorageObject> e : tempStorage.entrySet()) {
			stream.write(e.getValue().type.getId());
			CompactStringBinaryStorageType.INSTANCE.write(stream, e.getKey());
			e.getValue().type.unsafeWrite(stream, e.getValue().obj);
		}
	}
	
}
