# What is it?

The RESTProvider aims to make it easy to access REST service (or any online data format through mainly HTTP calls) and cache it locally into a SQLite database. It permits offline usage and some sort of syncing mechanism against a snapshot of user data.


# Motivation

The need for a framework to access the net is more apparent on mobile devices due to the instability of network connections and its cost. Accessing online information in an offline manner is essential for best user experience. Similarly, offline actions can be pushed back to online services when a connection becomes available.

# Structure

The RESTProvider relies on 2 submodules:
  > HttpService - making HTTP calls via a Service asynchronously
  > SQLiteProvider - helper project for various SQLite methods behind a ContentProvider
  
The idea is that the HttpService provide the framework for calling online data while the SQLiteProvider populate the data from the previous call into SQLite. The RESTProvider itself maps the calls to corresponding tables. For more information, visit the 2 projects individually.

# How to install

TODO

# Simple usage

TODO

# License

Otherwise noted, RESTProvider is free and open source and may be used under the terms of the []Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
