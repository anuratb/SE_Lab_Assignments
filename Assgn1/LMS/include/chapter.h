
#ifndef CHAPTER_INCLUDE
#define CHAPTER_INCLUDE
//=================================================================
#include <iostream>
#include <vector>
#include <string.h>
#include "paragraph.h"
using namespace std;
class Chapter
{
    int chap_no;                  //Chapter number in present book
    string Title;                 //Title of the chapter
    vector<Paragraph> Paragraphs; //List of Paragraphs

public:
    Chapter();                                      //CR
    Chapter(string title);                          //CR
    Chapter(const Chapter &other);                  //CCR
    Chapter &operator=(const Chapter &other);       //CAO
    void set_title(string title);                   //for setting title
    vector<Paragraph> get_all_para();               //for getting all paragraphs
    void add(Paragraph P);                          //for adding a paragraph
    int len();                                      //for getting number of paragraphs
    string get_title();                             //for getting title
    inline void set_chap_no(int n) { chap_no = n; } //for setting chapter number
    inline int get_chap_no() { return chap_no; }    //for getting chapter number
};
//=================================================================
#endif