package persistence;

import java.io.Writer;

public interface Writable {
    void write(Writer writer);
}
