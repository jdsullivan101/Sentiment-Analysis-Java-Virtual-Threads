# Sentiment-Analysis-Java-Virtual-Threads


@author: John Sullivan G00425758
@version Java-21

Description
This is a menu driven Java application that performs sentiment analysis on a
directory of tweets to determine the score of each word in the tweet. Based on the
score of the words, the sentiment of the user can be determined. The program uses
virtual threads to parse the files, the lexicon and to execute analysis on the tweets.

To Run
Open the terminal/console at the .jar file directory:
java –enable-preview -cp .:./oop.jar ie.atu.sw.Runner. The program uses preview
features in virtual threads so this needs to be enabled with the command above.
Once the menu is running you can select the options to set your file paths, load the
lexicon into a Map, perform analysis and write the report to a specified directory of
your choosing.

Features

• Specify directory in the form path/to/directory for the location of the tweets.
The method will confirm that your inputs are directories and prompt you if they
are not.

• Parse the tweets into a concurrent linked deque using virtual threads. The
program is capable of handling all the tweets in the directory.

• Specify the location of the lexicon that you wish to use in the form
path/to/lexicon/chosen.txt. The program can parse 1 lexicon into a map
structure.

• The lexicon parses the files by splitting the word from the value at regex “ , ”
as this is the common structure of those files.

• After specifying the directory to write the report, the user can execute the
analysis of the tweets. The program will prompt the user to write a name for
the file.

• Once the option to execute analysis is selected the program will perform the
analysis via virtual threads and write the file.

• Inside the options menu, an option exists for the user to create a directory by
specifying the path in the form path/to/directory. This will create a directory if
the pathway is correct.
