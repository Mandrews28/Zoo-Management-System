# sd2-pr2-Mandrews28

To load the Zoo Management System, you need to set up both the Front-End (FE) and the Back-End (BE).

In the command line, go to the BE. Run:
''' mvn clean install '''
to create the jar file. Then to start the BE application (it is configured to use localhost:8080):
'''java -jar target/AP-Assignment-<version>.jar '''

If you don't have npm configured, get the latest version from here: https://www.npmjs.com/.
To start the FE, go to the command line and run:
''' npm start '''
The FE is run on localhost:3000 and this should load automatically once you run the start command.
With both the BE and FE running, you should now be able to see the 'Interactive Zoo Management System' app!
