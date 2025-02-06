#!/usr/bin/env bash

# Create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
    mkdir ../bin
fi

# Delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

# Clear the contents of LeChatBot.txt (or create it if it doesn't exist)
if [ -e "../data/LeChatBot.txt" ]; then
    > "../data/LeChatBot.txt"  # Truncate file to 0 bytes
else
    mkdir -p "../data"  # Ensure the data folder exists
    touch "../data/LeChatBot.txt"  # Create an empty file
fi

# Compile the code into the bin folder, terminate if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin $(find ../src/main/java -name "*.java"); then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
cd ..
java -classpath bin lechatbot.LeChatBot < text-ui-test/input.txt > text-ui-test/ACTUAL.TXT
cd text-ui-test

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
