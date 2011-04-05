
package novoda.lib.rest.net;

public interface SathVisitor {

    void start();

    void visitStartElement(String elementName);

    void visitElement(Object element);

    void visitEndElement();

    void visitStartArray(int index);

    void visitEndArray();

    void end();

    void addCallback(Callback callback);

    public interface Callback {
        public void onHit(SathVisitor visitor, Object value);
    }
}
