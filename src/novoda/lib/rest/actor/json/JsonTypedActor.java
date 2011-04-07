
package novoda.lib.rest.actor.json;

import com.google.gson.stream.JsonReader;

import novoda.lib.rest.actor.TypedActor;
import novoda.lib.rest.marshaller.net.JsonMarshaller;

public class JsonTypedActor extends TypedActor<JsonReader, JsonMarshaller> {
    @Override
    protected JsonMarshaller getMarshaller() {
        return new JsonMarshaller();
    }
}
