Scheme Pretty Printer in Java [![Build Status](https://travis-ci.com/svondenstein/spp.svg?branch=master)](https://travis-ci.com/svondenstein/spp)
==================

## About This Program

This is a Pretty Printer for Scheme implemented in Java. The program takes Scheme statements in via the command line and outputs 'pretty printed' Scheme statements.

## Running Locally

### Pre-compiled

1. Navigate to the _releases_ section of this repository and download the `.jar` file
2. Run the jar file: `java -jar SPP.jar`

### Compiling from source

1. Clone locally via GitHub Desktop, or via CLI: 
`git clone https://github.com/svondenstein/SchemePrettyPrinter.git`
2. Compile the source files in src/: `javac *.java`
  -- optionally `javac -d ../out *.java` to preserve a clean `src` directory
3. Run Main: `java Main`
## Usage

1. Enter a valid Scheme statement via the command line.
2. The program will output a 'pretty printed' Scheme statement which matches the inputted statement.

## Licensing

The code in the _runtests_ file is not mine, it was written by the instructor of CSC 4101, Gerald Baumgartner. This is also true for all files in the _tests/_ and _soln/_ folders.
