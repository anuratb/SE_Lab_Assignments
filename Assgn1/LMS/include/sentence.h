
#ifndef SENTENCE_INCLUDE
#define SENTENCE_INCLUDE
//==========================================================================
#include <iostream>
#include <vector>
using namespace std;
class Sentence
{
    vector<string> Words; //List of words

public:
    Sentence();                                 //CR
    Sentence(const Sentence &other);            //CCR
    Sentence &operator=(const Sentence &other); //CAO
    void add_word(string str);                  //for adding word
    vector<string> get_all();                   //for getting all words
    int len();                                  //for getting no. of words
    string get(int ind);                        //for getting word at index ind(0 indexed)
};
//==========================================================================
#endif