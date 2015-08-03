# df-newextension-archetype

The df-newextension-archetype project contains the source for creating a Maven archetype for generating new Actian DataFlow extension projects.
These projects will support for new DataFlow operators and functions as well as support for KNIME node plug-ins for the open source KNIME data 
analytics and mining platform.

## Building

The Maven archetype is built using [Apache Maven 3.0.5 or later](http://maven.apache.org/).

To build, run:

    mvn clean install
    
## Creating a new extension project using Maven

To generate a new extension project in interactive mode run:

    mvn archetype:generate -Dfilter=com.actian.services:newextension-archetype
    
You will be prompted for a **groupId**.  This **groupId** will be used generate package names, so it should be a prefix in
reverse URI format (e.g. com.mycompany.mydepartment).  You will also be prompted for an **artifactId**.   This is used as the artifactId
for the top level POM and is used to generate package names, directory names and file names.  It should be a simple
name in lower case that describes the project well (e.g. timeseries for a set of time series analysis components).

Maven will also prompt for a **version** number.   This is currently not used as it doesn't work well with the OSGi artifacts
used for KNIME.  A default version of 1.0.0-SNAPSHOT will be used instead.  The version can be changed easily after
the project is created.

The value used for **package** property should be the same as specified for the **groupId**.

The **include-function-support** property controls whether the archetype generates code with the sample custom
function code enabled or disabled.  When this is has a value of **false** (default) the custom function code is
commented out. 

```

server:projects myuser$ mvn archetype:generate -Dfilter=com.actian.services:newextension-archetype
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=512M; support was removed in 8.0
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] >>> maven-archetype-plugin:2.2:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO]
[INFO] <<< maven-archetype-plugin:2.2:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO]
[INFO] --- maven-archetype-plugin:2.2:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
Choose archetype:
1: local -> com.actian.services.dataflow:newextension-archetype (newextension-archetype)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 1
Define value for property 'groupId': : com.actian.services
Define value for property 'artifactId': : myoperator
Define value for property 'version':  1.0-SNAPSHOT: : 1.0.0-SNAPSHOT
Define value for property 'package':  com.actian.services: :
[INFO] Using property: include-function-support = false
Confirm properties configuration:
groupId: com.actian.services
artifactId: myoperator
version: 1.0.0-SNAPSHOT
package: com.actian.services
include-function-support: false
 Y: : Y
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: newextension-archetype:1.0.0-SNAPSHOT
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: com.actian.services
[INFO] Parameter: artifactId, Value: myoperator
[INFO] Parameter: version, Value: 1.0.0-SNAPSHOT
[INFO] Parameter: package, Value: com.actian.services
[INFO] Parameter: packageInPathFormat, Value: com/actian/services
[INFO] Parameter: include-function-support, Value: false
[INFO] Parameter: package, Value: com.actian.services
[INFO] Parameter: version, Value: 1.0.0-SNAPSHOT
[INFO] Parameter: groupId, Value: com.actian.services
[INFO] Parameter: artifactId, Value: myoperator
[INFO] Parent element not overwritten in /Users/myuser/projects/myoperator/myoperator-dataflow-dependencies/pom.xml
[INFO] Parent element not overwritten in /Users/myuser/projects/myoperator/myoperator-dataflow-extensions/pom.xml
[INFO] Parent element not overwritten in /Users/myuser/projects/myoperator/myoperator-knime-extensions-feature/pom.xml
[INFO] Parent element not overwritten in /Users/myuser/projects/myoperator/myoperator-knime-extensions-nodes/pom.xml
[INFO] Parent element not overwritten in /Users/myuser/projects/myoperator/myoperator-knime-extensions-functions/pom.xml
[INFO] Parent element not overwritten in /Users/myuser/projects/myoperator/myoperator-knime-extensions-update-site/pom.xml
[INFO] project created from Archetype in dir: /Users/myuser/projects/myoperator
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 01:04 min
[INFO] Finished at: 2015-08-02T14:29:47-05:00
[INFO] Final Memory: 13M/220M
[INFO] ------------------------------------------------------------------------

```


