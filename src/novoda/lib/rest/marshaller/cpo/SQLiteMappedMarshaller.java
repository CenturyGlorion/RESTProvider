
package novoda.lib.rest.marshaller.cpo;

import java.util.Collection;

import novoda.lib.rest.marshaller.IContentProviderOperationMarshaller;
import novoda.lib.rest.uri.UriMapper;

public interface SQLiteMappedMarshaller<From> extends IContentProviderOperationMarshaller<From> {
    UriMapper<Collection<String>> getUriAsXPath();
}
