package persistence;

import java.io.IOException;
import java.io.Writer;

public interface Writable {
    void write(Writer writer) throws IOException;
}
