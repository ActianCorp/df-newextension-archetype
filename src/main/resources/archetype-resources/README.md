# ${artifactId}

${artifactId} description

## Configuration

Before building ${artifactId} you need to define the following environment variables to point to the local DataFlow update site [dataflow-p2-site](https://github.com/ActianCorp/dataflow-p2-site) root directory and the DataFlow version.

    export DATAFLOW_REPO_HOME=/Users/myuser/dataflow-p2-site
    export DATAFLOW_VER=6.5.2.112

## Building

The update site is built using [Apache Maven 3.0.5 or later](http://maven.apache.org/).

To build, run:

    mvn clean install
    
You can update the version number by running

    mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=version
    
where version is of the form x.y.z or x.y.z-SNAPSHOT.

## Using ${artifactId} with the DataFlow Engine

The build generates a JAR file in the target directory under ${artifactId}-dataflow-extensions with a name similar to 

    ${artifactId}-dataflow-extensions-1.y.z.jar

which can be included on the classpath when using the DataFlow engine.

## Installing the ${artifactId} plug-in in KNIME

The build also produces a ZIP file which can be used as an archive file with the KNIME 'Help/Install New Software...' dialog.
The ZIP file can be found in the target directory under ${artifactId}-knime-extensions-update-site and with a name like 


    com.actian.services.knime.${artifactId}.update-1.y.z.zip





