
package novoda.lib.rest.actor;

import java.io.IOException;

import novoda.lib.rest.marshaller.MarshallingException;
import novoda.lib.rest.marshaller.net.HttpResponseMarshaller;

import org.apache.http.HttpResponse;

import com.novoda.lib.httpservice.actor.Actor;

public abstract class TypedActor<To, M extends HttpResponseMarshaller<To>> extends Actor {
    
    @Override
    public void onResponseReceived(HttpResponse httpResponse) {
        try {
            onResponseReceived(getMarshaller().marshall(httpResponse));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MarshallingException e) {
            e.printStackTrace();
        }
    }

    protected abstract M getMarshaller();

    public void onResponseReceived(To data) {
    }
}
