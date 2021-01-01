See https://bugs.eclipse.org/bugs/show_bug.cgi?id=471114
and https://bugs.eclipse.org/bugs/show_bug.cgi?id=522565
and https://bugs.eclipse.org/bugs/show_bug.cgi?id=543870
and https://bugs.eclipse.org/bugs/show_bug.cgi?id=570012

NB. The tweaks here provide an enhanced EMF genmodel capability when /org.eclipse.ocl.examples.build/templates
is in use as a genmodel templateDirectory. The JET is compiled so dynamicTemplates are not required, however
the builder doesn't understand the dependencies so deleting the generated *.java may make an obscure problem go away.

The org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreGeneratorAdapterFactory provides orthogonal capabilities;
reifying OCL bodies as inline Java. OCLinEcoreGeneratorAdapterFactory is always in use within
Eclipse as a consequence of a plugin registration, and selectively in use if manually registered standalone.

AdaptorFactoryClass.javajet

- Bug 485089: add missing @Nullable to class template parameter 

Class.javajet

- Bug 471114 expose xxx_FEATURE_COUNT/xxx_OPERATION_COUNT as public Impl API
- Bug 471114 symbolic references to feature/operation-ids replaced by their integer values/expressions (beware some need parentheses)

FactoryClass.javajet

- Bug 471114 symbolic references to classifier-ids replaced by their integer values

PackageClass.javajet

- Bug 471114 classifier-ids no longer defined as public API unless "Generate Classifier ints" GenAnnotation true.
- Bug 471114 feature/operation-ids no longer defined as public API
- Bug 471114 symbolic references to classifier/feature/operation-ids replaced by their integer values

SwitchClass.javajet

- Bug 485089: add missing @Nullable to class template parameter 
- Bug 471114 symbolic references to classifier-ids replaced by their integer values.

ValidatorClass.javajet

- Bug 471114 symbolic references to classifier-ids replaced by their integer values.

