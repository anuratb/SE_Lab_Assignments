
#ifndef DIALOGUE_INCLUDE
#define DIALOGUE_INCLUDE
//==========================================================================
#include <iostream>
#include <string.h>
using namespace std;
class Dialogue
{
    string content;   //content of Dialogue as a string
    string char_name; //name of character

public:
    Dialogue();                                                 //CR
    Dialogue(string char_nm, string content);                   //CR
    Dialogue(const Dialogue &other);                            //CCR
    string get_cont();                                          //to get content
    string get_char();                                          //to get speaking character
    Dialogue &operator=(const Dialogue &D);                     //CAO
    inline void set_char(string nme) { char_name = nme; }       //set char name
    inline void append_content(string s) { content.append(s); } //add content
};

//==========================================================================
#endif