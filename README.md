bog
===

Builders of Object Graphs

Objectives

* The library supports the writing of builder classes that can be used to construct instances of some target class.

* If no properties are specified, the instance of the target class will be constructed entirely using default
values.

* Property values of the target class can be explicitly specified, in which case the specified value
 is assigned instead of the default value.

* Explicitly specified property values may be an instance of the property's type or instance of a
 builder of the property's type.

* Builders support property setting through both constructor parameters and setters.

* Builders support a two-phase approach to building the target instance, such that the instance can
 be constructed (phase 1) before all the property values have been configured in the builder.
 The remaining properties will be assigned to the instance in the second phase. (Note that only
 properties with setters can be assigned this way). This allows directly self-referencing and
 indirectly self-referencing instances to be built.

* Default values for properties can be configured in the builders. A default value picker strategy
 can be used to vary the default value. Using randomly chosen default values helps to prevent
 writing code that accidentally depends on a property value that has not been specified explicitly,
 which in the case of unit tests can lead to obscure and fragile tests.