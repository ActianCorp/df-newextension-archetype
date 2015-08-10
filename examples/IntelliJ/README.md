# Using the new extension archetype with IntelliJ

IntelliJ makes it easy to use archetypes for creating new projects.   Once you have built the archetype and installed
it in your local Maven repository you can use the following steps to use it from IntelliJ.

## Adding the archetype to the IntelliJ Catalog

The first thing you need to do to use the archetype from IntelliJ is to add it to IntelliJ's catalog of Maven archetypes.
This done with the 'File/New/Project...' wizard dialog.  Select Maven as the project type and then press the 'Add Archetype...'
button.


![Add Archetype](https://raw.githubusercontent.com/ActianCorp/df-newextension-archetype/master/examples/IntelliJ/step1.png)

For the 'Add Archetype' dialog enter com.actian.services.dataflow for the GroupId and newextension-archetype for the
GroupId.  For the version use the version number used to build the archetype (e.g. 1.0.0-SNAPSHOT).  Assuming
you have installed the archetype in the local Maven repository there is no need to specify a repository.

## Using the archetype from IntelliJ

Once the archetype has been registered with the catalog you can use it from the 'File/New/Project...' wizard.

Select Maven as the new project type and then check the 'Create from archetype' checkbox.   You then select the
newextension-archetype from the archetype list and press 'Next'.

![Create from Archetype](https://raw.githubusercontent.com/ActianCorp/df-newextension-archetype/master/examples/IntelliJ/step2.png)

The next panel of the 'New Project' wizard allows you to Maven groupId and artifactId for you new project.   It is best
to use a 3 or 4 part reverse URI prefix with the organization type, name, and division (e.g. com.acme.bricks) for the
groupId.  The artifactId should be a simple lower case simple name.   Both of these will be used to generate various
parts of the new project (e.g. package names, OSGi bundles).  The version field is ignored and the version used for
the generated project will always be 1.0.0-SNAPSHOT.  The generated README.md will have instructions for how to change
the project version using Maven.

![New Project](https://raw.githubusercontent.com/ActianCorp/df-newextension-archetype/master/examples/IntelliJ/step3.png)

The next panel allows you to review and edit the new property settings.  This is where you can specify the values for
optional properties.  For example you can set the 'include-function-support' property if you want to enable support
for custom functions.

![Add Maven Property](https://raw.githubusercontent.com/ActianCorp/df-newextension-archetype/master/examples/IntelliJ/step4.png)

Press 'Next' after you are finished with the property settings.

![New Project 2](https://raw.githubusercontent.com/ActianCorp/df-newextension-archetype/master/examples/IntelliJ/step5.png)

The last panel allows you to pick a project name and location.

![New Project 3](https://raw.githubusercontent.com/ActianCorp/df-newextension-archetype/master/examples/IntelliJ/step6.png)

After you press the 'Finish' button the archetype will generate the new project.

![Generated Project](https://raw.githubusercontent.com/ActianCorp/df-newextension-archetype/master/examples/IntelliJ/step7.png)

At this point you should have a complete buildable project with a sample DataFlow operator, called NewOperator, and a
sample KNIME node.   These can be renamed and modified to create your own custom operators.