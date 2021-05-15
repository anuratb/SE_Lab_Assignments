#include<cassert>
#ifndef CHARACTER_INCLUDE
#define CHARACTER_INCLUDE
//==========================================================================
#include <iostream>
#include <string.h>
#include <vector>
#include <set>
using namespace std;
class Character
{
    string name;//Character Name
    set<string> associated_char;//List of Character names who have worked with curretn char in at least one scene

public:
    Character();                                  //CR
    Character(string nme);                        //CR
    Character(const Character &other);            //CCR
    Character &operator=(const Character &other); //CAO
    string get_name();                            //for getting name
    void set_name(string nme);                    //for setting name
    void add_co(string char_nme);                 //for adding co character
    int len();                                    //for getting total of number of co actors
    vector<string> co_name();                     //for getting names of co acters
};
//==========================================================================
#endif