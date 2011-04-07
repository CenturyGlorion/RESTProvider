
package novoda.lib.rest.net;

import java.io.IOException;
import java.io.StringReader;

import novoda.lib.rest.net.SathVisitor.Callback;
import android.test.AndroidTestCase;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class SathTest extends AndroidTestCase {

    private int hits = 0;

    @Override
    protected void setUp() throws Exception {
        hits = 0;
        super.setUp();
    }

    public void testShouldThrowExceptionIfStringStartsWithBackSlash() throws Exception {
        try {
            new Sath("/s");
            fail();
        } catch (SathFormatException e) {
            assertTrue(true);
        }
    }

    public void testSimpleJsonParsing() throws Exception {
        assertValueIsFound("{\"start\": \"value\"}", "start", "value");
    }
    
    public void testNoHit() throws Exception {
        assertValueIsFound("{\"nohit\": \"value\"}", "start");
    }

    public void testArrayParsing() throws Exception {
        assertValueIsFound("{\"start\":[{\"get\": \"value\" }]}", "start/[#]/get", "value");
    }

    public void testArrayParsingWith2Values() throws Exception {
        assertValueIsFound("{\"start\":[{\"get\": \"value1\" }, {\"get\": \"value2\" }]}",
                "start/[#]/get", "value1", "value2");
    }

    private void assertValueIsFound(String json, String sath, final String... values)
            throws IOException {
        Sath s = new Sath(sath);
        s.addCallback(new Callback() {
            @Override
            public void onHit(SathVisitor visitor, Object o) {
                assertEquals(values[0], o);
                ++hits;
            }
        });
        executeParser(json, s);
        assertEquals("stack should be empty if hit and the equal in lenght " + s.toString(),
                values.length, hits);
    }

    private void executeParser(String json, SathVisitor visitor) throws IOException {
        JsonReader reader = new JsonReader(new StringReader(json));
        boolean isStarted = true;
        int i = 0;
        OUT: while (true) {
            JsonToken token = reader.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    reader.beginArray();
                    visitor.visitStartArray(i++);
                    break;
                case END_ARRAY:
                    reader.endArray();
                    visitor.visitEndArray();
                    break;
                case BEGIN_OBJECT:
                    reader.beginObject();
                    if (isStarted) {
                        visitor.start();
                        isStarted = false;
                    }
                    break;
                case END_OBJECT:
                    reader.endObject();
                    visitor.visitEndElement();
                    break;
                case NAME:
                    visitor.visitStartElement(reader.nextName());
                    break;
                case STRING:
                    visitor.visitElement(reader.nextString());
                    break;
                case BOOLEAN:
                    visitor.visitElement(reader.nextBoolean());
                    break;
                case NULL:
                    visitor.visitElement(null);
                    break;
                case NUMBER:
                    visitor.visitElement(reader.nextDouble());
                    break;
                case END_DOCUMENT:
                    break OUT;
            }
        }
    }
}
