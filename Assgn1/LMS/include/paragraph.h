
#ifndef PARAGRAPH_INCLUDE
#define PARAGRAPH_INCLUDE
//==========================================================================
#include <iostream>
#include <vector>
#include "sentence.h"
using namespace std;
class Paragraph
{
    int chap_no, para_no;       //no of chapters and paragraphs of parent
    string chap_name;           //name of parent chapter
    vector<Sentence> Sentences; //List of Sentences

public:
    Paragraph();                                               //CR
    Paragraph(const Paragraph &other);                         //CCR
    Paragraph &operator=(const Paragraph &other);              //CAO
    void add_Sentence(Sentence S);                             //for adding Sentence
    vector<Sentence> get_all();                                //for getting all sentences
    int len();                                                 //for getting no. of sentences
    Sentence get(int ind);                                     //for getting sentence at index ind(0 indexed)
    inline void set_chap_no(int n) { chap_no = n; }            //for setting chap no
    inline void set_para_no(int n) { para_no = n; }            //for setting para no
    inline void set_chap_name(string str) { chap_name = str; } //for setting chapter name
    inline int get_chap_no() { return chap_no; }               //fot getting chap no
    inline int get_para_no() { return para_no; }               //for getting para number
    inline string get_chap_nme() { return chap_name; }         //for getting parent chapter name
};
//==========================================================================
#endif