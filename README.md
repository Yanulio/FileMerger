# FileMerger
Console application that analyzes all the files in the directory and merges them

# Features
* Directory selection.
* Search for all requirements in the files.
* Merge files contents based on the requirements.

# Input Format
* Directory path
  * Has to be an absolute path to an existing directory.
  * Example: **D:/Steam/steamapps/common/dota 2 beta**
* Files in directory
  * The application only works with .txt files


* Requirements in the files
  * To mark a requirement for a specific file, you have to write somewhere in its contents a line: require 'A path to a file'.
  * The path must be relative to the directory. Example:
  * The given directory **D:/Steam** has a file **i_love_dota.txt**
and has a directory steamapps with **i_hate_dota.txt** file inside.
\
If **i_love_dota.txt** requires **i_hate_dota.txt** 
then in **i_love_dota.txt** there will be a line:
\
**require 'steamapps/i_hate_dota.txt'**
