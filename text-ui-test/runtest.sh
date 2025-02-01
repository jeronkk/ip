#!/usr/bin/env bash

# Create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
    mkdir ../bin
fi

# Delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

# Compile the code into the bin folder, terminate if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath ../bin LebronJames < input.txt > ACTUAL.TXT

dos2unix ACTUAL.TXT

# Compare the output to the expected output (EXPECTED.TXT)
diff -w ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
