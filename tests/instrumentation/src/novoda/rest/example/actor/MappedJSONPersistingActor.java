package novoda.rest.example.actor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import novoda.lib.rest.actor.JSONPersistingActor;
import novoda.lib.rest.marshaller.MarshallingException;
import android.content.ContentProviderOperation;
import android.net.Uri;

import com.google.gson.stream.JsonReader;

public abstract class MappedJSONPersistingActor extends JSONPersistingActor {

    @Override
    public ArrayList<ContentProviderOperation> marshall(JsonReader content) throws IOException,
            MarshallingException {
        return null;
    }
    
    protected abstract Map<Uri, String> getMapper();
}
