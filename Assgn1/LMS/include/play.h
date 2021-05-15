
#ifndef PLAY_INCLUDE
#define PLAY_INCLUDE
//==========================================================================
#include <iostream>
#include <vector>
#include "book.h"
#include "act.h"
#include "character.h"
using namespace std;
class Play : public Book
{
    string Prologue;              //Optional Prologue
    vector<Act> Acts;             //List of Acts
    vector<Character> Characters; //List of characters

public:
    Play();                                                                                                                                      //CR
    Play(string _title, string _author, string _release_date, string lang, string file_name, string P = "");                                     //CR
    Play(string _title, string _author, string _release_date, string lang, string file_name, string P, vector<Act> As, vector<Character> Chars); //CR
    Play(const Play &other);                                                                                                                     //CCR
    Play &operator=(const Play &other);
    string get_type();                //returns type                                                                                //CAO
    void add_act(Act A);              //to add an act
    void add_char(Character C);       //to add Character C
    vector<Act> get_all_act();        //to get vector of all acts
    vector<Character> get_all_char(); //to get vectpor all characters
    int act_len();                    //to get no. of acts
    int char_len();                   //to get no. of characters
    Act get_act(int ind);             //to get act at index ind
    Character get_char(int ind);      //To get charecter at index i
    void set_prologue(string str);    //to set the prologue
    void print_asso_char(string nme); //To print all chracters associated with character with name nme
};
//==========================================================================
#endif