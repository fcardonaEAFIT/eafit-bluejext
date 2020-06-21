package co.edu.eafit.dis.st0242.bluejext;

import bluej.extensions.BlueJ;
import bluej.extensions.Extension;
import bluej.extensions.ExtensionException;
import bluej.extensions.event.ApplicationEvent;
import bluej.extensions.event.ApplicationListener;
import bluej.extensions.event.PackageEvent;
import bluej.extensions.event.PackageListener;
import java.net.URL;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class SimpleExtension extends Extension
    implements PackageListener, ApplicationListener {

    private PrintWriter pw = null;
    public SimpleExtension() {
	super();
	try {
	    pw = new PrintWriter(new File("/tmp/log.txt"));
	    pw.println("Ready to start");
	}
	catch (FileNotFoundException fnfe) {
	    System.err.println(fnfe);
	}
    }
    /*
     * Application 
     */
    public void blueJReady(ApplicationEvent event) {
	pw.println("Event: " + event);
    }

    /*
     * When this method is called, the extension may start its work.
     */
    public void startup(BlueJ bluej) {
        // Listen for BlueJ events at the "package" level
        bluej.addPackageListener(this);
	bluej.addApplicationListener(this);
	System.out.println("Startup");
	pw.println("Startup");
	MenuBuilder mb = new MenuBuilder();
	bluej.setMenuGenerator(mb);
    }

    /*
     * A package has been opened. Print the name of the project it is part of.
     * System.out is redirected to the BlueJ debug log file.
     * The location of this file is given in the Help/About BlueJ dialog box.
     */
    public void packageOpened(PackageEvent ev) {
        try {
	    pw.println("Project " + ev.getPackage().getProject().getName()
			       + " opened.");
	}
        catch (ExtensionException e) {
	    pw.println("Project closed by BlueJ");
	}
    }

    /*
     * A package is closing.
     */
    public void packageClosing(PackageEvent ev) {
	pw.println("A package is openned");
    }

    /*
     * This method must decide if this Extension is compatible with the
     * current release of the BlueJ Extensions API
     */
    public boolean isCompatible() {
        return true;
    }

    /*
     * Returns the version number of this extension
     */
    public String getVersion () {
        return ("1.0.1");
    }

    /*
     * Returns the user-visible name of this extension
     */
    public String getName () {
        return ("EAFIT Simple Extension");
    }

    public void terminate() {
        pw.println ("Simple extension terminates");
	pw.close();
    }

    public String getDescription () {
        return ("A simple extension please work test 3");
    }

    /*
     * Returns a URL where you can find info on this extension.
     * The real problem is making sure that the link will still be alive
     * in three years...
     */
    public URL getURL ()
    {
        try {
	    return new URL("http://www.bluej.org/doc/writingextensions.html");
	}
        catch (Exception e) {
	    // The link is either dead or otherwise unreachable
	    System.out.println ("Simple extension: getURL: Exception="+e.getMessage());
	    return null;
	}
    }
}
