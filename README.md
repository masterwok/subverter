<a href="https://jitpack.io/#masterwok/subverter"><img src="https://jitpack.io/v/masterwok/subverter.svg"/></a>

# About #

The Subverter library provides a simple way of converting between subtitle file types.

# Supported Types #
 - VTT
 - SRT
 
# Usage #

The Subverter interface can be used directly. For example:

```java
Subverter subverter = new Subverter();

subverter.convert("input.srt", "output.vtt");
```

The above snippet would read the input.srt file, convert the contents, and 
store the result in the output.vtt file. 

The converter that is used is deteremined
by the input file path. What conversion the converter applies is determined by the
output file path.


**OR**

Converters can be directly used to convert between subtitle types. For example:


```java
SrtConverter
    .getInstance()
    .convert("input.srt", "output.vtt");
```

# To Install #

```groovy
   repositories {
        jcenter()
        maven { url "https://jitpack.io" }
   }
   dependencies {
         compile 'com.github.masterwok:subverter:v.1.0.4'
   }
```

# Supporting Additional Types #

1. Add the subtitle type to SubType.
2. Implement the conversion on existing types.
3. Implement the Converter contract for the new type.
4. Update the Subverter class to recognize the new SubType and use
the new Converter implementation.


