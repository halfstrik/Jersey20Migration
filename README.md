Jersey20Migration
=================

I have a Jersey 1.17.1 simple project.
The main goal is to serialize intities to JSON.
I use Jetty server. It starts from main method in launcher.Launcher class.

I also have one integration test with Jersey client for proving that server is working as I expected.

PROBLEM:
In branch jersey-2.0-migration I try to migrate to 2.0 Jersey.
Result is: server no longer working, test do not pass.
